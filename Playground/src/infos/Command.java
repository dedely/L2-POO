package infos;

public class Command {
	private String command;

	public Command (String command) {
		this.command = command;

	}

	public String instruction() {
		String tmp = "";
		if (command.equals("-f")) {
			tmp += "c'est l'analyse d'un fichier que vous souhaitez lancer\n";
		}
		
		if (command.equals("-d")) {
			tmp += "c'est l'analyse d'un dossier que vous souhaitez lancer\n";
		}

		if (command.equals("-h")) {
			tmp += "Liste de commande :\n";
			tmp += "-f : analyse fichier\n";
			tmp += "-d : analyse dossier\n";
			tmp += "-s : sauvegarde d'une analyse\n";
			tmp += "-h : help\n";
		}
		return tmp;
	}

	public String toString() {
		return instruction();
	}
}
