package infos;

public class ScanInDepth extends Scan {
	IntegrityInterface integrity;

	public ScanInDepth(FileInfo file) {
		super(file);
		try {
			checkIntegrity(file);
		} catch (NoTestAvailableException e) {
			System.err.println(e.getMessage());
		}
	}

	public void checkIntegrity(FileInfo file) throws NoTestAvailableException {
		if (getExtensionInfos()!= null && getExtensionInfos().length > 3) {
			switch (getExtensionInfos()[3]) {
			case "i": // image
				integrity = new ImageIntegrity(getFile());
				break;
			case "z": // archive
				integrity = new ZipIntegrity(getFile());
				break;
			default:
				throw new NoTestAvailableException(file.getFileExtension());
			}
		}
	}
	
	public void anomaly() {
		if(integrity != null && !integrity.getIntegrity() ) {
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