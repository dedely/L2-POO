package infos;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Adel
 * Classe abstraite représentant un élément à analyser.
 *
 */
public abstract class ToScan extends File {
	public ToScan(String pathName) throws FileNotFoundException {
		super(pathName);
		if (!exists())
			throw new FileNotFoundException("File not found");
	}
	
	public abstract ResultList scan();
}