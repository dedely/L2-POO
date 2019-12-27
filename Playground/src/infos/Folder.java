package infos;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Paul
 *
 */
public class Folder extends ToScan {
	private ArrayList<File> files;

	public Folder(String pathName) throws FileNotFoundException, NotADirectoryException {
		super(pathName);
		if (isDirectory()) {
			files = new ArrayList<File>();
			addToList(this);
		} else {
			throw new NotADirectoryException(pathName);
		}
	}

	/**
	 * @param folder liste les fichiers d’une arborescence quelconque en s'appuyant
	 *               sur la méthode listFile() de la classe File.
	 */
	public void addToList(File folder) {
		// Les dossiers ne sont pas ajoutés à la liste.
		if (folder.isFile()) {
			files.add(folder);
		} else {
			try {
				File[] fileInfos = folder.listFiles();
				for (File file : fileInfos) {
					if (file.isDirectory()) {
						addToList(file); // Appel récursif
					} else {
						files.add(file);
					}
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public ArrayList<File> getArray() {
		return files;
	}

	/**
	 * Lance l'analyse d'un dossier et retourne le résultat.
	 */
	public ResultList scan() {
		ResultList results = new ResultList();
		// Parcours de la liste de fichier
		Iterator<File> iter = getArray().iterator();
		while (iter.hasNext()) {
			FileInfo file;
			try {
				// Traitement
				file = new FileInfo(iter.next().getPath());
				results.add(file.scan());
			} catch (NotAFileException e) {
				System.err.println(e.getMessage());
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
		}
		return results;
	}

	public String toString() {
		Iterator<File> iter = files.iterator();
		String res = "Votre dossier " + getName() + " contient les fichiers suivants :\n\n ";
		while (iter.hasNext()) {
			res += iter.next().toString() + "\n";
		}
		return res;
	}
}