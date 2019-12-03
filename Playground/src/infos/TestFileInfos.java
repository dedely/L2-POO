package infos;

public class TestFileInfos {

	public static void main(String[] args) {
		Command consoleInput = new Command(args);
		try {
			consoleInput.runCommand();
		}catch(UnknownCommandException e) {
			System.err.println(e.getMessage());
		}
	}
}