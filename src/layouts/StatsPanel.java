package layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.Constants;

public class StatsPanel extends JPanel {
	
	final private static int CELL_STAT = Constants.CELL_STAT;
	final private static int EVO_STAT = Constants.EVO_STAT;
	final private static int HORIZONTAL = Constants.HORIZONTAL;
	final private static int VERTICAL = Constants.VERTICAL;	
	final private static int UNIT_SIZE = Constants.SEGMENT_UNIT_SIZE;
	final private static Color yellow = Constants.yellow;
	final private static Color blue = Constants.blue;
	
	StatContainer numCells, numEvo;
	
	public StatsPanel() {	
		
		this.setLayout(new BorderLayout());
		numCells = new StatContainer(CELL_STAT);
		numEvo = new StatContainer(EVO_STAT);
		
		this.add(numCells, BorderLayout.PAGE_START);
		this.add(numEvo, BorderLayout.PAGE_END );
		this.setBorder(new EmptyBorder(10,10,10,10));
		this.setBackground(blue);
		
	}
	
	public void addButtonPanel(JPanel buttonPanel) {

		buttonPanel.setBorder(new EmptyBorder(50,10,50,10));
		this.add(buttonPanel, BorderLayout.CENTER);
		
	}
    
	public void updateNumberOfCells(int num) {
		numCells.setCount(num);
	}
	
	public void updateNumberOfEvolutions(int num) {
		numEvo.setCount(num);
	}
	
	@Override
    public Dimension getPreferredSize() {
        return new Dimension(320, Constants.GRID_SIZE*Constants.CELL_SIZE+120);
    }
	 
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
	 
	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	private class StatContainer extends JPanel {
		
		CounterPanel counter;
		
		public StatContainer(int which) {
			
			this.setLayout(new GridLayout(2,1));
			
			JLabel whichLabel = new JLabel("",SwingConstants.CENTER);
			whichLabel.setForeground(blue);
			whichLabel.setFont(new Font("", Font.BOLD, 20));
			whichLabel.setBorder(new EmptyBorder(10,10,10,10));
			whichLabel.setBackground(yellow);
			whichLabel.setOpaque(true);
			counter = new CounterPanel();
			counter.setBorder(new EmptyBorder(10,10,10,10));
			
			if(which==CELL_STAT) {
				whichLabel.setText("Number of Living Cells");
				this.add(whichLabel);
				this.add(counter);
			}
			else {	
				whichLabel.setText("Number of Evolutions");
				this.add(counter);
				this.add(whichLabel);		
			}
			//Color custom = new Color(58,82,170);
			this.setBackground(yellow);
			this.setBorder(new EmptyBorder(10,10,10,10));
		}
		
		public void setCount(int val) {
			counter.setCount(val);
		}
		
	}
	
	private class CounterPanel extends JPanel {
		
		Counter[] counter;
		
		public CounterPanel() {
			counter = new Counter[3];
			this.setLayout(new GridLayout(1,3));
			for(int i=2;i>=0;i--) {
				counter[i] = new Counter();
				this.add(counter[i]);
			}
			this.setBackground(blue);
			this.setOpaque(true);
		}
		
		public void setCount(int n) {
			int hundreds, tens, ones;
			ones = n%10;
			tens = (n/10)%10;
			hundreds = (n/100)%10;
			counter[0].setCount(ones);
			counter[1].setCount(tens);
			counter[2].setCount(hundreds);
		}
		
		@Override 
		public Dimension getPreferredSize() {
			return new Dimension(9*UNIT_SIZE, 15*UNIT_SIZE);
		}
		
		@Override 
		public Dimension getMinimumSize() {
			return getPreferredSize();
		}
		
		@Override 
		public Dimension getMaximumSize() {
			return getPreferredSize();
		}
		
	}

	private class Counter extends JPanel {
		
		LCDSegment[] segment = new LCDSegment[7];

