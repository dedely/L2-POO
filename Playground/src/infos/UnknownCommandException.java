package infos;

public class UnknownCommandException extends Exception {
	public UnknownCommandException(String command) {
		super("Unknow Command: "+command);
	}
}
