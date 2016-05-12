package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import layouts.CellPanel;
import layouts.StatsPanel;
import logic.ConwaysLogic;
import logic.Load;
import logic.Save;
import layouts.Background;
import models.Cell;
import models.Constants;
import models.SaveObject;
public class Driver {
		
	private static JFrame frame, welcome, rule;
	private static CellPanel cellPanel;
	private static StatsPanel statPanel;
	private static ConwaysLogic logicThread;
	private static JButton start, stop, reset, save, load, exit;
	
    private static Cell[][] cell = new Cell[Constants.GRID_SIZE][Constants.GRID_SIZE];
	static int i = 0;
	
	public static void main(String args[]) {
		
		Color color1 = new Color(247,247,57);
		Color custom = new Color(58,82,170);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		
		WindowListener windowListener = new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				if(e.getSource() == frame) {
					logicThread.deactivate();
				}
				if(e.getSource() == welcome || e.getSource() == rule) {
					frame.setVisible(true);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				
			}
			
		};

		
		welcome = new JFrame();
		welcome.setVisible(true);
		welcome.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		welcome.addWindowListener(windowListener);
		welcome.getContentPane().setBackground(custom);
		
		JPanel panel = new JPanel();
		panel.setBackground(custom);
		panel.setOpaque(true);
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel welcometitle = new JLabel("Conway's Game of Life", JLabel.CENTER);
		welcometitle.setFont(new Font("Monotype Corsiva",Font.PLAIN,65));
		welcometitle.setForeground(Color.YELLOW);
	
		ImageIcon img = new ImageIcon("src/giphy.gif");
		JLabel label = new JLabel(img);
		
		panel.add(welcometitle, BorderLayout.PAGE_START);
		panel.add(label, BorderLayout.CENTER);
	    
