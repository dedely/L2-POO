package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Adel
 * 
 * 
 */
public class FileInfo extends File {
	private String fileExtension;
	private String fileMimeType;

	public FileInfo(String fileName) throws FileNotFoundException {
		super(fileName);
		if (this.exists() && this.isFile()) {
			fileExtension = getExtensionUsingApacheCommonLib(getName());
			fileMimeType = getMimeUsingTika(getName());
		} else {
			throw new FileNotFoundException("Fichier non trouvé");
		}
	}

	public String getExtensionUsingApacheCommonLib(String fileName) {
		return FilenameUtils.getExtension(fileName);

	}

	public String getMimeUsingTika(String fileName) {
		Tika tika = new Tika();
		return tika.detect(fileName);
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getMimeType() {
		return fileMimeType;
	}

	public String toString() {
		return "File name: " + super.getName() + "\nCharacteristics:\n" + "\tExtension: " + fileExtension
				+ "\n\tMIME type: " + fileMimeType;

	}
}