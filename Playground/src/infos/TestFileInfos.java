package infos;

public class TestFileInfos {

	public static void main(String[] args) {
		String test = "test.txt";
		FileInfos testClass = new FileInfos();
		System.out.println(testClass.getExtensionUsingApacheCommonLib(test));
		System.out.println(testClass.getMimeUsingTika(test));
		//Test du passage d'une commande en paramêtres
		Command testCommand = new Command(args);
		try {
			System.out.println(testCommand.instruction());
		}catch (UnknownCommandException uce){
			System.err.println(uce.getMessage());
		}
	}
}
