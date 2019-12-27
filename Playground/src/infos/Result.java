package infos;

import java.io.Serializable;

/**
 * @author Adel Permet de stocker le résultat de l'analyse d'un fichier.
 */
public class Result implements Serializable {

	private String fileInfos;
	private String analysisResults;
	private String anomaly;

	/**
	 * @param fileInfos
	 * @param analysisResults
	 * @param anomaly
	 */
	public Result(String fileInfos, String analysisResults, String anomaly) {
		this.fileInfos = fileInfos;
		this.analysisResults = analysisResults;
		this.anomaly = anomaly;
	}

	public String getFileInfos() {
		return fileInfos;
	}

	public String getAnalysisResults() {
		return analysisResults;
	}

	public String getAnomaly() {
		return anomaly;
	}

	public String toString() {
		String tmp = "";
		tmp += fileInfos + analysisResults + "Anomaly: " + anomaly + "\n";
		return tmp;
	}
}