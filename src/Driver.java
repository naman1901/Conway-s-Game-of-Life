import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import layouts.CellPanel;
import layouts.StatsPanel;
import models.Cell;

public class Driver {
	
	private static JFrame frame;
	private static CellPanel cellPanel;
	private static StatsPanel statPanel;
	private static ConwaysLogic logicThread;

	public static void main(String args[]) {
		frame = new JFrame();
		cellPanel = new CellPanel();
		statPanel = new StatsPanel();
		frame.setLayout(new BorderLayout());
		logicThread = new ConwaysLogic(cellPanel.getGrid());
		logicThread.start();
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
		
		JButton start, stop;
		start = new JButton();
		stop = new JButton();
		
		ActionListener startStopListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == start) {
					logicThread.performLogic();
				}
				else if(e.getSource() == stop) {
					logicThread.suspend();
				}
			}
			
		};
		
		start.addActionListener(startStopListener);
		stop.addActionListener(startStopListener);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   	frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.add(cellPanel, BorderLayout.LINE_START);
		frame.add(statPanel, BorderLayout.LINE_END);
		frame.add(start, BorderLayout.PAGE_START);
		frame.add(stop, BorderLayout.PAGE_END);
		frame.setResizable(true);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				logicThread.deactivate();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static void updateGrid() {

		int count = cellPanel.update();
		int iterations = cellPanel.getIterations();
		statPanel.updateNumberOfCells(count);
		statPanel.updateNumberOfEvolutions(iterations);
		
	}
	
}
