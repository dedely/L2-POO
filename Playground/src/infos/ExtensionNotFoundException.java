package infos;

public class ExtensionNotFoundException extends Exception {
	public ExtensionNotFoundException() {
		super("Extension not found in our database!");
	}
}

