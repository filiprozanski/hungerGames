package pl.praktykiatrem.game.battleship.resources;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

/**
 * 
 * Klasa <code>ShipIcons</code>
 *
 * klasa odpowiada za przygotowanie i przekazanie do aplikacji odpowiednich
 * obraz�w
 *
 * @author filipr
 * @version 30 lip 2014 15:06:47
 *
 */
public class ShipIcons {
	/**
	 * tablica przechowuj�ca tablic� gotowych obraz�w i kolor�w
	 */
	private static ImageIcon[] infoIcons = new ImageIcon[4];
	private static ImageIcon[] stateIcons = new ImageIcon[3];
	private static Color[] colors;
	/**
	 * obiekt przechowuj�cy ikon� statku domy�lnego
	 * 
	 */
	private static ImageIcon defaultShipIcon;
	/**
	 * obiekt przechowuj�cy ikon� "ok"
	 */
	private static ImageIcon okIcon = new ImageIcon();

	/**
	 * 
	 * Metoda <code>createColors</code>
	 * 
	 * wype�nia tablic� color�w kolorami
	 *
	 */
	public static final void createColors(int wieViel) {
		if (wieViel > 10)
			colors = new Color[wieViel + 1];
		else
			colors = new Color[11];
		colors[1] = new Color(136, 0, 21);
		colors[2] = new Color(237, 28, 36);
		colors[3] = new Color(255, 127, 39);
		colors[4] = new Color(0, 162, 232);
		colors[5] = new Color(34, 177, 76);
		colors[6] = new Color(163, 73, 164);
		colors[7] = new Color(153, 100, 234);
		colors[8] = new Color(255, 0, 255);
		colors[9] = new Color(0, 0, 255);

		for (int i = 10; i < wieViel + 1; i++)
			colors[i] = Rand.getRandColor();
	}

	/**
	 * 
	 * Metoda <code>createImages</code>
	 * 
	 * wype�nia tablic� stateIcons oraz zmienne okIcon i defaultShipIcon grafik�
	 *
	 */
	public static final void createImages() {
		try {
			stateIcons[0] = new ImageIcon(
					ShipIcons.class
							.getResource("/pl/praktykiatrem/game/battleship/resources/stateIcons/8.png"));// statek
			// zatopiony
			stateIcons[1] = new ImageIcon(
					ShipIcons.class
							.getResource("/pl/praktykiatrem/game/battleship/resources/stateIcons/9.png"));// pud�o
			stateIcons[2] = new ImageIcon(
					ShipIcons.class
							.getResource("/pl/praktykiatrem/game/battleship/resources/stateIcons/10.png"));// statek
			// w
			// ogniu
			okIcon = new ImageIcon(ShipIcons.class.getResource("ok.png"));// zielone
																			// k�ko
																			// OK
			defaultShipIcon = new ImageIcon(
					ShipIcons.class.getResource("default.png"));// statek bez
																// narysowanego
																// �agla
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * 
	 * Metoda <code>getOkIcon</code>
	 * 
	 * zwraca ikon� statku z ikon� OK
	 *
	 * @param type
	 * @param horizontal
	 * @param shipTypes
	 * @return ikona ok
	 */
	public static ImageIcon getOkIcon(int type, int shipTypes, Direction dir) {

		final BufferedImage img1 = iconToBufferedImage(getShipIcon(type,
				shipTypes, dir));
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
	 * Metoda <code>shipDrawIcon</code>
	 * 
	 * rysuje na default.png �agiel o kolorze (type) i zwraca ikone statku
	 *
	 * @param type
	 * @return ikona ok
	 */
	private static ImageIcon shipDrawIcon(int type) {
		final BufferedImage img1 = iconToBufferedImage(defaultShipIcon);
		final BufferedImage img2 = new BufferedImage(15, 15,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D temp = img2.createGraphics();
		// Color color = new Color(255, 0, 255);
		temp.setColor(colors[type]);
		int size = 15;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				temp.drawLine(0, i, j, size);
		temp.dispose();

		final BufferedImage combinedImage = new BufferedImage(img1.getWidth(),
				img1.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D image = combinedImage.createGraphics();
		image.drawImage(img1, 0, 0, null);
		image.drawImage(img2, 13, 2, null);
		image.dispose();
		return new ImageIcon(combinedImage);
	}

	/**
	 * 
	 * Metoda <code>iconToBufferedImage</code>
	 * 
	 * konwertuje ikone do typu BufferedImage
	 *
	 * @param Icon
	 * @return i tu te�
	 */
	public static BufferedImage iconToBufferedImage(Icon icon) {
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
	 *            okre�la typ ikony
	 * @return odpowiedni� ikon�, okre�lon� przez argument type
	 */
	public static ImageIcon getShipIcon(int type) {
		return shipDrawIcon(type);
	}

	/**
	 * 
	 * Metoda <code>getShipImage</code>
	 *
	 * @param type
	 *            okre�la typ ikony
	 * @param polesNumber
	 *            okre�la ilos� maszt�w
	 * @param dir
	 *            okre�la kierunek maszt�w
	 * @return obraz statk�w o okre�lonej ilo�ci maszt�w i kierunku
	 */
	public static ImageIcon getShipIcon(int type, int polesNumber, Direction dir) {
		return new ImageIcon(getShipImage(type, polesNumber, dir));
	}

	public static BufferedImage getShipImage(int type, int polesNumber,
			Direction dir) {
		final BufferedImage img;
		if (type == 0)
			img = iconToBufferedImage(stateIcons[0]);
		else
			img = iconToBufferedImage(shipDrawIcon(type));

		final BufferedImage combinedImage;
		if (dir == null)
			dir = Direction.HORIZONTAL;
		if (dir == Direction.HORIZONTAL)
			combinedImage = new BufferedImage(img.getWidth() * polesNumber,
					img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		else
			combinedImage = new BufferedImage(img.getWidth(), img.getHeight()
					* polesNumber, BufferedImage.TYPE_INT_ARGB);

		Graphics2D image = combinedImage.createGraphics();
		for (int i = 0; i < polesNumber; i++)
			if (dir == Direction.HORIZONTAL)
				image.drawImage(img, i * img.getWidth(), 0, null);
			else
				image.drawImage(img, 0, i * img.getHeight(), null);

		image.dispose();
		return combinedImage;
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
