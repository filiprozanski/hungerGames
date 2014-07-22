package pl.praktykiatrem.game.battleship;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Menu extends JPanel {
	private class StartGameAction extends AbstractAction {

		public StartGameAction() {
			super(GoToGame);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			mainView.startGame();

		}

	}

	private Image img;
	final static String GAME = "Plansza";
	final static String MENU = "Menu";
	final static String GoToGame = "Przejd¼ do gry.";
	final static String GoToMenu = "Przejd¼ do menu.";
	final static String CREDITS = "O programie";

	private IMainView mainView;

	public Menu(IMainView mainView) {
		this.mainView = mainView;
		// load the background image
		try {
			img = ImageIO.read(new File(
					"src/pl/praktykiatrem/game/battleship/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {
		// menu.setLayout());

		JButton buttonGoToGame = new JButton(new StartGameAction());

		JButton buttonGoToCredits = new JButton(CREDITS);

		buttonGoToCredits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.showCredits();

			}

		});

		buttonGoToCredits.setAlignmentX(100);
		buttonGoToCredits.setAlignmentY(100);

		add(buttonGoToGame);
		add(buttonGoToCredits);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

}
