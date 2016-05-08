package logic;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import layouts.CellPanel;

public class Load {
	
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
	
	CellPanel cellpanel = new CellPanel();
	ArrayList<CellPanel> objects = new ArrayList<>();
	ArrayList<String> names = new ArrayList<>();
	ArrayList<Integer> iterations = new ArrayList<>();
	public Load()
	{
		
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
	   	JFrame grid = new JFrame();
	   	grid.setVisible(true);
	   	
		FileInputStream fis = null;
	   	try{
	   		fis = new FileInputStream("file.txt");
	   		while(true){
	   			ObjectInputStream ois = new ObjectInputStream(fis);
	   			objects.add((CellPanel) ois.readObject());
	   			names.add((String) ois.readObject());
	   			iterations.add((Integer) ois.readObject());
	   		
	   		}}catch(EOFException e){}
	   		finally{
	   			if(fis!=null)
	   				fis.close();
	   		}
	   	for(int x=0;x<objects.size();x++){
	   		grid.add(objects.get(x));
	   		
	   	}
		
	}
	
}
