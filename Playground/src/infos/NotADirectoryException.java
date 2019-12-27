package infos;

/**
 * @author Adel L'exception est générée si une instance de la classe File n'est
 *         pas un dossier.
 *
 */
public class NotADirectoryException extends Exception {
	/**
	 * @param pathName
	 */
	public NotADirectoryException(String pathName) {
		super(pathName + " is not a directory");
	}
}