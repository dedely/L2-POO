package infos;

public class UnknownCommandException extends Exception {
	/**
	 * @param command
	 * 
	 */
	public UnknownCommandException(String command) {
		super("Unknow Command: " + command);
	}
}