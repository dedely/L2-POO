package infos;

import java.util.ArrayList;

/**
 * @author Adel collection de Result. Permet de stocker le résultat de l'analyse
 *         d'un ou plusieurs fichiers
 *
 */
public class ResultList {
	private ArrayList<Result> results = new ArrayList<Result>();

	public ArrayList<Result> getResults() {
		return results;
	}

	/**
	 * @param result est ajouté à la liste de résultats.
	 */
	public void add(Result result) {
		results.add(result);
	}

	/**
	 * @param list ajoute les éléments de list à la liste de résultats.
	 */
	public void add(ResultList list) {
		for (Result result : list.getResults()) {
			results.add(result);
		}
	}

	/**
	 * @return le nombre d'éléments présents dans la liste. Cela correspond au
	 *         nombre de fichiers analysés.
	 */
	public int resultCount() {
		return results.size();
	}

	/**
	 * @return nombre d'anomalies rapportées
	 */
	public int anomalyCount() {
		int ctr = 0;
		for (Result result : results) {
			if (result.getAnomaly().equals("true"))
				ctr++;
		}
		return ctr;
	}

	public boolean isEmpty() {
		return results.isEmpty();
	}

	/**
	 * @return Un résumé des résultats contenus dans results.
	 */
	public String summarize() {
		String tmp = "";
		tmp += resultCount() + " file(s) scanned. " + anomalyCount() + " anomaly(ies) detected.\n";
		return tmp;
	}

	public void clear() {
		results.clear();
	}

	public String toString() {
		String tmp = "";
		for (Result result : results) {
			tmp += result.toString() + "\n" + "-----------------------------\n";
		}
		return tmp;
	}
}