package infos;

public class ConsoleModeTest {

	public static void main(String[] args) {
		Command consoleInput = new Command(args);
		try {
			consoleInput.runCommand();
			System.out.println(consoleInput.getResults().toString());
		} catch (UnknownCommandException e) {
			System.err.println(e.getMessage());
		}
	}
}