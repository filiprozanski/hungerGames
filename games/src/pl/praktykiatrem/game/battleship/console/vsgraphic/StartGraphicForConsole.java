package pl.praktykiatrem.game.battleship.console.vsgraphic;

import java.awt.Color;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;
import pl.praktykiatrem.game.battleship.graphic.panels.ShootingPanel;
import pl.praktykiatrem.game.battleship.rules.Game;

public class StartGraphicForConsole {
	private JFrame frame1;
	private PlayerStatus player1;
	private PlayerStatus player2;
	private Game game;
	private ShootingControllerForConsole shController;
	private SettingControllerForConsole seController;

	public StartGraphicForConsole(String name1, String name2, int rulesType) {
		game = new Game(rulesType);
		initialize(name1, name2);
		stageA();
	}

	public void initialize(String name1, String name2) {
		int sizeX = game.getBoardSizeH();
		int sizeY = game.getBoardSizeV();
		int[] shipsType = game.getShipTypes();

		player1 = new PlayerStatus(sizeX, sizeY, shipsType);
		player2 = new PlayerStatus(sizeX, sizeY, shipsType);

		player1.setName(name1);
		player2.setName(name2);

		frame1 = new JFrame(player1.getName());
		frame1.setResizable(false);
		frame1.setBackground(new Color(135, 206, 235));
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void stageA() {
		seController = new SettingControllerForConsole(game, player1, player2,
				this);

		frame1.getContentPane().add((ShipSettingPanel) seController.getView(1));
		frame1.setSize(660, 660);
		frame1.setVisible(true);
	}

	public void stageB() {
		shController = new ShootingControllerForConsole(player1, player2, game,
				this);

		frame1.getContentPane().removeAll();
		frame1.getContentPane().add((ShootingPanel) shController.getView(1));
		frame1.setSize(660, 660);
		frame1.setVisible(true);
	}

	public void changeStage() {
		stageB();
	}

	public void callMenu() {
		frame1.dispose();
		// menuObserver.callMenu();
	}
}
