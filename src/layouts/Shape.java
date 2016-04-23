package layouts;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
public class Shape extends JPanel {
	
	public Shape(){
		
		Color custom = new Color(58,82,170);
		this.setBackground(Color.YELLOW);
		
		JLabel title = new JLabel("PRESS L KEY FOR LEGEND");
		title.setForeground(Color.yellow);
		title.setFont(new Font("", Font.BOLD, 35));
		title.setBorder(new EmptyBorder(20,20,20,20));
		
		JPanel panel = new JPanel();
		panel.add(title);
		panel.setBackground(custom);
		
		this.add(panel);
		this.setBorder(new EmptyBorder(5,5,5,5));
		
	}

	
}
