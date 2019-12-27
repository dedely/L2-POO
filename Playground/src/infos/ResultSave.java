package infos;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Adel Fournit les méthodes nécessaires à la sauvegarde et à la lecture
 *         des résultats dans un fichier. Il s'agit d'une adaptation des
 *         méthodes du TD8.
 *
 */
public class ResultSave extends ResultList {
	/**
	 * @param fileName
	 */
	public void save(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
			for (Result result : getResults()) {
				writer.write(result.getFileInfos() + result.getAnalysisResults());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param fileName
	 */
	public void serializationSave(String fileName) {
		ObjectOutputStream stream;
		try {
			stream = new ObjectOutputStream(new FileOutputStream(fileName));
			for (Result result : getResults()) {
				stream.writeObject(result);
			}
			stream.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @param fileName
	 */
	public void serializationRead(String fileName) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(fileName));
			Result result = null;
			while ((result = (Result) stream.readObject()) != null) {
				add(result);
			}
			stream.close();
		} catch (EOFException e) {
			// No message predefined, we have to write here our own message.
			System.out.println("End of file reading");
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
