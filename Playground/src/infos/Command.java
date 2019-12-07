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
		if (commands.length > 0) {
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

	public void fileAnalysis(String fileName) {
		try {
			FileInfo file = new FileInfo(fileName);
			System.out.println(file);
			Analysis fileAnalysis = new Analysis(file);
			if (fileAnalysis.getExtensionInfos() != null) {
				// Si l'extension ne fait pas partie de la base de données, on ne dispose pas
				// d'informations, l'analyse n'est pas effectuée:
				// Les méthodes qui utilisent la base, ne sont pas appelées.
				System.out.println("Database information: " + fileAnalysis.toString());
				System.out.println("Matching MIME type: " + fileAnalysis.checkMime().toString());
				System.out.println("Found file signature: " + fileAnalysis.searchSignatureInFile().toString());
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public void folderAnalysis(String folderName) {
		Folder folder = new Folder(folderName);
		Iterator<File> iter = folder.getArray().iterator();
		while (iter.hasNext()) {
			fileAnalysis(iter.next().getPath());
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
