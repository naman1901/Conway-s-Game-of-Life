import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.Cell;

public class Driver {

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		panel.setPreferredSize(new Dimension(700,700));
		Cell[][] c = new Cell[70][70];
		for(int i=0;i<70;i++){
			for(int j=0;j<70;j++) {
				c[i][j] = new Cell();
				c[i][j].setVisible(true);
				panel.add(c[i][j]);
			}
		}
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//	   	frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	
}
