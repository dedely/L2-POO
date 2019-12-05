package infos;

import java.io.FileNotFoundException;

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
				folderAnalysis();
				break;
			case "-h":
				help();
				break;
			default:
				throw new UnknownCommandException(commands[0]);
			}
		} else {
			help();
		}
	}

	public void fileAnalysis(String fileName) {
		try {
			FileInfo file = new FileInfo(fileName);
			Analysis fileAnalysis = new Analysis(file);
			try {
				String tmp = "";
				String[] result = fileAnalysis.searchExtensionInfosInDatabase();
				for (int index = 0; index < result.length; index++) {
					tmp += result[index] + "\t";
				}
				System.out.println(tmp);
			} catch (ExtensionNotFoundException e) {
				System.err.println(e.getMessage());
			}
			System.out.println(file);
			System.out.println(fileAnalysis.checkMime().toString());
			System.out.println(fileAnalysis.searchSignatureInFile().toString());

		} catch (NullPointerException e) {
			System.err.println((e.getMessage()));
		} catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public void folderAnalysis() {

	}

	public void help() {
		System.out.println("Liste de commandes :");
		System.out.println("\t-f : analyse fichier");
		System.out.println("\t-d : analyse dossier");
		System.out.println("\t-s : sauvegarde d'une analyse");
		System.out.println("\t-h : help");
	}
}
