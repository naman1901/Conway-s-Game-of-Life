package layouts;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import models.Cell;
import models.Constants;

public class CellPanel extends JPanel implements Runnable {

	private Cell[][] cell = new Cell[Constants.GRID_SIZE][Constants.GRID_SIZE];
	int iterations;
	
	public CellPanel() {
		iterations = 0;
		
		int i, j;
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints gbConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);
		for(i=0;i<Constants.GRID_SIZE;i++) {
			for(j=0;j<Constants.GRID_SIZE;j++) {
				cell[i][j] = new Cell();
				gbConstraints.gridx = i;
				gbConstraints.gridy = j;
				gbConstraints.weightx = 0.5;
				gbConstraints.weighty = 0.5;
//				gbConstraints.fill = GridBagConstraints.BOTH;
				cell[i][j].setVisible(true);
				this.add(cell[i][j], gbConstraints);
			}
		}
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(Constants.GRID_SIZE*Constants.CELL_SIZE, Constants.GRID_SIZE*Constants.CELL_SIZE);
     }

	 @Override
	 public Dimension getMaximumSize() {
		 return getPreferredSize();
	 }
	 
	 @Override
	 public Dimension getMinimumSize() {
		 return getPreferredSize();
	 }
	 
	 public Cell[][] getGrid() {
		 return cell;
	 }
	 
	 public int getIterations() {
		 return iterations;
	 }
	 
	 public void setGrid(Cell c[][]) {
		 
		 for(int i=0;i<Constants.GRID_SIZE;i++) {
			 for(int j=0;j<Constants.GRID_SIZE;j++) {
				 cell[i][j].changeState(c[i][j].getNewState());
			 }
		 }
		 
	 }
	 
	 public void setIterations(int iterations) {
		 this.iterations = iterations;
	 }
	 
	 public int update() {
		 int i, j;
		 int count = 0;
		 iterations++;
		 for(i=0;i<Constants.GRID_SIZE;i++) {
			 for(j=0;j<Constants.GRID_SIZE;j++) {
				 cell[i][j].copyState();
				 cell[i][j].setState();
				 if(cell[i][j].getNewState()) {
					 count++;
				 }
					 
			 }
		 }
		 return count;
	 }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
