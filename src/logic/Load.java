package logic;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import layouts.CellPanel;
import models.Constants;
import models.SaveObject;
import main.Driver;

public class Load implements Runnable {

	Thread t;
	int i;
	ArrayList<SaveObject> savedObjects;
	JFrame frame;

	@Override
	public void run() {
		
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		
		path += "\\save\\";

		File folder = new File(path);
		File[] Files = folder.listFiles();

		for(File file: Files) {
			if(file.isFile()) {
				try {
					FileInputStream fin = new FileInputStream(path+file.getName());
					ObjectInputStream ois = new ObjectInputStream(fin);
					SaveObject obj = (SaveObject) ois.readObject();
					if(obj!=null) {
						savedObjects.add(obj);
						System.out.println(obj.getName());
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(savedObjects.size()==0) {
			JOptionPane.showMessageDialog(null, "No saved records found!", "Conway's Game of Life", JOptionPane.ERROR_MESSAGE);
			t.interrupt();
			return;
		}
		else {
			frame = new JFrame();
			frame.add(new LoadPanel());
			frame.setResizable(false);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
		}
		
	}
	
	public Load() {
		t = new Thread(this);
		i = 0;
		savedObjects = new ArrayList<SaveObject>();
		t.start();
	}
	
	private class LoadPanel extends JPanel {
		
		public LoadPanel() {
			
			this.setLayout(new BorderLayout());
			this.setBorder(new EmptyBorder(10,10,10,10));
			this.setBackground(Constants.blue);
		
			JLabel name = new JLabel("", JLabel.CENTER);
			name.setFont(new Font("", Font.BOLD, 30));
			name.setForeground(Constants.yellow);
			name.setBackground(Constants.blue);
			
			CellPanel p = new CellPanel() {
				@Override
			    public Dimension getPreferredSize() {
			        return new Dimension(Constants.GRID_SIZE*Constants.CELL_SIZE+11, Constants.GRID_SIZE*Constants.CELL_SIZE+2);
			    }
			};
			SaveObject obj = savedObjects.get(0);
			p.setGrid(obj.getCell());
			p.setIterations(obj.getIterations());
			p.setBorder(new EmptyBorder(0,20,0,20));
			p.disableComponents();
			name.setText(obj.getName());
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,0,50,10));
			buttonPanel.setBackground(Constants.blue);
			buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
			
			JButton left, right, load;
			left = new JButton("<<");
			right = new JButton(">>");
			load = new JButton("Load");

			left.setBackground(Constants.yellow);
			right.setBackground(Constants.yellow);
			load.setBackground(Constants.yellow);
			

			left.setFont(new Font("", Font.BOLD, 20));
			right.setFont(new Font("", Font.BOLD, 20));
			load.setFont(new Font("", Font.BOLD, 20));
		
			ActionListener buttonListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == left) {
						i--;
						if(i<0) {
							i = savedObjects.size() - 1;
						}
					}
					else if(e.getSource() == right) {
						i++;
						if(i>=savedObjects.size()) {
							i = 0;
						}
					}
					else if(e.getSource() == load) {
						Driver.loadGrid(savedObjects.get(i));
						frame.dispose();
						t.interrupt();
					}
					
					SaveObject obj = savedObjects.get(i);
					p.setGrid(obj.getCell());
					p.setIterations(obj.getIterations());
					name.setText(obj.getName());
				}
			};

			left.addActionListener(buttonListener);
			right.addActionListener(buttonListener);
			load.addActionListener(buttonListener);

			buttonPanel.add(left);
			buttonPanel.add(load);
			buttonPanel.add(right);
			
			this.add(name, BorderLayout.NORTH);
			this.add(p, BorderLayout.CENTER);
			this.add(buttonPanel, BorderLayout.SOUTH);
			
		}
		
	}
	
}
