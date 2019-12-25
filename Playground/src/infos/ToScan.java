package infos;

import java.io.File;
import java.io.FileNotFoundException;

public class ToScan extends File {
	public ToScan(String pathName) throws FileNotFoundException {
		super(pathName);
		if (!exists())
			throw new FileNotFoundException("File not found");
	}
}