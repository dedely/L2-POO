package infos;

import java.io.File;

/**
 * @author Adel
 * 
 *
 */
public interface IntegrityInterface {
	boolean validatedIntegrity(File file);
	boolean getIntegrity();
}