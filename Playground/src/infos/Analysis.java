package infos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Adel
 *
 */
public class Analysis {
	private static String DATABASE = "signatures.csv";
	public final static String SEPARATOR = ";";
	private File file;
	private String[] extensionInfos;
	
	public Analysis(File file) {
		this.file = file;
		try {
			extensionInfos = findExtensionInfosInDatabase();
		}catch (ExtensionNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return the appropriate line of the database as a String array.
	 * @throws ExtensionNotFoundException
	 */
	public String[] findExtensionInfosInDatabase() throws ExtensionNotFoundException {
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
		if (fields == null) {
			throw new ExtensionNotFoundException();
		} else {
			return fields;
		}
	}
	
	public Boolean checkMime() {
		return extensionInfos[1].equals(file.getMimeType());
	}
}
