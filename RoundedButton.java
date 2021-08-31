import javax.swing.*;
import java.awt.*;

public class RoundedButton extends JButton 
{	
	private Dimension button_size; 
	
	public RoundedButton() 
	{
		setBackground(Color.BLACK);
		button_size = new Dimension();
		button_size.width = (button_size.height = Math.max(getPreferredSize().width, getPreferredSize().height) + 25);
		setPreferredSize(button_size);
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		g.setColor(getBackground());
		g.fillOval(0, 0, button_size.width, button_size.height);
	}
	
	@Override
	protected void paintBorder(Graphics g) 
	{
		g.fillOval(0, 0, button_size.width, button_size.height);
	}
	
}
