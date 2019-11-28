package infos;

public class Command {
	private String[] commands;

	public Command(String[] commands) {
		this.commands = commands;

	}

	public String instruction() throws UnknownCommandException {
		String tmp = "";
		for (String s : commands) {
			switch (s.toLowerCase()) {
			case "-f":
				tmp += "c'est l'analyse d'un fichier que vous souhaitez lancer\n";
				break;
			case "-h":
				tmp += "Liste de commande :\n";
				break;
			default:
				throw new UnknownCommandException(s);
			}
		}
		return tmp;
	}
}
