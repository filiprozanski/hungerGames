package pl.praktykiatrem.game.battleship;

/*
 * CardLayoutDemo.java
 *
 */
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.praktykiatrem.game.battleship.graphic.panels.BoardGraphicSeting;

public class Main extends JPanel implements ActionListener, IMainView {
	JPanel cards; // a panel that uses CardLayout
	final static String GAME = "Plansza";
	final static String MENU = "Menu";
	final static String GoToGame = "Przejd¼ do gry.";
	final static String GoToMenu = "Przejd¼ do menu.";
	final static String CREDITS = "O programie";
	private Image img;

	public Main() {
		inicialize();
	}

	public void inicialize() {
		Controller controller = new Controller();
		Menu Menu = new Menu(this);
		Background credits = new Background("doge.jpg");
		JPanel game = new JPanel();
		BoardGraphicSeting board = new BoardGraphicSeting(controller);

		// menu.setLayout());

		JButton buttonGoToGame = new JButton(GoToGame);
		JButton buttonGoToMenu = new JButton(GoToMenu);
		JButton buttonGoToCredits = new JButton(CREDITS);

		buttonGoToGame.addActionListener(this);
		buttonGoToMenu.addActionListener(this);
		buttonGoToCredits.addActionListener(this);

		buttonGoToCredits.setAlignmentX(100);
		buttonGoToCredits.setAlignmentY(100);

		buttonGoToGame.setAlignmentX(Component.CENTER_ALIGNMENT);

		game.add(board, BorderLayout.CENTER);
		game.add(buttonGoToMenu, BorderLayout.PAGE_END);

		// Create the panel that contains the "cards".
		cards = new JPanel(new CardLayout());
		cards.add(Menu, MENU);
		cards.add(game, GAME);
		cards.add(credits, CREDITS);

		// pane.add(comboBoxPane, BorderLayout.PAGE_START);
		add(cards, BorderLayout.CENTER);
	}

	public void itemStateChanged(ItemEvent evt) {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, (String) evt.getItem());

	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame out = new JFrame("Battleships");
		Main demo = new Main();
		out.add(demo);
		// Display the window.
		out.pack();
		out.setVisible(true);
	}

	public static void main(String[] args) {
		/* Use an appropriate Look and Feel */
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

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	@Override
	public void startGame() {
		cl.show(cards, GAME);

	}

	@Override
	public void showCredits() {
		cl.show(cards, CREDITS);

	}
}