package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import java.io.File;
/**
 * @author Adel
 * 
 * 
 */
public class FileInfo extends File {
	private String fileExtension;
	private String fileMimeType;
	
	public FileInfo(String fileName) {
		super(fileName);
		fileExtension = getExtensionUsingApacheCommonLib(getName());
		fileMimeType = getMimeUsingTika(getName());
	}
	
	public String getExtensionUsingApacheCommonLib(String fileName) {
	    return FilenameUtils.getExtension(fileName);
	    
	}
		
	public String getMimeUsingTika(String fileName){
		Tika tika = new Tika();
		return tika.detect(fileName);
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
	
	public String getMimeType() {
		return fileMimeType;
	}
}