package infos;

import java.util.ArrayList;

public class ResultList {
	private ArrayList<Result> results = new ArrayList<Result>();

	public ArrayList<Result> getResults() {
		return results;
	}

	public void add(Result result) {
		results.add(result);
	}

	public void add(ResultList res) {
		for (Result result : res.getResults()) {
			results.add(result);
		}
	}

	public int resultCount() {
		return results.size();
	}
	
	public int anomalyCount() {
		int ctr = 0;
		for (Result result : results) {
			if(result.getAnomaly())
				ctr ++;
		}
		return ctr;
	}
	
	public boolean isEmpty() {
		return results.isEmpty();
	}
	
	public String summarize() {
		String tmp="";
		tmp += resultCount() + " file(s) scanned. " + anomalyCount() + " anomaly(ies) detected.\n";
		return tmp;
	}

	public String toString() {
		String tmp = "";
		for (Result result : results) {
			tmp += result.toString() + "\n" + "-----------------------------\n";
		}
		tmp += summarize() + "-----------------------------\n";
		return tmp;
	}
}