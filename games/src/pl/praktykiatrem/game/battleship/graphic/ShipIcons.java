package pl.praktykiatrem.game.battleship.graphic;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ShipIcons {
	private static Image[] elements = new Image[4];

	public static final void createImages() {
		try {
			elements[0] = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/files/1.PNG"));
			elements[1] = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/files/2.PNG"));
			elements[2] = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/files/3.PNG"));
			elements[3] = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/files/4.PNG"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static ImageIcon getIcon(int type) {
		return new ImageIcon(elements[type]);
	}
}
