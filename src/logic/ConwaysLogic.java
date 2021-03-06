package logic;

import javax.swing.JOptionPane;

import main.Driver;
import models.Cell;
import models.Constants;

public class ConwaysLogic implements Runnable {

	Thread t;
	boolean suspended;
	boolean active;
	int runSpeed;
	
	public ConwaysLogic() {
		suspended = true;
		active = true;
		runSpeed = 200;
	}
	
	@Override
	public void run() {
		
		int i, j;
		while(active) {
			try { 
				while(suspended && active) {
					Thread.sleep(1);
				}
				if(!active) {
					break;
				}
				boolean noChanges = true;
				//System.out.println("WORKING:");
				Cell[][] cell = Driver.getGrid();
				for(i=0;i<Constants.GRID_SIZE;i++) {
					for(j=0;j<Constants.GRID_SIZE;j++) {
						int l, r, t, b;
						l = j-1;
						r = j+1;
						t = i-1;
						b = i+1;
						if(r>=Constants.GRID_SIZE) {
							r = 0;
						}
						if(l<0) {
							l = Constants.GRID_SIZE - 1;
						}
						if(b>=Constants.GRID_SIZE) {
							b = 0;
						}
						if(t<0) {
							t = Constants.GRID_SIZE - 1;
						}
						int c = 0;
						if(cell[t][l].getOldState()) {
							c++;
						}
						if(cell[t][j].getOldState()) {
							c++;
						}
						if(cell[t][r].getOldState()) {
							c++;
						}
						if(cell[i][l].getOldState()) {
							c++;
						}
						if(cell[i][r].getOldState()) {
							c++;
						}
						if(cell[b][l].getOldState()) {
							c++;
						}
						if(cell[b][j].getOldState()) {
							c++;
						}
						if(cell[b][r].getOldState()) {
							c++;
						}
						if((cell[i][j].getOldState()) && (c<2 || c>3)) {
							cell[i][j].changeState(false);
							noChanges = false;
						}
						else if((!cell[i][j].getOldState()) && (c==3)) {
							cell[i][j].changeState(true);
							noChanges = false;
						}
						else {
							cell[i][j].changeState(cell[i][j].getOldState());
						}
					}
					
				}
				if(noChanges) {
					suspended = true;
					JOptionPane.showMessageDialog(null, "The grid is now stable!", "Conway's Game of Life", JOptionPane.INFORMATION_MESSAGE);
					Driver.notifyStopped();
				}
				else {
					Driver.updateGrid();
					Thread.sleep(runSpeed);
				}
			} catch(InterruptedException e) {
				System.out.print(e.getMessage());
			}
		}	
	}

	public void start() {
		if(t==null) {
			t = new Thread(this);
			t.start();
		}
	}
	
	public void suspend() {
		suspended = true;
	}
	
	public void performLogic() {
		suspended = false;
	}
	
	public void deactivate() {
		active = false;
	}
}
