package infos;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Adel, Paul Permet de déterminer les informations de base d'un fichier donné
 *         : -son extension -son type Mime -sa taille
 */
public class FileInfo extends ToScan {
	private String fileExtension;
	private String fileMimeType;
	private long fileLength;

	/**
	 * @param pathname fournie par l'utilisateur
	 * @throws FileNotFoundException si l'adresse fournie est erronée ou le fichier
	 *                               n'est pas trouvé.
	 * @throws NotAFileException     si le fichier est en fait un dossier. Cela
	 *                               permet de s'assurer que l'utilisateur entre les
	 *                               bons arguments en mode console.
	 */
	public FileInfo(String pathname) throws FileNotFoundException, NotAFileException {
		super(pathname);
		if (isFile()) {
			fileExtension = getExtensionUsingApacheCommonLib(getName());
			fileMimeType = getMimeUsingTika(getName());
			fileLength = length();
		} else {
			throw new NotAFileException(pathname);
		}
	}

	/**
	 * @param pathname
	 * @return l'extension d'un fichier l'aide de la libraire ApacheCommonLib
	 */
	public String getExtensionUsingApacheCommonLib(String pathname) {
		return FilenameUtils.getExtension(pathname);
	}

	/**
	 * @param pathname
	 * @return le type mime d'un fichier à l'aide de la libraire Tika. la méthode
	 *         detect(Path path) fournie par Tika s'appuie à la fois sur l'extension
	 *         d'un fichier et son contenu pour déterminer son type MIME. Ce n'est
	 *         pas le cas d'autres variantes de la méthode detect.
	 */
	public String getMimeUsingTika(String pathname) {
		Tika tika = new Tika();
		String tmp = "";
		try {
			tmp = tika.detect(toPath()); // à commenter.
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getMimeType() {
		return fileMimeType;
	}

	public boolean isEmpty() {
		return fileLength == 0;
	}

	/**
	 * Lance l'analyse d'un fichier et retourne le résultat.
	 */
	public ResultList scan() {
		ScanInDepth fileAnalysis = new ScanInDepth(this);
		ResultList analysisResult = new ResultList();
		analysisResult.add(fileAnalysis.getResult());
		return analysisResult;
	}

	public String toString() {
		return "File name: " + getName() + "\nCharacteristics:\n" + "\tExtension: " + fileExtension + "\n\tMIME type: "
				+ fileMimeType + "\n\tlength: " + fileLength + "\n";

	}
}