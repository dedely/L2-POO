package infos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @authors Adel, Paul
 *
 */
public class Scan {
	private static String DATABASE = "/signatures.csv";
	public final static String SEPARATOR = ";";
	private FileInfo file;
	private String[] extensionInfos;
	private boolean foundExtensionInDatabase;
	private Boolean anomalyDetected;
	private Result result;

	public Scan(FileInfo file) {
		this.file = file;
		try {
			extensionInfos = searchExtensionInfosInDatabase();
		} catch (ExtensionNotFoundException e) {
			System.err.println(e.getMessage());
		}
		anomalyDetected = anomaly();
		
		result = new Result(file.toString(), this.toString(), anomalyDetected.toString());
	}

	/**
	 * @return les éléments de la base de données correspondant à l'extension du
	 *         fichier analysé
	 * @throws ExtensionNotFoundException si aucune information n'est disponible
	 *                                    dans la base.
	 */
	public String[] searchExtensionInfosInDatabase() throws ExtensionNotFoundException {
		String line, fields[] = null;
		foundExtensionInDatabase = false;
		try {
			InputStream in = getClass().getResourceAsStream(DATABASE);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			// On parcours la base
			while (((line = reader.readLine()) != null) && (!foundExtensionInDatabase)) {
				fields = line.split(SEPARATOR);
				// Jusqu'à trouver l'extension
				if (fields[0].equals(file.getFileExtension())) {
					foundExtensionInDatabase = true;
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		if (foundExtensionInDatabase == false) {
			throw new ExtensionNotFoundException();
		} else {
			return fields;
		}
	}

	public boolean foundExtensionInDatabase() {
		return foundExtensionInDatabase;
	}

	public Boolean checkMime() {
		return extensionInfos[1].equals(file.getMimeType());
	}

	/**
	 * @return si le contenu d'identification est présent dans le fichier analysé.
	 */
	public Boolean searchSignatureInFile() {
		boolean foundSignature = false;
		String line;
		BufferedReader in = null;
		if (extensionInfos.length == 2) {
			foundSignature = true; // Par défaut, si la 3eme case du fichier csv est vide, on considère qu'il n'y a
									// pas de pbs avec le contenu d'identification.
		} else {
			try {
				in = new BufferedReader(new FileReader(file.getPath()));
				//On parcours le fichier ligne par ligne.
				while (((line = in.readLine()) != null) && !foundSignature) {
					// la méthode String str.indexOf(String subStr) permet de retourner l'indice
					// d'une sous-chaine dans une chaine de caractères. Elle retourne -1 si la
					// sous-chaine n'est pas présente.
					// Donc si line.indexOf(extensionInfos[2]) >= 0, le contenu d'identification est
					// présent.
					foundSignature = line.indexOf(extensionInfos[2]) >= 0;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return foundSignature;
	}

	public String[] getExtensionInfos() {
		return extensionInfos;
	}

	public FileInfo getFile() {
		return file;
	}

	/**
	 * @return la présence d'anomalies.
	 */
	public boolean anomaly() {
		if (foundExtensionInDatabase) {
			return file.isEmpty() || !(checkMime() && searchSignatureInFile());
		} else {
			return file.isEmpty();
		}
	}

	public boolean getAnomaly() {
		return anomalyDetected;
	}

	public void setAnomaly(boolean newState) {
		anomalyDetected = newState;
	}

	public Result getResult() {
		return result;
	}

	public String toString() {
		String tmp = "FoundExtensionInDatabase : " + foundExtensionInDatabase + "\n";
		if (extensionInfos != null) {
			tmp += "Available data: ";
			for (String str : extensionInfos) {
				tmp += " " + str;
			}
			tmp += "\nMatching MIME type: " + checkMime().toString();
			tmp += "\nFound file signature: " + searchSignatureInFile().toString() + "\n";
		}
		return tmp;
	}
}