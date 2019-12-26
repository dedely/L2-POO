package infos;

public class Result {

	private String fileInfos;
	private String analysisResults;
	private String anomaly;

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