		public Counter() {
			
			this.setBackground(blue);
			this.setOpaque(true);
			
			this.setLayout(new GridBagLayout());
			GridBagConstraints gbConstraints = new GridBagConstraints();
			
			/**
			 * Refer to this
			 * 
			 *     ___ (0)
			 *     
			 * 	 |(1)	|(2)
			 *     ___ (3)
			 *     
			 * 	 |(4)	|(5)
			 * 
			 *     ___ (6)
			 * 
			 */
			
			segment[0] = new LCDSegment(HORIZONTAL);
			gbConstraints.gridx = 1;
			gbConstraints.gridy = 0;
			gbConstraints.gridheight = 1;
			gbConstraints.gridwidth = 3;
			this.add(segment[0], gbConstraints);			
			
			segment[1] = new LCDSegment(VERTICAL);
			gbConstraints.gridx = 0;
			gbConstraints.gridy = 1;
			gbConstraints.gridheight = 3;
			gbConstraints.gridwidth = 1;
			this.add(segment[1], gbConstraints);			
			
			segment[2] = new LCDSegment(VERTICAL);
			gbConstraints.gridx = 4;
			gbConstraints.gridy = 1;
			gbConstraints.gridheight = 3;
			gbConstraints.gridwidth = 1;
			this.add(segment[2], gbConstraints);			
			
			segment[3] = new LCDSegment(HORIZONTAL);
			gbConstraints.gridx = 1;
			gbConstraints.gridy = 5;
			gbConstraints.gridheight = 1;
			gbConstraints.gridwidth = 3;
			this.add(segment[3], gbConstraints);			
			
			segment[4] = new LCDSegment(VERTICAL);
			gbConstraints.gridx = 0;
			gbConstraints.gridy = 6;
			gbConstraints.gridheight = 3;
			gbConstraints.gridwidth = 1;
			this.add(segment[4], gbConstraints);			
			
			segment[5] = new LCDSegment(VERTICAL);
			gbConstraints.gridx = 4;
			gbConstraints.gridy = 6;
			gbConstraints.gridheight = 3;
			gbConstraints.gridwidth = 1;
			this.add(segment[5], gbConstraints);			
			
			segment[6] = new LCDSegment(HORIZONTAL);
			gbConstraints.gridx = 1;
			gbConstraints.gridy = 9;
			gbConstraints.gridheight = 1;
			gbConstraints.gridwidth = 3;
			this.add(segment[6], gbConstraints);		
			
			this.setCount(0);
		}
		
		public void setCount(int n) {
			int i;
			for(i=0;i<7;i++) {
				segment[i].switchOff();
			}
			if(n==0 || n==2 || n==3 || n==5 || n==6 || n==7 || n==8 || n==9) {
				segment[0].switchOn();
			}
			if(n==0 || n==4 || n==5 || n==6 || n==8 || n==9) {
				segment[1].switchOn();
			}
			if(n==0 || n==1 || n==2 || n==3 || n==4 || n==7 || n==8 || n==9) {
				segment[2].switchOn();
			}
			if(n==2 || n==3 || n==4 || n==5 || n==6 || n==8 || n==9) {
				segment[3].switchOn();
			}
			if(n==0 || n==2 || n==6 || n==8) {
				segment[4].switchOn();
			}
			if(n==0 || n==1 || n==3 || n==4 || n==5 || n==6 || n==7 || n==8 || n==9) {
				segment[5].switchOn();
			}
			if(n==0 || n==2 || n==3 || n==5 || n==6 || n==8 || n==9) {
				segment[6].switchOn();
			}
		}
		
		@Override 
		public Dimension getPreferredSize() {
			return new Dimension(9*UNIT_SIZE, 5*UNIT_SIZE);
		}
		
		@Override 
		public Dimension getMinimumSize() {
			return getPreferredSize();
		}
		
		@Override 
		public Dimension getMaximumSize() {
			return getPreferredSize();
		}
		
	}
	

	
	private class LCDSegment extends JLabel {
		
		Dimension d;
		
		public LCDSegment(int orientation) {
			if(orientation == VERTICAL) {
				d = new Dimension(3*UNIT_SIZE, UNIT_SIZE);
			}
			else {
				d = new Dimension(UNIT_SIZE, 3*UNIT_SIZE);				
			}
			this.setBackground(Color.LIGHT_GRAY);
			this.setOpaque(true);
		}
		
		public void switchOff() {
			this.setBackground(blue);
		}
		
		public void switchOn() {
			this.setBackground(yellow);
		}
		
		@Override 
		public Dimension getPreferredSize() {
			return d;
		}
		
		@Override 
		public Dimension getMinimumSize() {
			return d;
		}
		
		@Override 
		public Dimension getMaximumSize() {
			return d;
		}
		
	}
	
}