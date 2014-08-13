package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerForNoPlayer;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerForNoPlayer;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class StartGraphicForNoPlayer {
	private BSPlayerStatus player1;

	private BSPlayerStatus player2;

	private Game game;

	private IShootingController shController;

	private ISettingController seController;

	private IMenuCallObserver menuObserver;

	private Difficulty difficultyLevel;

	/*
	 * public static void main(String[] args) { StartGraphicLocal start = new
	 * StartGraphicLocal();
	 * 
	 * start.initialize(); start.stageA(); }
	 */

	public StartGraphicForNoPlayer(String name1, String name2,
			IMenuCallObserver menuObserver, RulesType rulesType,
			Difficulty difficulty) {
		this.menuObserver = menuObserver;
		this.difficultyLevel = difficulty;
		game = new Game(rulesType);
		initialize(name1, name2);
		stageA();
	}

	public void initialize(String name1, String name2) {
		int sizeX = game.getBoardSizeV();
		int sizeY = game.getBoardSizeH();
		int[] shipsType = game.getShipTypes();

		player1 = new BSPlayerStatus(sizeX, sizeY, shipsType);
		player2 = new BSPlayerStatus(sizeX, sizeY, shipsType);

		player1.setName(name1);
		player2.setName(name2);
	}

	public void stageA() {
		try {
			seController = new SettingControllerForNoPlayer(game, player1,
					player2, this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void stageB() {
		shController = new ShootingControllerForNoPlayer(player1, player2,
				game, this, difficultyLevel);
	}

	public void changeStage() {
		stageB();
	}

	public void callMenu() {
		menuObserver.callMenu();
	}
}
