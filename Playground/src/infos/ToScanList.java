package infos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ToScanList {
	private ArrayList<ToScan> filesToScan = new ArrayList<ToScan>();
	
	public ArrayList<ToScan> getScanList() {
		return filesToScan;
	}
	
	public void addToScanList(ToScan file){
		filesToScan.add(file);
	}
	
	public void addToScanList(File file) {
		ToScan toAdd;
		if(file.isDirectory()) {
			try {
				toAdd = new Folder(file.getPath());
				filesToScan.add(toAdd);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotADirectoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				toAdd = new FileInfo(file.getPath());
				filesToScan.add(toAdd);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotAFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void remove(ToScan file) {
		//A revoir...
		filesToScan.remove(file);
	}
	
	public int getToScanListCount() {
		return filesToScan.size();
	}
	
	public boolean isEmpty() {
		return filesToScan.isEmpty();
	}
	
	public String toString() {
		String tmp = "";
		for(File file : filesToScan) {	
			tmp += file.toString();
		}
		return tmp;
	}
}