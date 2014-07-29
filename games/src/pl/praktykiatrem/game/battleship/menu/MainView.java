package pl.praktykiatrem.game.battleship.menu;

/*
 * CardLayoutDemo.java
 *
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.ISettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.SettingPresenter;
import pl.praktykiatrem.game.battleship.graphic.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.panels.IShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

//import pl.praktykiatrem.game.battleship.graphic.BoardGraphicSeting;

public class MainView extends JPanel implements IMainView {

	JPanel cards; // a panel that uses CardLayout
	CardLayout cl;

	private PlayerStatus player;
	private Game game1;

	public MainView(Game game, PlayerStatus x) {
		this.player = x;
		this.game1 = game;
		inicialize();
	}

	public void inicialize() {

		MenuView menuView = new MenuView(this);
		CreditsView creditsView = new CreditsView(this);
		GameOverView gameOverView = new GameOverView(this);
		JPanel game = new JPanel();
		JPanel set = new JPanel();

		ISettingPresenter pres1 = new SettingPresenter(game1, player);
		IShootingPresenter pres2 = new ShootingPresenter(game1, player);

		JButton buttonGoToMenu = new JButton(GoToMenu);
		JButton buttonGoToMenu1 = new JButton(GoToMenu);
		set.add((ShipSettingPanel) pres1.getView());
		set.add(buttonGoToMenu1);

		game.add((ShootingPanel) pres2.getView(), BorderLayout.CENTER);
		game.add(buttonGoToMenu, BorderLayout.PAGE_END);
		game.add(buttonGoToMenu);

		buttonGoToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu();
			}
		});

		buttonGoToMenu1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu();
			}
		});

		cards = new JPanel(new CardLayout());
		cards.add(menuView, MENU);
		cards.add(set, SET);
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
}