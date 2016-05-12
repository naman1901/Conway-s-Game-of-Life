package layouts;

import java.awt.Color;
import javax.swing.JPanel;

public class Background extends JPanel{
	
	public static Color custom;
	public static Color custom2;

	public Background()
	{
		custom = new Color(58,82,170);
		custom2 = new Color(0,48,255);
		this.setBackground(custom);
		
		//JPanel panel = new JPanel();
		//panel.add(title);
		//this.add(panel);
       //panel.setBackground(custom);
       //this.setPreferredSize(new Dimension(200, 200));
	}
	
}
