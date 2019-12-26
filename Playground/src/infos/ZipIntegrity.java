package infos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipIntegrity implements IntegrityInterface{
	private boolean unzippedFile;
	
	public ZipIntegrity(File file) {
		unzippedFile = validatedIntegrity(file);
	}
	
	public boolean validatedIntegrity(File file) {
		unzippedFile = false;
		try {
			// création de la ZipInputStream qui va servir à lire les données du fichier zip
			File folder = new File("C:\\Users\\Adel\\Desktop\\Projet");
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
			// extractions des entrées du fichiers zip (i.e. le contenu du zip)
			ZipEntry ze = null;
			try {
				while ((ze = zis.getNextEntry()) != null) {

					// Pour chaque entrée, on crée un fichier
					// dans le répertoire de sortie "folder"
					File f1 = new File(folder.getCanonicalPath(), ze.getName());

					// Si l'entrée est un répertoire,
					// on le crée dans le répertoire de sortie
					// et on passe à l'entrée suivante (continue)
					if (ze.isDirectory()) {
						f1.mkdirs();
						continue;
					}

					// L'entrée est un fichier, on crée une OutputStream
					// pour écrire le contenu du nouveau fichier
					f1.getParentFile().mkdirs();
					OutputStream fos = new BufferedOutputStream(new FileOutputStream(f1));

					// On écrit le contenu du nouveau fichier
					// qu'on lit à partir de la ZipInputStream
					// au moyen d'un buffer (byte[])
					try {
						try {
							final byte[] buf = new byte[8192];
							int bytesRead;
							while (-1 != (bytesRead = zis.read(buf)))
								fos.write(buf, 0, bytesRead);
							
							unzippedFile = true;
						} finally {
							fos.close();
						}

					} catch (final IOException ioe) {
						// en cas d'erreur on efface le fichier
						f1.delete();
					}
				}
			} finally {
				// fermeture de la ZipInputStream
				zis.close();
			}
			//unzippedFile = true;
		} catch (FileNotFoundException e) {
			System.err.println("Decompression impossible1\n");
		} catch (Exception e) {
			System.err.println("decompression impossible2\n");
		}
		return unzippedFile;
	}
	
	public boolean getIntegrity() {
		return unzippedFile;
	}
	
	public String toString() {
		return "unzippedFile: " + unzippedFile + "\n";
	}
}
