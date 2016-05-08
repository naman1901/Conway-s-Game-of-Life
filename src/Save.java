import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import layouts.CellPanel;

public class Save implements Serializable {
	
	private final Runnable save = new Runnable(){
		
		@Override
		public void run(){
			try {
				saveGame();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	private static ExecutorService executor = Executors.newFixedThreadPool(1);
	
	private CellPanel panel1;
	private String name1;
	private int iterations1;
	
	public Save(CellPanel panel,String name, int iterations)
	{
		this.panel1 = panel;
		this.name1 = name;
		this.iterations1 = iterations;
	}
	
	public void save()
	{
		executor.submit(save);
	}
	public static void close(){
		executor.shutdown();
	}
	private void saveGame() throws Exception
	{
		//JFrame grid = new JFrame();
		//grid.setVisible(true);
		//grid.setSize(700, 700);
		//grid.add(panel1);
		//grid.setTitle(name1);
		//grid.setLocationRelativeTo(null);
		//grid.pack();
		
		
		//Save to a file
		FileOutputStream fout = new FileOutputStream("file.txt");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		
		out.writeObject(panel1);
		out.writeObject(name1);
		out.write(iterations1);
		
		//out.flush();
		//grid.setTitle("success");
		
	}
	
}
