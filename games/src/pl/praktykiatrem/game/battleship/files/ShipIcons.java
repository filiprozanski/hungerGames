package pl.praktykiatrem.game.battleship.files;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ShipIcons {
	private static ImageIcon[] elements = new ImageIcon[11];

	public static final void createImages() {
		try {
			elements[0] = new ImageIcon(ShipIcons.class.getResource("0.png"));// bia³e
			elements[1] = new ImageIcon(ShipIcons.class.getResource("1t.png"));
			elements[2] = new ImageIcon(ShipIcons.class.getResource("2.png"));
			elements[3] = new ImageIcon(ShipIcons.class.getResource("3.png"));
			elements[4] = new ImageIcon(ShipIcons.class.getResource("4.png"));
			elements[5] = new ImageIcon(ShipIcons.class.getResource("5.png"));
			elements[6] = new ImageIcon(ShipIcons.class.getResource("6.png"));
			elements[7] = new ImageIcon(ShipIcons.class.getResource("7.png"));
			elements[8] = new ImageIcon(ShipIcons.class.getResource("8.png"));
			elements[9] = new ImageIcon(ShipIcons.class.getResource("9.png"));
			elements[10] = new ImageIcon(ShipIcons.class.getResource("10.png"));

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static BufferedImage createTransparentImage(final int width,
			final int height) {
		return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}

	public static ImageIcon getIcon(int type) {
		if (type <= 10)
			return elements[type];
		else
			return elements[0];
	}
}
