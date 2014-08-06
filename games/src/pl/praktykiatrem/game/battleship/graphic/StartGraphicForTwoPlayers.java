package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerForTwoPlayers;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerForTwoPlayers;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;

public class StartGraphicForTwoPlayers {

	private JFrame frame1;

	private JFrame frame2;

	private PlayerStatus player1;

	private PlayerStatus player2;

	private Game game;

	private ShootingControllerForTwoPlayers shController;

	private SettingControllerForTwoPlayers seController;

	private IMenuCallObserver menuObserver;

	/*
	 * public static void main(String[] args) { StartGraphicLocal start = new
	 * StartGraphicLocal();
	 * 
	 * start.initialize(); start.stageA(); }
	 */

	public StartGraphicForTwoPlayers(String name1, String name2,
			IMenuCallObserver menuObserver, int rulesType) {
		this.menuObserver = menuObserver;
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
		seController = new SettingControllerForTwoPlayers(game, player1,
				player2, this);
	}

	public void stageB() {
		shController = new ShootingControllerForTwoPlayers(player1, player2,
				game, this);
	}

	public void changeStage() {
		stageB();
	}

	public void callMenu() {
		menuObserver.callMenu();
	}
}
