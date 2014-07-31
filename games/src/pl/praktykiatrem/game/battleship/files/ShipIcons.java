package pl.praktykiatrem.game.battleship.files;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * 
 * Klasa <code>ShipIcons</code>
 *
 * klasa odpowiada za przygotowanie i przekazanie do aplikacji odpowiednich
 * obrazów
 *
 * @author filipr
 * @version 30 lip 2014 15:06:47
 *
 */
public class ShipIcons {
	/**
	 * tablica przechowuj±ca tablicê gotowych obrazów
	 */
	private static ImageIcon[] shipIcons = new ImageIcon[8];
	private static ImageIcon[] infoIcons = new ImageIcon[4];
	private static ImageIcon[] stateIcons = new ImageIcon[3];
	/**
	 * obiekt przechowuj±cy ikonê "ok"
	 */
	private static ImageIcon okIcon = new ImageIcon();

	/**
	 * 
	 * Metoda <code>createImages</code>
	 * 
	 * wype³nia tablicê obrazami
	 *
	 */
	public static final void createImages() {
		try {
			shipIcons[0] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\0.png"));
			shipIcons[1] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\1.png"));
			shipIcons[2] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\2.png"));
			shipIcons[3] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\3.png"));
			shipIcons[4] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\4.png"));
			shipIcons[5] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\5.png"));
			shipIcons[6] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\6.png"));
			shipIcons[7] = new ImageIcon(
					ShipIcons.class.getResource("\\shipsIcons\\7.png"));
			stateIcons[0] = new ImageIcon(
					ShipIcons.class.getResource("\\stateIcons\\8.png"));
			stateIcons[1] = new ImageIcon(
					ShipIcons.class.getResource("\\stateIcons\\9.png"));
			stateIcons[2] = new ImageIcon(
					ShipIcons.class.getResource("\\stateIcons\\10.png"));
			okIcon = new ImageIcon(ShipIcons.class.getResource("ok1.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 
	 * Metoda <code>getOkIcon</code>
	 * 
	 * zwraca ikonê "ok"
	 *
	 * @param type
	 * @return ikona ok
	 */
	public static ImageIcon getOkIcon(int type) {

		final BufferedImage img1 = iconToBufferedImage(shipIcons[type]);
		final BufferedImage img2 = iconToBufferedImage(okIcon);
		final BufferedImage combinedImage = new BufferedImage(img1.getWidth()
				+ img2.getWidth(),
				Math.max(img1.getHeight(), img2.getHeight()),
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D image = combinedImage.createGraphics();
		image.drawImage(img2, 0, 0, null);
		image.drawImage(img1, img2.getWidth(), 0, null);
		image.dispose();
		return new ImageIcon(combinedImage);
	}

	/**
	 * 
	 * Metoda <code>iconToBufferedImage</code>
	 * 
	 * Wiktor, czy móg³bym prosiæ Ciê o komentarz? ;*
	 *
	 * @param icon
	 * @return i tu te¿
	 */
	private static BufferedImage iconToBufferedImage(Icon icon) {
		if (icon == null)
			return null;

		BufferedImage image = new BufferedImage(icon.getIconWidth(),
				icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		icon.paintIcon(null, image.getGraphics(), 0, 0);
		return image;
	}

	/**
	 * 
	 * Metoda <code>getIcon</code>
	 *
	 * @param type
	 *            okre¶la typ ikony
	 * @return odpowiedni± ikonê, okre¶lon± przez argument type
	 */
	public static ImageIcon getShipIcon(int type) {
		if (type <= 7)
			return shipIcons[type];
		else
			return shipIcons[0];
	}

	public static ImageIcon getInfoIcon(int type) {
		if (type <= 3)
			return infoIcons[type];
		else
			return infoIcons[0];
	}

	public static ImageIcon getStateIcon(int type) {
		if (type <= 2)
			return stateIcons[type];
		else
			return stateIcons[0];
	}
}
