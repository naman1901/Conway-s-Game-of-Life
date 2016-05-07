package logic;
import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import layouts.CellPanel;

public class SaveLoad {
	
	private final Runnable save = new Runnable() {
		
		@Override
		public void run(){
			saveGame();
		}
		
	};
	private static final ExecutorService executor = Executors.newFixedThreadPool(1);
	
	private final CellPanel panel1;
	
	public SaveLoad(CellPanel panel)
	{
		this.panel1 = panel;
	}
	
	public void save()
	{
		executor.submit(save);
	}
	public static void close(){
		executor.shutdown();
	}
	private void saveGame()
	{
		JFrame grid = new JFrame();
		grid.setVisible(true);
		grid.setSize(700, 700);
		grid.add(panel1);
		grid.setLocationRelativeTo(null);
		grid.pack();
	}
	
}
