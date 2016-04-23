package layouts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class background extends JPanel{

	public background()
	{
		Color custom = new Color(58,82,170);
		Color custom2 = new Color(0,48,255);
		this.setBackground(custom);
		
		//JPanel panel = new JPanel();
		//panel.add(title);
		//this.add(panel);
       //panel.setBackground(custom);
       //this.setPreferredSize(new Dimension(200, 200));
	}
	
}
