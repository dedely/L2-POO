package infos;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;

public class Folder {
	private String folderName;
	private ArrayList<File> files;

	public Folder(String folderName) {
		this.folderName = folderName;
		files = new ArrayList<File>();
		remplissage(this.folderName);
	}

	public void remplissage(String folderName) // BOUCLE RECURSIVE
	{
		try {
			File f = new File(folderName);
			File[] fileInfos = f.listFiles();
			for (File file : fileInfos) {
				if (file.isDirectory()) {
					remplissage(file.getPath());
				}
				files.add(file);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public ArrayList<File> getArray() {
		return files;
	}

	public String toString() {
		Iterator<File> iter = files.iterator();
		String res = "Votre dossier " + folderName + " contient les fichiers suivants :\n\n ";
		while (iter.hasNext()) {
			res += iter.next().toString() + "\n";
		}
		return res;
	}
}
