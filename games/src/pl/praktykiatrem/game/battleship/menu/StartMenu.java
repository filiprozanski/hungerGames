package pl.praktykiatrem.game.battleship.menu;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartMenu {
	private static PlayerStatus player1;
	private static PlayerStatus player2;
	private static Game game;

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

				initialize();
				createAndShowGUI(player1);
				createAndShowGUI(player2);
			}
		});
	}

	private static void createAndShowGUI(PlayerStatus X) {

		JFrame out = new JFrame("Battleships");
		MainView menu = new MainView(game, X);
		out.add(menu);
		out.pack();
		out.setVisible(true);
	}

	private static void initialize() {
		game = new Game();

		int sizeX = game.getBoardSize_H();
		int sizeY = game.getBoardSize_V();
		int[] shipsType = game.getShipTypes();

		player1 = new PlayerStatus(sizeX, sizeY, shipsType);
		player2 = new PlayerStatus(sizeX, sizeY, shipsType);

		player1.setName("Filip");
		player2.setName("Wiktor");

	}
}
