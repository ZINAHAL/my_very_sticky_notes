import javax.swing.*;
import java.awt.*;

public class RunApplication 
{	
	private static final int X = 1500,
			Y = 200,
			FRAME_WIDTH = 400,
			FRAME_HEIGHT = 400;
	
	public static void main(String[] command_line_inputs)  
	{	
		UIDefaults current_look_and_feel_defaults = UIManager.getDefaults();
		current_look_and_feel_defaults.put("Panel.background", new Color(242,242,189));
		current_look_and_feel_defaults.put("TextPane.background", new Color(242,242,189));
		current_look_and_feel_defaults.put("TextPane.font", new Font("Purisa", Font.BOLD, 18));
		current_look_and_feel_defaults.put("Button.background", new Color(255,220,35));
		current_look_and_feel_defaults.put("ScrollBar.background", new Color(242,242,189));
		current_look_and_feel_defaults.put("ScrollPane.border", new Color(242,242,189));
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setBounds(X, Y, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		StickyNote sn_app = new StickyNote(FRAME_WIDTH, FRAME_HEIGHT);
		frame.getContentPane().add(sn_app);
		frame.addWindowListener(sn_app.backEnd());
		frame.setVisible(true);
	}
	
}
