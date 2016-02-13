package capaDePresentacion.gui.imagenes;

import javax.swing.ImageIcon;

public class CreateImage {
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = CreateImage.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			return null;
		}
	}
}
