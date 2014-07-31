package pl.praktykiatrem.game.battleship.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class MainView extends JPanel implements IMainView {

	JPanel cards; // a panel that uses CardLayout
	CardLayout cl;

	private PlayerStatus player;
	private Game game1;
	private static PlayCard game;
	private IStageObserver observer;
	private ShootingController sController;

	public MainView(Game game, PlayerStatus x, IStageObserver observer,
			ShootingController sController) {
		this.player = x;
		this.game1 = game;
		this.observer = observer;
		this.sController = sController;
		inicialize();
	}

	public void inicialize() {

		MenuView menuView = new MenuView(this);
		CreditsView creditsView = new CreditsView(this);
		GameOverView gameOverView = new GameOverView(this);
		game = new PlayCard(this, game1, player, observer, sController);
		// JPanel set = new JPanel();

		JButton buttonGoToMenu = new JButton(GoToMenu);
		JButton buttonGoToMenu1 = new JButton(GoToMenu);
		// set.add((ShipSettingPanel) pres1.getView());
		// set.add(buttonGoToMenu1);

		buttonGoToMenu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu();
			}
		});

		cards = new JPanel(new CardLayout());
		cards.add(menuView, MENU);
		// cards.add(set, SET);
		cards.add(game, GAME);
		cards.add(gameOverView, GAMEOVER);
		cards.add(creditsView, CREDITS);

		add(cards, BorderLayout.CENTER);
	}

	@Override
	public void startGame() {
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, GAME);
	}

	public void showSet() {
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, SET);
	}

	@Override
	public void showCredits() {
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, CREDITS);
	}

	@Override
	public void showMenu() {
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, MENU);
	}

	public void showGameOver() {
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, GAMEOVER);
	}

	public static void changeStage() {
		game.play();
	}
}
