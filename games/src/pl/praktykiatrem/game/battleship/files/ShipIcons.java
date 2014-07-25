package pl.praktykiatrem.game.battleship.files;

import javax.swing.ImageIcon;

public class ShipIcons {
	private static ImageIcon[] elements = new ImageIcon[4];

	public static final void createImages() {
		try {
			elements[0] = new ImageIcon(ShipIcons.class.getResource("1.png"));
			elements[1] = new ImageIcon(ShipIcons.class.getResource("2.png"));
			elements[2] = new ImageIcon(ShipIcons.class.getResource("3.png"));
			elements[3] = new ImageIcon(ShipIcons.class.getResource("4.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static ImageIcon getIcon(int type) {
		return elements[type];
	}
}
