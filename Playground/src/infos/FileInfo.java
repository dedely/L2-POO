package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Adel
 * 
 * 
 */
public class FileInfo extends ToScan {
	private String fileExtension;
	private String fileMimeType;
	private long fileLength;

	public FileInfo(String pathname) throws FileNotFoundException, NotAFileException {
		super(pathname);
		if (isFile()) {
			fileExtension = getExtensionUsingApacheCommonLib(getName());
			fileMimeType = getMimeUsingTika(getName());
			fileLength = length();
		} else {
			throw new NotAFileException(pathname);
		}
	}

	public String getExtensionUsingApacheCommonLib(String pathname) {
		return FilenameUtils.getExtension(pathname);

	}

	public String getMimeUsingTika(String pathname) {
		Tika tika = new Tika();
		String tmp = "";
		try {
			tmp = tika.detect(toPath()); // à commenter.
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

	public boolean isEmpty() {
		return fileLength == 0;
	}

	public ResultList scan() {
		ScanInDepth fileAnalysis = new ScanInDepth(this);
		ResultList analysisResult = new ResultList();
		analysisResult.add(fileAnalysis.getResult());
		return analysisResult;
	}

	public String toString() {
		return "File name: " + getName() + "\nCharacteristics:\n" + "\tExtension: " + fileExtension
				+ "\n\tMIME type: " + fileMimeType + "\n\tlength: " + fileLength + "\n";

	}
}