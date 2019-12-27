package infos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Paul Fournit les méthodes nécessaires pour la vérification de
 *         l'intégrité d'une image.
 *
 */
public class ImageIntegrity implements IntegrityInterface {
	private int width;
	private int height;
	private boolean isImage;

	public ImageIntegrity(File file) {
		this.isImage = validatedIntegrity(file);
	}

	public boolean validatedIntegrity(File file) {
		boolean isImage = false;
		try {
			BufferedImage bimg = ImageIO.read(file);
			width = bimg.getWidth();
			height = bimg.getHeight();
			if (bimg != null) {
				isImage = true;
			}
		} catch (IOException e) {
			System.err.println("problème de dim\n");
		}
		return isImage;
	}

	public boolean getIntegrity() {
		return isImage;
	}

	public String toString() {
		String tmp = "";
		tmp += "isImage : " + isImage + "\tdimensions : width = " + width + " height = " + height + "\n";
		return tmp;
	}
}
