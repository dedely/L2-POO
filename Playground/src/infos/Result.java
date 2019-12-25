package infos;

public class Result {

	private String fileInfos;
	private String analysisResults;

	public Result(ScanInDepth fileAnalysis) {
		fileInfos = fileAnalysis.getFile().toString();
		analysisResults = fileAnalysis.toString();
	}

	public String getFileInfos() {
		return fileInfos;
	}
	
	public String getAnalysisResults() {
		return analysisResults;
	}

	public String toString() {
		String tmp = "";
		tmp += fileInfos + analysisResults;
		return tmp;
	}
}