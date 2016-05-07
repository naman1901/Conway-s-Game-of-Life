package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import layouts.CellPanel;
import layouts.StatsPanel;
import logic.ConwaysLogic;
import logic.SaveLoad;

public class Driver {
	
	private static JFrame frame;
	private static CellPanel cellPanel;
	private static StatsPanel statPanel;
	private static ConwaysLogic logicThread;
    static CellPanel[] panel;
	static String[] user = new String[20];
	static int i = 0;
	
	public static void main(String args[]) {
		frame = new JFrame();
		cellPanel = new CellPanel();
		statPanel = new StatsPanel();
		panel = new CellPanel[20];
		frame.setLayout(new BorderLayout());
		logicThread = new ConwaysLogic(cellPanel.getGrid());
		logicThread.start();
		
		JButton start, stop, save, load;
		start = new JButton("BEGIN");
		stop = new JButton("STOP");
		save = new JButton("SAVE");
		load = new JButton("LOAD");
		
		Color avocado = new Color(86,130,3);
		start.setBackground(avocado);
		stop.setBackground(avocado);
		save.setBackground(Color.YELLOW);
		load.setBackground(Color.YELLOW);
		start.setFont(new Font("", Font.PLAIN,20));
		stop.setFont(new Font("", Font.PLAIN, 20));
		save.setFont(new Font("", Font.BOLD, 30));
		load.setFont(new Font("", Font.BOLD, 30));
		start.setForeground(Color.WHITE);
		stop.setForeground(Color.WHITE);
		save.setBorder(new EmptyBorder(20,20,20,20));
		load.setBorder(new EmptyBorder(20,20,20,20));
		
		ActionListener startStopListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == start) {
					logicThread.performLogic();
				}
				else if(e.getSource() == stop) {
					logicThread.suspend();
				}
				else if(e.getSource() == load){
					
					for(int x=0;x<i;x++){
							SaveLoad saver = new SaveLoad(panel[x]);
							saver.save();
							}
						
				}
				else if(e.getSource() == save){
					JFrame name = new JFrame();
					
					JTextField textfield = new JTextField("Enter your name: ");
					user[i] = textfield.getText();
					panel[i] = cellPanel;
					i=i+1;
					
					name.add(textfield);
					name.setSize(700,200);
					name.setLocationRelativeTo(null);
					name.setVisible(true);
				}
			}
			
		};
		
		start.addActionListener(startStopListener);
		stop.addActionListener(startStopListener);
		load.addActionListener(startStopListener);
		save.addActionListener(startStopListener);
		
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
		Color custom = new Color(58,82,170);
		
		//frame.setBackground(custom);
		JPanel title_panel = new JPanel();
		JLabel title = new JLabel("CONWOY\'S GAME OF LIFE");
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("", Font.BOLD, 65));
		
		title.setBorder(new EmptyBorder(100, 15, 200, 20));
		title_panel.setBackground(custom);
		
		title_panel.add(title);
		
		title_panel.add(save);
		title_panel.add(load);
		
		frame.add(title_panel);
		frame.setBackground(custom);	
	}

	public static void updateGrid() {

		int count = cellPanel.update();
		int iterations = cellPanel.getIterations();
		statPanel.updateNumberOfCells(count);
		statPanel.updateNumberOfEvolutions(iterations);
		
	}
	
}
