package pl.praktykiatrem.game.battleship.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuView extends JPanel {

	private Image img;

	private IMainView mainView;

	public MenuView(IMainView mainView) {
		this.mainView = mainView;
		// load the background image
		try {
			img = ImageIO
					.read(new File(
							"src/pl/praktykiatrem/game/battleship/files/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {

		JButton buttonGoToGame = new JButton(mainView.GAME);
		JButton buttonGoToCredits = new JButton(mainView.CREDITS);
		JButton buttonGoToSet = new JButton(mainView.SET);

		buttonGoToCredits.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.showCredits();
			}
		});

		buttonGoToGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.startGame();
			}
		});

		buttonGoToSet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.showSet();
			}
		});

		buttonGoToCredits.setAlignmentX(100);
		buttonGoToCredits.setAlignmentY(100);

		add(buttonGoToGame);
		add(buttonGoToCredits);
		add(buttonGoToSet);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
