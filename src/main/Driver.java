import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import layouts.CellPanel;
import layouts.StatsPanel;
import layouts.background;
import models.Cell;
import models.Constants;
public class Driver {
	
	private static JFrame frame;
	private static CellPanel cellPanel;
	private static StatsPanel statPanel;
	private static ConwaysLogic logicThread;
	
    private static Cell[][] cell = new Cell[Constants.GRID_SIZE][Constants.GRID_SIZE];
	static int i = 0;
	
	public static void main(String args[]) {
		
		
		frame = new JFrame();
		frame.setVisible(true);
		cellPanel = new CellPanel();
		statPanel = new StatsPanel();
		JPanel title_panel = new JPanel();
		
		
		
		frame.setLayout(new BorderLayout());
		logicThread = new ConwaysLogic(cellPanel.getGrid());
		logicThread.start();

		
	
		
		
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
		
		JButton start, stop, save, load;
		start = new JButton("BEGIN");
		stop = new JButton("STOP");
		save = new JButton("SAVE");
		load = new JButton("LOAD");
		JButton btn = new JButton("Save");
		JTextField textfield = new JTextField(30);
		JButton reset = new JButton("RESET");
		
		
		Color avocado = new Color(86,130,3);
		start.setBackground(Color.yellow);
		stop.setBackground(Color.YELLOW);
		save.setBackground(Color.YELLOW);
		load.setBackground(Color.YELLOW);
		reset.setBackground(Color.YELLOW);
		
		start.setFont(new Font("", Font.BOLD,20));
		stop.setFont(new Font("", Font.BOLD, 20));
		save.setFont(new Font("", Font.BOLD, 20));
		load.setFont(new Font("", Font.BOLD, 20));
		reset.setFont(new Font("", Font.BOLD, 20));
		
		
		save.setBorder(new EmptyBorder(15,15,15,15));
		load.setBorder(new EmptyBorder(15,15,15,15));
		reset.setBorder(new EmptyBorder(15,15,15,15));
		start.setBorder(new EmptyBorder(15,15,15,15));
		stop.setBorder(new EmptyBorder(15,15,15,15));
		
	    JPanel buttonpanel = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    //c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    buttonpanel.add(start,c);
	    
	   // c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 2;
	    c.gridy = 0;
	    buttonpanel.add(stop,c);
	    
	    //c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 1;
	    c.gridy = 3;
	    c.insets = new Insets(10, 0, 0, 0);
	    buttonpanel.add(reset,c);
	    
	    c.gridx = 0;
	    c.gridy = 4;
	    //c.insets = new Insets(30, 0, 0, 0);
	    buttonpanel.add(save,c);
	    
	    c.gridx = 2;
	    c.gridy = 4;
	    //c.insets = new Insets(30, 0, 0, 0);
	    buttonpanel.add(load,c);
        

		
	buttonpanel.setBackground(background.custom);
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
					
					Load loader = new Load();
					loader.save();
						
				}
				else if(e.getSource() == save){
					Save saver = new Save(cellPanel.getGrid());
					saver.save();
				}
				else if(e.getSource() == reset){
					cellPanel.resetGrid(cell);
				}
			}
			
		};
		
		
		
		reset.addActionListener(startStopListener);
		start.addActionListener(startStopListener);
		stop.addActionListener(startStopListener);
		load.addActionListener(startStopListener);
		save.addActionListener(startStopListener);
	    
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   	frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.add(cellPanel, BorderLayout.LINE_START);
		frame.add(statPanel, BorderLayout.LINE_END);
		//frame.add(start, BorderLayout.PAGE_START);
		//frame.add(stop, BorderLayout.PAGE_END);
		
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
		
		JLabel title = new JLabel("CONWOY\'S GAME OF LIFE");
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("", Font.BOLD, 65));
		
		title.setBorder(new EmptyBorder(100, 15, 200, 20));
		title_panel.setBackground(custom);
		
		title_panel.add(title);
		
		
		
		frame.add(title_panel);
		frame.setBackground(custom);	
		
		//frame.dispose();
		Color color1 = new Color(247,247,57);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		JFrame welcome = new JFrame();
		welcome.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(color1);
		panel.setOpaque(true);
		
		JLabel welcometitle = new JLabel("CONWAY's GAME OF LIFE",JLabel.CENTER);
		welcometitle.setFont(new Font("",Font.BOLD,70));
		welcometitle.setBorder(new EmptyBorder(100,0,0,0));
		welcometitle.setForeground(custom);
		
		
		int x = (int) dim.getWidth();
		int y = (int) dim.getHeight();
		
		ImageIcon img = new ImageIcon("src/giphy2.gif");
		JLabel label = new JLabel(img);
        label.setBorder(new EmptyBorder(100,100,0,0));
		panel.add(welcometitle);
		panel.add(label);
	    
		JButton rules = new JButton("Rules");
		rules.setFont(new Font("",Font.BOLD,30));
		//rules.setBorder(new EmptyBorder(0,100,0,0));
		rules.setContentAreaFilled(false);
		//rules.setBackground(color1);
		//rules.setOpaque(false);
		ActionListener rulesListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == rules){
					welcome.dispose();
					JFrame rule = new JFrame("RULES");
					JButton cont = new JButton("Continue?");
					cont.setFont(new Font("",Font.BOLD,30));
					JPanel panelr = new JPanel();
					rule.setVisible(true);
					rule.setSize(1600, 800);
					rule.setLocationRelativeTo(null);
					ImageIcon image = new ImageIcon("src/Capture.jpg");
					JLabel img = new JLabel(image);
					panelr.add(img);
					panelr.add(cont);
					rule.add(panelr);
					ActionListener continueListener = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							if(arg0.getSource() == cont){
								rule.dispose();
							}
						}
					};
					cont.addActionListener(continueListener);
					
				}
			}
		};
		rules.addActionListener(rulesListener);
		panel.add(rules);
		title_panel.add(buttonpanel);
		
		welcome.setSize(x,y);
		welcome.add(panel);
		
	   //String mediaURL = "https://www.youtube.com/watch?v=NWfASBgBXLc";
	   
		
	}

	public static void updateGrid() {

		int count = cellPanel.update();
		int iterations = cellPanel.getIterations();
		statPanel.updateNumberOfCells(count);
		statPanel.updateNumberOfEvolutions(iterations);
		
	}
	
}
