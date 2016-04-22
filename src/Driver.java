import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import layouts.CellPanel;
import layouts.StatsPanel;
import models.Cell;

public class Driver {

	public static void main(String args[]) {
		JFrame frame = new JFrame();
		CellPanel cellPanel = new CellPanel();
		StatsPanel statPanel = new StatsPanel();
		frame.setLayout(new BorderLayout());
//		JPanel panel = new JPanel();
//		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
//		panel.setPreferredSize(new Dimension(700,700));
//		Cell[][] c = new Cell[70][70];
//		for(int i=0;i<70;i++){
//			for(int j=0;j<70;j++) {
//				c[i][j] = new Cell();
//				c[i][j].setVisible(true);
//				panel.add(c[i][j]);
//			}
//		}
		JButton button = new JButton();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button) {
					int x;
					x = (int) System.currentTimeMillis();
					x%=100;
					statPanel.updateNumberOfCells(x*2);
					statPanel.updateNumberOfEvolutions(x);
				}
				
			}
			
		});
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   	frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.add(cellPanel, BorderLayout.LINE_START);
		frame.add(statPanel, BorderLayout.LINE_END);
		frame.add(button, BorderLayout.PAGE_START);
		frame.setResizable(true);
		//frame.pack();
		frame.setVisible(true);
	}
	
}
