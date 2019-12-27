package infos;

/**
 * @author Adel L'exception est générée lorsque l'extension d'un fichier
 *         n'est pas présente dans la base.
 *
 */
public class ExtensionNotFoundException extends Exception {
	public ExtensionNotFoundException() {
		super("Extension not found in our database!");
	}
}