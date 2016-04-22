package layouts;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsPanel extends JPanel {
	
	final public static int CELL_STAT = 1;
	final public static int EVO_STAT = 2;
	public final static int HORIZONTAL = 1;
	public final static int VERTICAL = 2;	
	
	StatContainer numCells, numEvo;
	
	public StatsPanel() {	
		
		this.setLayout(new BorderLayout());
		numCells = new StatContainer(CELL_STAT);
		numEvo = new StatContainer(EVO_STAT);
		
		this.add(numCells, BorderLayout.PAGE_START);
		this.add(numEvo, BorderLayout.PAGE_END );
		
	}
	
	public void updateNumberOfCells(int num) {
		numCells.setCount(num);
	}
	
	public void updateNumberOfEvolutions(int num) {
		numEvo.setCount(num);
	}
	
	private class StatContainer extends JPanel {
		
		JLabel countLabel;
		
		public StatContainer(int which) {
			
			this.setLayout(new GridBagLayout());
			GridBagConstraints gbConstraints = new GridBagConstraints();
			gbConstraints.fill = GridBagConstraints.BOTH;
			JLabel whichLabel = new JLabel();
			countLabel = new JLabel("0");
			
			if(which==CELL_STAT) {
				whichLabel.setText("Number of Living Cells");
				this.add(whichLabel, gbConstraints);
				gbConstraints.gridy = 1;
				gbConstraints.gridheight = 2;
				this.add(countLabel, gbConstraints);
			}
			else {
				gbConstraints.gridheight = 2;
				this.add(countLabel, gbConstraints);	
				whichLabel.setText("Number of Evolutions");
				gbConstraints.gridheight = 1;
				gbConstraints.gridy = 2;
				this.add(whichLabel, gbConstraints);			
			}
			
		}
		
		public void setCount(int val) {
			countLabel.setText(Integer.toString(val));
		}
		
	}

//	private class CounterPanel extends JPanel {
//		
//		LEDSegment[] segment = new LEDSegment[7];
		
//		public CounterPanel() {
//			
//			GridBagLayout gbLayout = new GridBagLayout();
//			GridBagConstraints gbConstraints = new GridBagConstraints();
//			
//			for(int i=0;i<7;i++) {
//				
//			}
//			
//		}
//		
//	}
//	

	
//	private class LEDSegment extends JLabel {
//		
//		public final static int UNIT_SIZE = 5;
//		Dimension d;
//		
//		public LEDSegment(int orientation) {
//			if(orientation == VERTICAL) {
//				d = new Dimension(UNIT_SIZE, 3*UNIT_SIZE);
//			}
//			else {
//				d = new Dimension(3*UNIT_SIZE, UNIT_SIZE);				
//			}
//		}
//		
//		@Override 
//		public Dimension getPreferredSize() {
//			return d;
//		}
//		
//		@Override 
//		public Dimension getMinimumSize() {
//			return d;
//		}
//		
//		@Override 
//		public Dimension getMaximumSize() {
//			return d;
//		}
//		
//	}
	
}
