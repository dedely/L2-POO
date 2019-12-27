package infos;
/**
 * @author Adel L'exception est générée si une instance de la classe File n'est
 *         pas un dossier.
 *
 */
public class NotAFileException extends Exception {
	/**
	 * @param pathname
	 */
	public NotAFileException(String pathname) {
		super(pathname + " is not a file");
	}
}
