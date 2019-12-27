package infos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @author Adel
 *
 */
public class ToScanList {
	private ArrayList<ToScan> filesToScan = new ArrayList<ToScan>();

	public ArrayList<ToScan> getScanList() {
		return filesToScan;
	}

	public void addToScanList(ToScan file) {
		filesToScan.add(file);
	}

	public void addToScanList(File file) {
		ToScan toAdd;
		if (file.isDirectory()) {
			try {
				toAdd = new Folder(file.getPath());
				filesToScan.add(toAdd);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NotADirectoryException e) {
				e.printStackTrace();
			}
		} else {
			try {
				toAdd = new FileInfo(file.getPath());
				filesToScan.add(toAdd);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (NotAFileException e) {
				e.printStackTrace();
			}
		}
	}

	public void remove(ToScan file) {
		filesToScan.remove(file);
	}

	public void clear() {
		filesToScan.clear();
	}

	public int getToScanListCount() {
		return filesToScan.size();
	}

	public boolean isEmpty() {
		return filesToScan.isEmpty();
	}

	public ResultList scan() {
		ResultList results = new ResultList();
		for (ToScan file : filesToScan) {
			results.add(file.scan());
		}
		return results;
	}

	public String toString() {
		String tmp = "";
		for (File file : filesToScan) {
			tmp += file.toString();
		}
		return tmp;
	}
}