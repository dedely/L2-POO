package infos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Fichier {

	private FileReader fr;
	private ArrayList<String> tab;

	public Fichier (String name) {
		try {
			fr = new FileReader(name);
		}
		catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public void read() 
	{
		String line="";
		try {
			BufferedReader reader = new BufferedReader(fr);
			while ((line=reader.readLine()) != null) {
				tab.add(line);
			}
			reader.close();
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		Iterator<String> iter = tab.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}