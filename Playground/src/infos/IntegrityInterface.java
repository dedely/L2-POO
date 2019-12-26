package infos;

import java.io.File;

public interface IntegrityInterface {
	boolean validatedIntegrity(File file);
	boolean getIntegrity();
}