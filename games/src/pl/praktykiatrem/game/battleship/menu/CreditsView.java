package pl.praktykiatrem.game.battleship.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreditsView extends JPanel {

	private Image img;

	private IMainView mainView;

	public CreditsView(IMainView mainView) {
		this.mainView = mainView;
		// load the background image
		try {
			img = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/files/doge.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {
		JTextField text = new JTextField("No Siemaaaaaa :D ", 30);
		add(text);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
