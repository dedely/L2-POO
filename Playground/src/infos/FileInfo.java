package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
/**
 * @author Adel
 * 
 * 
 */
public class FileInfo {
	private String fileName;
	private String fileExtension;
	private String fileMimeType;
	
	public FileInfo(String fileName) {
		this.fileName = fileName;
		fileExtension = getExtensionUsingApacheCommonLib(this.fileName);
		fileMimeType = getMimeUsingTika(this.fileName);
	}
	
	public String getExtensionUsingApacheCommonLib(String fileName) {
	    return FilenameUtils.getExtension(fileName);
	    
	}
		
	public String getMimeUsingTika(String fileName){
		Tika tika = new Tika();
		return tika.detect(fileName);
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
	
	public String getMimeType() {
		return fileMimeType;
	}
}