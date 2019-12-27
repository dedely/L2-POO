package infos;

/**
 * @author Adel La base de donnée est consultée pour déterminer le test
 *         d'intégrité à effectuer. L'exception est générée si la base suggère
 *         un test non supporté par l'application.
 *
 */
public class IntegrityTestUnavailableException extends Exception {
	/**
	 * @param fileExtension
	 */
	public IntegrityTestUnavailableException(String fileExtension) {
		super("No integrity test is available for this extension: " + fileExtension);
	}
}