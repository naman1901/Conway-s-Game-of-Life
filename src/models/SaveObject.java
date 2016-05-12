package models;

import java.io.Serializable;

public class SaveObject implements Serializable {

	Cell[][] cell;
	String name;
	int iterations;
	int cellCount;

	public SaveObject() {
		//Default
	}
	
	public SaveObject(Cell[][] cell, String name, int iterations, int cellCount) {
		this.cell = new Cell[Constants.GRID_SIZE][Constants.GRID_SIZE];
		int i, j;
		for(i=0;i<Constants.GRID_SIZE;i++) {
			for(j=0;j<Constants.GRID_SIZE;j++) {
				this.cell[i][j] = new Cell();
				this.cell[i][j].changeState(cell[i][j].getNewState());
			}
		}
		this.name = name;
		this.iterations = iterations;
		this.cellCount = cellCount;
	}

	public Cell[][] getCell() {
		return cell;
	}

	public void setCell(Cell[][] cell) {
		this.cell = cell;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getIterations() {
		return iterations;
	}
	
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getCellCount() {
		return cellCount;
	}

	public void setCellCount(int cellCount) {
		this.cellCount = cellCount;
	}
	
	
}
