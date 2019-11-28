package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
/**
 * @author Adel
 * 
 * 
 */
public class Extension {
	/**
	 * @param filename, it can be either a filename, or a path.
	 * @return file a extension. If the file has no extension, this method will return an empty string.
	 * If the filename is only an extension, this method will return the String after the dot.
	 */
	public String getExtensionUsingApacheCommonLib(String filename) {
	    return FilenameUtils.getExtension(filename);
	    
	}
		
	public String getMimeUsingTika(String fileName){
		Tika tika = new Tika();
		return tika.detect(fileName);
	}
}