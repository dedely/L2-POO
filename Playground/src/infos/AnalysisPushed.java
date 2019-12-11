package infos;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class AnalysisPushed extends Analysis {
	private Boolean unzippedFile;
	private Boolean special = false;

	public AnalysisPushed(FileInfo file1) {
		super(file1);
		if (file1.getFileExtension().equals("zip")) {
			special = true;
			unzip();
		} else {
			if (file1.getFileExtension().toString().equals("jpg")|| file1.getFileExtension().toString().equals("png")) {
				special = true;
				dim();
			}
		}
	}

	public Boolean unzip() {
		unzippedFile = false;
		try {
			// création de la ZipInputStream qui va servir à lire les données du fichier zip
			File folder = new File("C:\\Users\\Adel\\Desktop\\Projet");
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(super.file)));
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
	
	public String dim() {
		String tmp = "";
		try {
			BufferedImage bimg = ImageIO.read(super.file);
			int width = bimg.getWidth();
			int height = bimg.getHeight();
			tmp += "dimension : width = " + width + " height = " + height +"\n";
		} catch (IOException e) {
			System.err.println("problème de dim\n");
		}
		return tmp;
	}
	
	public String toString() {
		String tmp = super.toString();
		if(special) {
			if(getFile().getFileExtension().equals("zip")) {
				tmp += "Unzipped: " + unzippedFile + "\n";
			}else {
				tmp += dim();
			}
		}
		return tmp;
	}

}