		JButton rules = new JButton("Rules");
		rules.setFont(new Font("",Font.BOLD,30));
		rules.setBackground(Color.YELLOW);
		ActionListener rulesListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == rules){
					welcome.dispose();

					JLabel title = new JLabel("Conway's Game of Life", JLabel.CENTER);
					title.setFont(new Font("Monotype Corsiva",Font.PLAIN,65));
					title.setForeground(Color.YELLOW);
					
					rule = new JFrame("RULES");
					rule.getContentPane().setBackground(Color.YELLOW);
					rule.addWindowListener(windowListener);
					rule.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JButton cont = new JButton("Continue");
					cont.setFont(new Font("",Font.BOLD,30));
					cont.setBackground(Color.YELLOW);
					JPanel panelr = new JPanel();
					panelr.setLayout(new BorderLayout());
					panelr.setBackground(custom);
					panelr.setBorder(new EmptyBorder(10,10,10,10));
				    StyleContext sc = new StyleContext();
				    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
				    final Style textStyle = sc.addStyle("textStyle", null);
				    textStyle.addAttribute(StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
				    textStyle.addAttribute(StyleConstants.FontFamily, "Arial");
				    textStyle.addAttribute(StyleConstants.Foreground, Color.WHITE);
					JTextPane r = new JTextPane(doc);
					r.setPreferredSize(new Dimension(400,250));
					r.setBackground(custom);
					r.setBorder(new EmptyBorder(20,20,20,20));
					r.setEditable(false);
					try {
						doc.insertString(0, Constants.rules, null);
						doc.setParagraphAttributes(0, Constants.rules.length(), textStyle, true);
					} catch (BadLocationException e1) {
						e1.printStackTrace();
					}
					panelr.add(title, BorderLayout.PAGE_START);
					panelr.add(r, BorderLayout.CENTER);
					panelr.add(cont, BorderLayout.SOUTH);
					rule.add(panelr);
					ActionListener continueListener = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(arg0.getSource() == cont) {
								frame.setVisible(true);
								rule.dispose();
							}
						}
					};
					cont.addActionListener(continueListener);

					rule.setVisible(true);
					rule.setResizable(false);
					rule.pack();
					rule.setLocationRelativeTo(null);
				}
			}
		};
		rules.addActionListener(rulesListener);
		panel.add(rules, BorderLayout.PAGE_END);

		welcome.add(panel);
		welcome.pack();
		welcome.setResizable(false);
		welcome.setLocationRelativeTo(null);
		
		frame = new JFrame();
		frame.setVisible(false);
		cellPanel = new CellPanel();
		statPanel = new StatsPanel();
		JPanel title_panel = new JPanel();
		
		frame.setLayout(new BorderLayout());
		logicThread = new ConwaysLogic();
		logicThread.start();
		
		start = new JButton("START");
		stop = new JButton("STOP");
		reset = new JButton("RESET");
		save = new JButton("SAVE");
		load = new JButton("LOAD");
		exit = new JButton("EXIT");
		
		start.setBackground(Color.YELLOW);
		stop.setBackground(Color.RED);
		reset.setBackground(Color.YELLOW);		
		save.setBackground(Color.YELLOW);	
		load.setBackground(Color.YELLOW);	
		exit.setBackground(Color.YELLOW);	
		
		start.setFont(new Font("", Font.BOLD, 20));
		stop.setFont(new Font("", Font.BOLD, 20));
		reset.setFont(new Font("", Font.BOLD, 20));
		save.setFont(new Font("", Font.BOLD, 20));
		load.setFont(new Font("", Font.BOLD, 20));
		exit.setFont(new Font("", Font.BOLD, 20));
		
	    JPanel buttonpanel = new JPanel(new GridLayout(0,1,10,10));
	    buttonpanel.add(start);
	    buttonpanel.add(stop);
	    buttonpanel.add(reset);
	    buttonpanel.add(save);
	    buttonpanel.add(load);
	    buttonpanel.add(exit);
	    buttonpanel.setBackground(Background.custom);
	
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == start) {
					logicThread.performLogic();
					stop.setBackground(Constants.yellow);
					start.setBackground(Color.GREEN);
				}
				else if(e.getSource() == stop) {
					logicThread.suspend();
					stop.setBackground(Color.RED);
					start.setBackground(Constants.yellow);
				}
				else if(e.getSource() == reset) {
					cellPanel.resetGrid(cell);
					statPanel.updateNumberOfCells(0);
					statPanel.updateNumberOfEvolutions(0);
				}
				else if(e.getSource() == save) {
					String name = JOptionPane.showInputDialog("Enter Name: ");
					System.out.println("OK");
					int iterations = cellPanel.getIterations();
					Cell[][] cells = cellPanel.getGrid();
					int cellCount = cellPanel.getCellCount();
					Save s = new Save();
					s.save(cells, name, iterations, cellCount);
				}
				else if(e.getSource() == load) {
					Load l = new Load();
				}
				else if(e.getSource() == exit) {
					logicThread.deactivate();
					frame.dispose();
				}
			}
		};
		
		reset.addActionListener(buttonListener);
		start.addActionListener(buttonListener);
		stop.addActionListener(buttonListener);
		save.addActionListener(buttonListener);
		load.addActionListener(buttonListener);
		exit.addActionListener(buttonListener);
		
		statPanel.addButtonPanel(buttonpanel);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	   	frame.setBounds(0,0,screenSize.width, screenSize.height);
		frame.add(cellPanel, BorderLayout.LINE_START);
		frame.add(statPanel, BorderLayout.LINE_END);
		//frame.add(start, BorderLayout.PAGE_START);
		//frame.add(stop, BorderLayout.PAGE_END);
		
				
		frame.setResizable(false);
		frame.addWindowListener(windowListener);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(custom);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel title = new JLabel("Conway\'s Game of Life");
		title.setForeground(Color.YELLOW);
		title.setFont(new Font("Monotype Corsiva", Font.PLAIN, 65));
		title_panel.setBackground(custom);
		title_panel.add(title);		
		frame.add(title_panel, BorderLayout.PAGE_START);
		frame.setBackground(custom);
				
	}

	public static void updateGrid() {

		int count = cellPanel.update();
		int iterations = cellPanel.getIterations();
		statPanel.updateNumberOfCells(count);
		statPanel.updateNumberOfEvolutions(iterations);
		
	}
	
	public static void loadGrid(SaveObject s) {
		cellPanel.setGrid(s.getCell());
		statPanel.updateNumberOfCells(s.getCellCount());
		statPanel.updateNumberOfEvolutions(s.getIterations());
	}
	
	public static Cell[][] getGrid() {
		return cellPanel.getGrid();
	}
	
	public static void notifyStopped() {
		start.setBackground(Color.yellow);
		stop.setBackground(Color.RED);
	}
	
	public static void countUp() {
		cellPanel.countUp();
		statPanel.updateNumberOfCells(cellPanel.getCellCount());
	}
	
	public static void countDown() {
		cellPanel.countDown();
		statPanel.updateNumberOfCells(cellPanel.getCellCount());
	}

}
