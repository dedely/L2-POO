package infos;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import java.awt.image.*;

public class AnalysisPushed extends Analysis{
	public AnalysisPushed(FileInfo file1) {
		super(file1);
		if (file1.getFileExtension().equals("zip")) {
			unzip();
		}
		else 
			dim();
	}
	
	 public void unzip (){
	    	try {
		        // création de la ZipInputStream qui va servir à lire les données du fichier zip
		        File folder = new File("C:\\Users\\paulb\\OneDrive\\Bureau\\prog\\JAVA\\decompress");
		        ZipInputStream zis = new ZipInputStream(
		                new BufferedInputStream(
		                        new FileInputStream(super.file)));
		        // extractions des entrées du fichiers zip (i.e. le contenu du zip)
		        ZipEntry ze = null;
		        try {
		            while((ze = zis.getNextEntry()) != null){

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
		                OutputStream fos = new BufferedOutputStream(
		                        new FileOutputStream(f1));
		           
		                // On écrit le contenu du nouveau fichier
		                // qu'on lit à partir de la ZipInputStream
		                // au moyen d'un buffer (byte[])
		                try {
		                    try {
		                        final byte[] buf = new byte[8192];
		                        int bytesRead;
		                        while (-1 != (bytesRead = zis.read(buf)))
		                            fos.write(buf, 0, bytesRead);
		                    }
		                    finally {
		                        fos.close();
		                    }
		                }
		                catch (final IOException ioe) {
		                    // en cas d'erreur on efface le fichier
		                    f1.delete();
		                }
		            }
		        }
		        finally {
		            // fermeture de la ZipInputStream
		            zis.close();
		        }
		    }
		    catch (FileNotFoundException e){
		    	System.err.println("Decompression impossible1\n");
		    }
		 	catch (Exception e) {
		   		System.err.println("decompression impossible2\n");
		    }
		}
	 
	 public void dim() {
		 try {
				BufferedImage bimg = ImageIO.read(super.file);
				int width          = bimg.getWidth();
				int height         = bimg.getHeight();
				System.out.println("dimension : width et height " + width + " et " + height);
				}
				catch (IOException e) {
					System.err.println("problème de dim\n");
				}
	 }
	 
}
