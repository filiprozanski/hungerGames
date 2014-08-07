package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;

public class StartGraphicForOnePlayer {

	private JFrame frame1;

	private JFrame frame2;

	private PlayerStatus player1;

	private PlayerStatus player2;

	private Game game;

	private IShootingController shController;

	private ISettingController seController;

	private IMenuCallObserver menuObserver;

	private int difficultyLevel;

	/*
	 * public static void main(String[] args) { StartGraphicLocal start = new
	 * StartGraphicLocal();
	 * 
	 * start.initialize(); start.stageA(); }
	 */

	public StartGraphicForOnePlayer(String name1, String name2,
			IMenuCallObserver menuObserver, int rulesType, int difficulty) {
		this.menuObserver = menuObserver;
		this.difficultyLevel = difficulty;
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
	}

	public void stageA() {
		try {
			seController = new SettingControllerForOnePlayer(game, player1,
					player2, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stageB() {
		shController = new ShootingControllerForOnePlayer(player1, player2,
				game, this, difficultyLevel);
	}

	public void changeStage() {
		stageB();
	}

	public void callMenu() {
		menuObserver.callMenu();
	}
}
