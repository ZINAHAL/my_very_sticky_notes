import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;

public class StickyNote extends JPanel 
{	
	private int frame_width, frame_height;
	private JTextPane user_text_area;
	private DefaultStyledDocument styled_doc;
	private JScrollPane scroll_bar;
	private JButton color_button;
	private JButton clear_button;

	public StickyNote(int frame_width, int frame_height) 
	{
		this.frame_width = frame_width;
		this.frame_height = frame_height;
		user_text_area = new JTextPane();
		user_text_area.setContentType("text/rtf");
		styled_doc = (DefaultStyledDocument) user_text_area.getStyledDocument();
		scroll_bar = new JScrollPane(user_text_area);		
		scroll_bar.setPreferredSize(new Dimension(frame_width-15, frame_height-115));
		color_button = new RoundedButton();
		controlsForTextColor();
		clear_button = new JButton("CLEAR");
		controlsForClearButton();
		add(scroll_bar);
		add(color_button);
		add(clear_button);
		((FlowLayout) getLayout()).setHgap(40);
	}
	
	private void controlsForTextColor() 
	{
		color_button.addActionListener(
				
				new ActionListener() 
				{
					JColorChooser color_chooser = new JColorChooser(Color.BLACK);
					JDialog color_window = new JDialog();
					JButton ok_button = new JButton("OK");
					JButton cancel_button = new JButton("CANCEL");
					FlowLayout flow_layout = new FlowLayout();

					
					public void actionPerformed(ActionEvent e) 
					{
						/*
						 * i cannot put the below above as the recomended methods will not come up
						 */
						color_window.setBounds(1000, 200, frame_width+30, frame_height-110);
						color_window.setResizable(false);
						flow_layout.setHgap(30);
						flow_layout.setVgap(15);
						color_window.setLayout(flow_layout);
						color_window.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						color_window.getContentPane().add(color_chooser.getChooserPanels()[0]);
						color_window.getContentPane().add(color_chooser.getPreviewPanel());
						ok_button.setPreferredSize(cancel_button.getPreferredSize());
						color_window.getContentPane().add(ok_button);
						color_window.getContentPane().add(cancel_button);
						ok_button.addActionListener(
								
								new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
									{
										Color selected_color = color_chooser.getColor();
										Style text_style = styled_doc.addStyle("sticky_note_text_style", null);
										StyleConstants.setForeground(text_style, selected_color);
										user_text_area.setCharacterAttributes(text_style, true);
										color_button.setBackground(selected_color);
										color_window.dispose();
									}
								}
						);
						
						cancel_button.addActionListener(
								
								new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
									{
										color_window.dispose();
									}
								}
								
						);
		
						color_window.setVisible(true);
					}
				}
		);
	}
	
	private  class UserInputHandler extends WindowAdapter 
	{
		private File path_to_saved_content = new File("sticky_note_saved_content.rtf");
		private RTFEditorKit rtf_kit = new RTFEditorKit();
		
		@Override
		public void windowOpened(WindowEvent e) 
		{	
			if(path_to_saved_content.exists())
			{
				try(BufferedInputStream buffer_in = new BufferedInputStream(new FileInputStream(path_to_saved_content))) 
				{
					rtf_kit.read(buffer_in, styled_doc, 0);
					
				}catch(IOException ioe) 
				{
					ioe.printStackTrace();
				}
				catch(BadLocationException ble)
				{
					ble.printStackTrace();
				}
			}
		}
		
		@Override
		public void windowClosing(WindowEvent e) 
		{
			
			try(BufferedOutputStream buffer_out = new BufferedOutputStream(new FileOutputStream(path_to_saved_content))) 
			{
				removeExtraNewLine();
				rtf_kit.write(buffer_out, styled_doc, 0, styled_doc.getLength());
				
			}catch(IOException ioe) 
			{
				ioe.printStackTrace();
				
			}catch(BadLocationException ble)
			{
				ble.printStackTrace();
			}
			
			System.exit(0);
		}
		
		private void removeExtraNewLine() throws BadLocationException
		{
			int text_length = styled_doc.getLength();
			String text_in_doc = styled_doc.getText(0, text_length);
			if(text_length != 0 && text_in_doc.charAt(text_length-1) == '\n')
			{
				styled_doc.remove(text_length-1, 1);
			}
		}
		
	}
	
	public WindowAdapter backEnd() 
	{
		return new UserInputHandler();
	}
	
	private void controlsForClearButton()
	{
		clear_button.addActionListener(
				
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try {
							styled_doc.remove(0, styled_doc.getLength());
						} catch(BadLocationException ble) {
							ble.printStackTrace();
						}
					}
				}
		);
	}
	
}
