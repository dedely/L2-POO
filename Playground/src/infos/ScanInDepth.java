package infos;

/**
 * @authors Adel, Paul Cette classe fournit les méthodes nécessaires à une analyse complète.
 */
public class ScanInDepth extends Scan {
	IntegrityInterface integrity;

	public ScanInDepth(FileInfo file) {
		super(file);
		try {
			checkIntegrity(file);
		} catch (IntegrityTestUnavailableException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param file
	 * @throws IntegrityTestUnavailableException
	 */
	public void checkIntegrity(FileInfo file) throws IntegrityTestUnavailableException {
		if (getExtensionInfos() != null && getExtensionInfos().length > 3) {
			switch (getExtensionInfos()[3]) {
			case "i": // image
				integrity = new ImageIntegrity(getFile());
				break;
			case "z": // archive
				integrity = new ZipIntegrity(getFile());
				break;
			default:
				throw new IntegrityTestUnavailableException(file.getFileExtension());
			}
		}
		if (integrity != null && !integrity.getIntegrity()) {
			super.setAnomaly(true);
		}
	}

	public String toString() {
		String tmp = super.toString();
		if (integrity != null) {
			tmp += integrity.toString();
		}
		return tmp;
	}
}