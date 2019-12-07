package infos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Analysis {
	private static String DATABASE = "signatures.csv";
	public final static String SEPARATOR = ";";
	private FileInfo file;
	private String[] extensionInfos;

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
		Boolean foundExtensionInDatabase = false;
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

	public Boolean checkMime() {
			return extensionInfos[1].equals(file.getMimeType());
	}

	public Boolean searchSignatureInFile() {
		boolean foundSignature = false;
		String line;
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file.getPath()));
			while (((line = in.readLine()) != null) && !foundSignature) {
				foundSignature = line.indexOf(extensionInfos[2]) >= 0;
				/*
				 * if (line.charAt(line.indexOf(extensionInfos[2])-1)=='/') { foundSignature =
				 * false; }
				 */
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
		return foundSignature;
	}

	public String[] getExtensionInfos() {
		return extensionInfos;
	}
	public String toString() {
		String tmp = "";
		if (extensionInfos != null) {
			for (String str : extensionInfos) {
				tmp += " " + str;
			}
		}
		return tmp;
	}
}
