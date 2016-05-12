package layouts;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Cell;
import models.Constants;

public class CellPanel extends JPanel {

	private Cell[][] cell = new Cell[Constants.GRID_SIZE][Constants.GRID_SIZE];
	int iterations;
	int cellCount;
	
	public CellPanel() {
		iterations = 0;
		cellCount = 0;
		
		int i, j;
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints gbConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);
		for(i=0;i<Constants.GRID_SIZE;i++) {
			for(j=0;j<Constants.GRID_SIZE;j++) {
				cell[i][j] = new Cell();
				gbConstraints.gridx = i;
				gbConstraints.gridy = j;
				cell[i][j].setVisible(true);
				this.add(cell[i][j], gbConstraints);
			}
		}
		this.setBackground(Constants.blue);
		this.setBorder(new EmptyBorder(0,20,0,10));
	}
	
	 @Override
     public Dimension getPreferredSize() {
         return new Dimension(Constants.GRID_SIZE*Constants.CELL_SIZE+50, Constants.GRID_SIZE*Constants.CELL_SIZE+120);
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
				 cell[i][j].copyState();
				 cell[i][j].setState();
			 }
		 }
		 
	 }
	 public void resetGrid(Cell[][] c){
		 cellCount = iterations = 0;
		 for(int i=0;i<Constants.GRID_SIZE;i++) {
			 for(int j=0;j<Constants.GRID_SIZE;j++) {
				cell[i][j].setVisible(true);
				cell[i][j].changeState(false);
				cell[i][j].copyState();
				cell[i][j].setState();
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
		 cellCount = count;
		 return count;
	 }

	public int getCellCount() {
		return cellCount;
	}

	public void setCellCount(int cellCount) {
		this.cellCount = cellCount;
	}
	
	public void countUp() {
		cellCount++;
	}
	
	public void countDown() {
		cellCount--;
	}
	
	public void disableComponents() {
		for(int i=0;i<Constants.GRID_SIZE;i++) {
			for(int j=0;j<Constants.GRID_SIZE;j++) {
				for(MouseListener ml: cell[i][j].getMouseListeners()) {
					cell[i][j].removeMouseListener(ml);
				}
			}
		}
	}
	 
}
