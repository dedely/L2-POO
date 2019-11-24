import java.nio.file.*;

public class MainApp {
	public static void main (String [] args) {
		if (args[0].equals("-f")) {
			System.out.println("c'est l'analyse d'un fichier que vous souhaitez lancer\n");
			Path path = Paths.get("momo/love.txt");
			System.out.println(path);




		}
	}
}