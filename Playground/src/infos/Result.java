package infos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Result {

	private String fileInfos;
	private String analysisResults;

	public Result(AnalysisPushed fileAnalysis) {
		fileInfos = fileAnalysis.getFile().toString();
		analysisResults = fileAnalysis.toString();
	}

	public void save(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)); // Here true is to append the
																						// content to file
			writer.write(fileInfos + analysisResults);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void serializationSave(String fileName) {
		ObjectOutputStream stream;
		try {
			stream = new ObjectOutputStream(new FileOutputStream(fileName));
			stream.writeObject(fileInfos + analysisResults);
			stream.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public String toString() {
		String tmp = "";
		tmp += fileInfos + analysisResults;
		return tmp;
	}
}
