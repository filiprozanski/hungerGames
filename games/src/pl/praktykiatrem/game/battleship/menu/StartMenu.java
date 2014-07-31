package pl.praktykiatrem.game.battleship.menu;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartMenu {
	private static PlayerStatus player1;
	private static PlayerStatus player2;
	private static Game game;
	private static IStageObserver observer;
	private static ShootingController sController;

	public static void main(String[] args) {
		StartMenu startMenu = new StartMenu();
		startMenu.initialize();
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
				createAndShowGUI(player1);
				createAndShowGUI(player2);
			}
		});
	}

	private static void createAndShowGUI(PlayerStatus X) {

		JFrame out = new JFrame("Battleships");
		MainView menu = new MainView(game, X, observer, sController);
		out.add(menu);
		out.setLocationByPlatform(true);
		out.setSize(660, 660);
		out.setResizable(false);
		out.setBackground(new Color(135, 206, 235));
		out.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		out.setVisible(true);

	}

	private void initialize() {
		game = new Game();
		observer = new SettingController(this);

		int sizeX = game.getBoardSize_H();
		int sizeY = game.getBoardSize_V();
		int[] shipsType = game.getShipTypes();

		player1 = new PlayerStatus(sizeX, sizeY, shipsType);
		player2 = new PlayerStatus(sizeX, sizeY, shipsType);

		player1.setName("Filip");
		player2.setName("Wiktor");
		sController = new ShootingController(player1, player2, game);
	}

}
