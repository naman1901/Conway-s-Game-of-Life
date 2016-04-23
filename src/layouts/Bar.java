package layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

public class Bar extends JPanel{

	public Bar()
	{
		Color custom = new Color(58,82,170);
		Count c = new Count();
		Shape s = new Shape();
		this.setBackground(custom);
		JPanel title_panel = new JPanel();
		JLabel title = new JLabel("CONWOY\'S GAME OF LIFE");
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("", Font.BOLD, 65));
		title.setBorder(new EmptyBorder(20, 20, 200, 20));
		title_panel.setBackground(custom);
		title_panel.add(title);
		this.add(title_panel);
		this.add(c);
	   
		this.add(s);
		
	}
}
