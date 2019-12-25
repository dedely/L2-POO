package infos;

import java.io.FileNotFoundException;

public class Command {
	private String[] commands;
	private ResultSave results = new ResultSave();
	
	public Command(String[] commands) {
		this.commands = commands;

	}

	public void runCommand() throws UnknownCommandException {
		if (commands.length > 1) {
			switch (commands[0].toLowerCase()) {
			// La méthode toLowerCase est utilisée pour rendre l'utilisation des commandes
			// insensible à la casse.
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
			 if (commands.length == 4 && commands[2].equals("-s")) {
				 results.save(commands[3] + ".txt");
				 results.serializationSave(commands[3] + ".ser");
			 }
		} else {
			System.out.println("Merci d'entrer des paramêtres.");
			help();
		}
	}

	public void fileAnalysis(String pathName) {
		try {
			FileInfo file = new FileInfo(pathName);
			results.add(file.scan());
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (NotAFileException e) {
			System.err.println(e.getMessage());
		}
	}

	public void folderAnalysis(String pathName) {
		try {
			Folder folder = new Folder(pathName);
			results.add(folder.scan());
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (NotADirectoryException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public ResultList getResults() {
		return results;
	}

	public void help() {
		System.out.println("Liste de commandes :");
		System.out.println("\t-f : analyse fichier");
		System.out.println("\t-d : analyse dossier");
		System.out.println("\t-s : sauvegarde d'une analyse");
		System.out.println("\t-h : help");
	}
}
