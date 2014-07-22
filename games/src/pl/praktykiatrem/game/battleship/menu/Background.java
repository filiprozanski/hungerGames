package pl.praktykiatrem.game.battleship.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {
	private Image img;

	public Background(String name) {
		// load the background image
		try {
			img = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/files/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
