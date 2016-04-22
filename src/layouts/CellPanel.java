package layouts;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import models.Cell;

public class CellPanel extends JPanel {
	
	final public static int GRID_SIZE = 70;

	Cell[][] cell = new Cell[GRID_SIZE][GRID_SIZE];
	
	public CellPanel() {
		int i, j;
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints gbConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);
		for(i=0;i<GRID_SIZE;i++) {
			for(j=0;j<GRID_SIZE;j++) {
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
         return new Dimension(GRID_SIZE*Cell.CELL_SIZE, GRID_SIZE*Cell.CELL_SIZE);
     }

	 @Override
	 public Dimension getMaximumSize() {
		 return getPreferredSize();
	 }
	 
	 @Override
	 public Dimension getMinimumSize() {
		 return getPreferredSize();
	 }
	
}
