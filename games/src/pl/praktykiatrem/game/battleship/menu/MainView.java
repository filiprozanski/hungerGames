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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.praktykiatrem.game.battleship.Controller;
//import pl.praktykiatrem.game.battleship.graphic.BoardGraphicSeting;
import pl.praktykiatrem.game.battleship.graphic.BoardGraphicSeting;

public class MainView extends JPanel implements IMainView {
	JPanel cards; // a panel that uses CardLayout
	CardLayout cl;

	public MainView() {
		inicialize();
	}

	public void inicialize() {
		Controller controller = new Controller();
		MenuView menuView = new MenuView(this);
		CreditsView creditsView = new CreditsView(this);
		GameOverView gameOverView = new GameOverView(this);
		JPanel game = new JPanel();
		BoardGraphicSeting board = new BoardGraphicSeting(controller);

		JButton buttonGoToMenu = new JButton(GoToMenu);
		game.add(board, BorderLayout.CENTER);
		game.add(buttonGoToMenu, BorderLayout.PAGE_END);

		buttonGoToMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu();
			}
		});

		cards = new JPanel(new CardLayout());
		cards.add(menuView, MENU);
		cards.add(game, GAME);
		cards.add(gameOverView, GAMEOVER);
		cards.add(creditsView, CREDITS);

		add(cards, BorderLayout.CENTER);
	}

	private static void createAndShowGUI() {
		JFrame out = new JFrame("Battleships");
		MainView demo = new MainView();
		out.add(demo);
		out.pack();
		out.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		UIManager.put("swing.boldMetal", Boolean.FALSE);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void startGame() {
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, GAME);
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