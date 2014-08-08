package pl.praktykiatrem.game.battleship.files;

import javax.swing.ImageIcon;

public class TTIcons {
	private static ImageIcon[] signIcons = new ImageIcon[2];

	public static final void createImages() {
		try {
			signIcons[0] = new ImageIcon(
					ShipIcons.class.getResource("\\tttIcons\\O.png"));
			signIcons[1] = new ImageIcon(
					ShipIcons.class.getResource("\\tttIcons\\X.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static ImageIcon getIcon(int type) {
		if (type < 2)
			return signIcons[type];
		else
			return null;
	}
}
