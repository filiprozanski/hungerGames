package pl.praktykiatrem.game.battleship.files;

import javax.swing.ImageIcon;

public class ShipIcons {
	private static ImageIcon[] elements = new ImageIcon[6];

	public static final void createImages() {
		try {
			elements[0] = new ImageIcon(ShipIcons.class.getResource("0.png"));// bia³e
																				// pole
			elements[1] = new ImageIcon(ShipIcons.class.getResource("1.png"));// pud³o
			elements[2] = new ImageIcon(ShipIcons.class.getResource("2.png"));// trafiony
			elements[3] = new ImageIcon(ShipIcons.class.getResource("3.png"));// statek
			elements[4] = new ImageIcon(ShipIcons.class.getResource("4.png"));// ogieñ!!!
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static ImageIcon getIcon(int type) {
		return elements[type];
	}
}
