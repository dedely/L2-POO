package infos;

import java.io.FileNotFoundException;

public class Command {
	private String[] commands;

	public Command(String[] commands) {
		this.commands = commands;

	}

	public void runCommand() throws UnknownCommandException {
		switch (commands[0].toLowerCase()) {
		case "-f":
			try {
				fileAnalysis(commands[1]);
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			}
			break;
		case "-d":
			folderAnalysis();
			break;
		case "-h":
			help();
			break;
		default:
			throw new UnknownCommandException(commands[0]);
		}
	}

	public void fileAnalysis(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Analysis fileAnalysis = new Analysis(file);
		try {
			String tmp = "";
			String[] result = fileAnalysis.findExtensionInfosInDatabase();
			for (int index = 0; index < result.length; index++) {
				tmp += result[index] + "\t";
			}
			System.out.println(tmp);
		} catch (ExtensionNotFoundException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(fileAnalysis.checkMime().toString());
	}

	public void folderAnalysis() {

	}

	public void help() {
		System.out.println("Liste de commande :");
		System.out.println("\t-f : analyse fichier");
		System.out.println("\t-d : analyse dossier");
		System.out.println("\t-s : sauvegarde d'une analyse");
		System.out.println("\t-h : help");
	}
}
