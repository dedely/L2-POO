package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Adel
 * 
 * 
 */
public class FileInfo extends File {
	private String fileExtension;
	private String fileMimeType;
	private long fileLength;

	public FileInfo(String fileName) throws FileNotFoundException {
		super(fileName);
		if (this.exists() && this.isFile()) {
			fileExtension = getExtensionUsingApacheCommonLib(getName());
			fileMimeType = getMimeUsingTika(getName());
			fileLength = length();
		} else {
			throw new FileNotFoundException(fileName + " was not found or is not a file.");
		}
	}

	public String getExtensionUsingApacheCommonLib(String fileName) {
		return FilenameUtils.getExtension(fileName);

	}

	public String getMimeUsingTika(String fileName) {
		Tika tika = new Tika();
		String tmp = "";
		try {
			tmp = tika.detect(this.toPath()); //à commenter.
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getMimeType() {
		return fileMimeType;
	}

	public boolean estVideFile() {
		return fileLength == 0;
	}

	public String toString() {
		return "File name: " + super.getName() + "\nCharacteristics:\n" + "\tExtension: " + fileExtension
				+ "\n\tMIME type: " + fileMimeType + "\n\tlength: " + fileLength + "\n";

	}
}