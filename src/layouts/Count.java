package layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Count extends JPanel{
		
		public Count(){
			
			Color custom = new Color(58,82,170);
			this.setBackground(Color.YELLOW);
			
			JLabel title = new JLabel("EVOLUTION COUNT");
			title.setForeground(Color.BLUE);
			title.setFont(new Font("", Font.BOLD, 35));
			title.setBorder(new EmptyBorder(20,20,20,20));
			
			JPanel shape = new JPanel();
			shape.setPreferredSize(new Dimension(200, 200));
			shape.setBackground(custom);
			
			
			//Shape s = new Shape();
			
			//s.setBackground(custom);
			this.add(title,BorderLayout.EAST);
			this.add(shape,BorderLayout.SOUTH);
			//this.add(s);
		}
	
}
