package models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Cell extends JLabel implements Serializable {

	private boolean oldState;
	private boolean newState;

	
	
	public Cell() {
		oldState = newState = false;
		this.setOpaque(true);
		this.setBackground(Color.RED);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				newState = !newState;
				copyState();
				setState();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// Do nothing
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// Do nothing
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// Do nothing
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public boolean getOldState() {
		return oldState;
	}
	
	public boolean getNewState() {
		return newState;
	}
	
	public void copyState() {
		oldState = newState;
	}
	
	public void changeState(boolean state) {
		newState = state;
	}
	
	public void setState() {
		if(newState) {
			this.setBackground(Color.GREEN);
		} else {
			this.setBackground(Color.RED);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Constants.CELL_SIZE, Constants.CELL_SIZE);
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
