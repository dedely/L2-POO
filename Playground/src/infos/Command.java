package infos;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.io.File;

public class Command {
	private String[] commands;

	public Command(String[] commands) {
		this.commands = commands;

	}

	public void runCommand() throws UnknownCommandException {
		if (commands.length > 1) { 
			switch (commands[0].toLowerCase()) {
			case "-f":
				fileAnalysis(commands[1]);
				break;
			case "-d":
				folderAnalysis(commands[1]);
				break;
			case "-h":
				help();
				break;
			default:
				throw new UnknownCommandException(commands[0]);
			}
		} else {
			System.out.println("Merci d'entrer des paramêtres.");
			help();
		}
	}

	public Result fileAnalysis(String fileName) {
		Result analysisResult = null;
		try {
			FileInfo file = new FileInfo(fileName);
			AnalysisPushed fileAnalysis = new AnalysisPushed(file);
			analysisResult = new Result(fileAnalysis);
			System.out.println(analysisResult.toString());
			
			if (commands.length == 4 && commands[2].equals("-s") ) 		
				analysisResult.save(commands[3]);

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		return analysisResult;

	}

	public void folderAnalysis(String folderName) {
		try {
			Folder folder = new Folder(folderName);
			Iterator<File> iter = folder.getArray().iterator();
			while (iter.hasNext()) {
				fileAnalysis(iter.next().getPath());
				System.out.println("-----------------------------");
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public void help() {
		System.out.println("Liste de commandes :");
		System.out.println("\t-f : analyse fichier");
		System.out.println("\t-d : analyse dossier");
		System.out.println("\t-s : sauvegarde d'une analyse");
		System.out.println("\t-h : help");
	}
}
