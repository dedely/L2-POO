package infos;

public class Result {

	private String fileInfos;
	private String analysisResults;
	private boolean anomaly;

	public Result(String fileInfos, String analysisResults, boolean anomaly) {
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
	
	public boolean getAnomaly() {
		return anomaly;
	}

	public String toString() {
		String tmp = "";
		tmp += fileInfos + analysisResults;
		return tmp;
	}
}