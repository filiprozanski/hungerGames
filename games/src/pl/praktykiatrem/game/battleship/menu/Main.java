package pl.praktykiatrem.game.battleship.menu;

/*
 * CardLayoutDemo.java
 *
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.praktykiatrem.game.battleship.Controller;
import pl.praktykiatrem.game.battleship.graphic.BoardGraphicSeting;

public class Main extends JPanel implements IMainView {
	JPanel cards; // a panel that uses CardLayout
	final static String GAME = "Plansza";
	final static String MENU = "Menu";
	final static String GoToGame = "Przejd¼ do gry.";
	final static String GoToMenu = "Przejd¼ do menu.";
	final static String CREDITS = "O programie";
	private Image img;
	CardLayout cl;

	public Main() {
		inicialize();
	}

	public void inicialize() {
		Controller controller = new Controller();
		MenuView menuView = new MenuView(this);
		Background credits = new Background("doge.jpg");
		JPanel game = new JPanel();
		BoardGraphicSeting board = new BoardGraphicSeting(controller);

		JButton buttonGoToMenu = new JButton(GoToMenu);
		game.add(board, BorderLayout.CENTER);
		game.add(buttonGoToMenu, BorderLayout.PAGE_END);

		cards = new JPanel(new CardLayout());
		cards.add(menuView, MENU);
		cards.add(game, GAME);
		cards.add(credits, CREDITS);

		add(cards, BorderLayout.CENTER);
	}

	private static void createAndShowGUI() {
		JFrame out = new JFrame("Battleships");
		Main demo = new Main();
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
		/* Turn off metal's use of bold fonts */
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
}