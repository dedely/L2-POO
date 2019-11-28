package infos;

public class TestFileInfos {

	public static void main(String[] args) {
		
	/*	String test = "test.txt";

		FileInfos testClass = new FileInfos();
		System.out.println(testClass.getExtensionUsingApacheCommonLib(test));
		System.out.println(testClass.getMimeUsingTika(test));

		//Test du passage d'une commande en paramêtres
		Command testCommand = new Command(args);
		try {
			testCommand.runCommand();
		}catch (UnknownCommandException uce){
			System.err.println(uce.getMessage());

		 */
		
		if (args[0].equals("-f")) {
			System.out.println("c'est l'analyse d'un fichier que vous souhaitez lancer\n");
			Fichier fic = new Fichier (args[1]);
			fic.comparaison();
		}
		if (args[0].equals("-d")) {
			System.out.println("c'est l'analyse d'un dossier que vous souhaitez lancer\n");

		}
		if (args[0].equals("-h")) {
			System.out.println("Liste de commande :\n");
			
			System.out.println("-f : analyse fichier");
			System.out.println("-d : analyse dossier");
			System.out.println("-s : sauvegarde d'une analyse");
			System.out.println("-h : help");

		}
	}
}
