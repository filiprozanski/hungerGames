package pl.praktykiatrem.game.battleship.files;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ShipIcons {
	private static ImageIcon[] elements = new ImageIcon[11];
	private static ImageIcon okIcon = new ImageIcon();

	public static final void createImages() {
		try {
			elements[0] = new ImageIcon(ShipIcons.class.getResource("0.png"));// bia³e
			elements[1] = new ImageIcon(ShipIcons.class.getResource("1.png"));
			elements[2] = new ImageIcon(ShipIcons.class.getResource("2.png"));
			elements[3] = new ImageIcon(ShipIcons.class.getResource("3.png"));
			elements[4] = new ImageIcon(ShipIcons.class.getResource("4.png"));
			elements[5] = new ImageIcon(ShipIcons.class.getResource("5.png"));
			elements[6] = new ImageIcon(ShipIcons.class.getResource("6.png"));
			elements[7] = new ImageIcon(ShipIcons.class.getResource("7.png"));
			elements[8] = new ImageIcon(ShipIcons.class.getResource("8.png"));
			elements[9] = new ImageIcon(ShipIcons.class.getResource("9.png"));
			elements[10] = new ImageIcon(ShipIcons.class.getResource("10.png"));
			okIcon = new ImageIcon(ShipIcons.class.getResource("ok1.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static ImageIcon getOkIcon(int type) {

		final BufferedImage img1 = iconToBufferedImage(elements[type]);
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

	public static BufferedImage iconToBufferedImage(Icon icon) {
		if (icon == null)
			return null;

		BufferedImage image = new BufferedImage(icon.getIconWidth(),
				icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		icon.paintIcon(null, image.getGraphics(), 0, 0);
		return image;
	}

	public static ImageIcon getIcon(int type) {
		if (type <= 10)
			return elements[type];
		else
			return elements[0];
	}
}
