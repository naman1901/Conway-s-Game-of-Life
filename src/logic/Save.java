package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import models.Cell;
import models.SaveObject;

public class Save implements Runnable {

	Thread t;
	SaveObject s;
	
	@Override
	public void run() {
		
		try {

			System.out.println("Saving");
			
			Path currentRelativePath = Paths.get("");
			String path = currentRelativePath.toAbsolutePath().toString();
			
			path += "\\save\\";
			path += s.getName() + ".data";
			
			System.out.println("Current relative path is: " + path);
			
			File file = new File(path);
			if(!file.exists()) {
			    file.createNewFile();
			} 
			
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(s);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public Save() {
		t = new Thread(this);
	}
	
	public void save(Cell[][] cells, String name, int iterations, int cellCount) {
		s = new SaveObject(cells, name, iterations, cellCount);
		t.start();
	}
	
}
