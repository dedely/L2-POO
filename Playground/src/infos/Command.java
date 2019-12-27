package infos;

import java.io.FileNotFoundException;

/**
 * @author Adel Fournit les méthodes nécessaires à l'exécution des commandes
 *         utilisateur en mode console.
 */
public class Command {
	private String[] commands;
	private ResultSave results = new ResultSave();

	/**
	 * @param commands sont les arguments passés à la main.
	 */
	public Command(String[] commands) {
		this.commands = commands;

	}

	/**
	 * @throws UnknownCommandException si la commande saisie est inconnue.
	 */
	public void runCommand() throws UnknownCommandException {
		if (commands.length == 1) {
			if (commands[0].equals("-h")) {
				help();
			} else {
				throw new UnknownCommandException(commands[0]);
			}
		} else {
			if (commands.length > 1) {
				switch (commands[0]) {
				case "-f":
					fileAnalysis(commands[1]);
					break;
				case "-d":
					folderAnalysis(commands[1]);
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
	}

	/**
	 * @param pathName analyse d'un fichier
	 */
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

	/**
	 * @param pathName analyse d'un dossier
	 */
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

	/**
	 * @return les résultats d'une analyse
	 */
	public ResultList getResults() {
		return results;
	}

	/**
	 * liste les commandes disponibles
	 */
	public void help() {
		System.out.println("Liste de commandes :");
		System.out.println("\t-f filePath : analyse fichier");
		System.out.println("\t-d folderPath : analyse dossier");
		System.out.println("\t-s fileName: sauvegarde d'une analyse");
		System.out.println("\t-h : help");
	}
}
