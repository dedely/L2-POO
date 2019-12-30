package infos;

/**
 * @authors Adel, Paul Cette classe fournit les méthodes nécessaires à une analyse complète.
 */
public class ScanInDepth extends Scan {
	IntegrityInterface integrity;
	Result result;
	
	public ScanInDepth(FileInfo file) {
		super(file);
		try {
			checkIntegrity();
		} catch (IntegrityTestUnavailableException e) {
			System.err.println(e.getMessage());
		}
		result = new Result(file.toString(), this.toString(), getAnomaly().toString());
	}

	/**
	 * @param file
	 * @throws IntegrityTestUnavailableException
	 */
	public void checkIntegrity() throws IntegrityTestUnavailableException {
		if (getExtensionInfos() != null && getExtensionInfos().length > 3) {
			switch (getExtensionInfos()[3]) {
			case "i": // image
				integrity = new ImageIntegrity(getFile());
				break;
			case "z": // archive
				integrity = new ZipIntegrity(getFile());
				break;
			default:
				throw new IntegrityTestUnavailableException(getFile().getFileExtension());
			}
		}
		if (integrity != null && !integrity.getIntegrity()) {
			super.setAnomaly(true);
		}
	}

	public Result getResult() {
		return result;
	}
	
	public String toString() {
		String tmp = super.toString();
		if (integrity != null) {
			tmp += integrity.toString();
		}
		return tmp;
	}
}