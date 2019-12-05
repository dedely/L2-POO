package infos;

import java.util.ArrayList;
import java.util.Iterator;

public class Folder {
	private FileInfo D;
	private ArrayList<FileInfo> arrayFileInfo = new ArrayList<FileInfo>();
	public Folder(String nameFolder) {
		try {	
			this.D = new FileInfo(nameFolder);
			this.remplissage(nameFolder);
		}
		catch (Exception e) {
			System.err.println("Fichier introuvable\n");
		}
	}

	public void remplissage(String nameFolder) // BOUCLE RECURSIVE
	{	
		try {
			FileInfo f = new FileInfo(nameFolder);
			String[] FileInfos=f.list();
			FileInfo ff;
			for (int i=0;i<FileInfos.length;i++) {
				ff=new FileInfo(nameFolder+"/"+FileInfos[i]);
				if (ff.isDirectory()) {
					this.remplissage(nameFolder+"/"+FileInfos[i]);
				}
				this.arrayFileInfo.add(new FileInfo(FileInfos[i]));
			}
		}
		catch (Exception e) {
			System.err.println("Fichier introuvable\n");
		}
	}

	public String toString() {
		Iterator<FileInfo> iter = this.arrayFileInfo.iterator();
		String res = "Votre dossier " +this.D + " contient les fichiers suivants :\n\n ";
		while (iter.hasNext()) {
			res+=iter.next().toString() + "\n";
		}
		return res;
	}
}
