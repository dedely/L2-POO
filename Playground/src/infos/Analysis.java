package infos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Analysis {
	private static String DATABASE = "C:\\mnt\\c\\Users\\Adel\\git\\L2-POO\\Playground\\resources\\signatures.csv";
	public final static String SEPARATOR = ";";
	protected FileInfo file;
	private String[] extensionInfos;
	private Boolean foundExtensionInDatabase;

	public Analysis(FileInfo file) {
		this.file = file;
		try {
			extensionInfos = searchExtensionInfosInDatabase();
		} catch (ExtensionNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return the appropriate line of the database as a String array.
	 * @throws ExtensionNotFoundException
	 */
	public String[] searchExtensionInfosInDatabase() throws ExtensionNotFoundException {
		String line, fields[] = null;
		foundExtensionInDatabase = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATABASE));
			while (((line = reader.readLine()) != null) && (!foundExtensionInDatabase)) {
				fields = line.split(SEPARATOR);
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

	public Boolean foundExtensionInDatabase() {
		return foundExtensionInDatabase;
	}

	public Boolean checkMime() {
		return extensionInfos[1].equals(file.getMimeType());
	}

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
				while (((line = in.readLine()) != null) && !foundSignature) {
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

	public String toString() {
		String tmp = "FoundExtensionInDatabase : " + foundExtensionInDatabase + "\n";
		if (extensionInfos != null) {
			tmp += "Available data: ";
			for (String str : extensionInfos) {
				tmp += " " + str;
			}
			tmp += "\nMatching MIME type: " + checkMime().toString() + "\nFound file signature: "
					+ searchSignatureInFile().toString() + "\n";
		}
		return tmp;
	}
}
