package infos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Fichier {

	public final static String SEPARATOR =";";

	private String extension;
	private String mime;

	private FileReader fr;
	private ArrayList<String> tab = new ArrayList<String>();


	public Fichier (String name) {
		try {
			fr = new FileReader(name);
		}
		catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		File testClass = new File();
		this.extension = testClass.getExtensionUsingApacheCommonLib(name);
		System.out.println(testClass.getExtensionUsingApacheCommonLib(name));
		this.mime=testClass.getMimeUsingTika(name);
		System.out.println(testClass.getMimeUsingTika(name));
	}



	public void read() 
	{
		String line="";
		try {
			BufferedReader reader = new BufferedReader(fr);
			while((line=reader.readLine())!=null) 
				System.out.println(line);
			reader.close();
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}





	public void comparaison() 
	{
		String line="";
		String testExt="";
		String testMime="";
		String testSign="";
		String fields[];


		// STOCKAGE DU FICHIER CSV

		try {
			BufferedReader reader = new BufferedReader(new FileReader("signatures.csv"));
			while ((line=reader.readLine()) != null) {
				fields=line.split(SEPARATOR);
				tab.add(fields[0]);
				tab.add(fields[1]);
				tab.add(fields[2]);
			}
			reader.close();
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}




		// PARCOURS JUSQU'A TROUVER L'EXTENSION


		Iterator<String> iter = tab.iterator();
		while (iter.next()!=this.extension) {
			testExt=iter.next();
			if (testExt.equals(this.extension)) {
				System.out.println(testExt + "  est l'extension de votre fichier\n");

				System.out.println((testMime=iter.next()) + "  est le type mime de votre fichier\n");

				System.out.println((testSign=iter.next()) + "  est la signature censee apparaitre dans votre fichier\n");
			}
			if (!iter.hasNext() || testExt.equals(this.extension))
				break;

		}
		if (testMime.equals(""))
			System.out.println("Votre extension n'existe pas\n");


		// COMPARAISON DES TYPES MIMES

		if (!testMime.equals(this.mime)) {
			System.out.println("votre fichier n'a pas la bonne extension, car le type MIME ne correspond pas");
		}
		else {
			System.out.println(" votre fichier semble etre un fichier " + this.extension);
		}
	


	}
}
