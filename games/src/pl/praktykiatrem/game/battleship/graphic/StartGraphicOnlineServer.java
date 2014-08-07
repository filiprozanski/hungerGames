package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerOnline;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingControllerOnline;
import pl.praktykiatrem.game.battleship.rmi.IRMIClient;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;

public class StartGraphicOnlineServer {
	private PlayerStatus player1;

	private PlayerStatus player2;

	private Game game;

	private ShootingControllerOnline shController;

	private SettingControllerOnline seController;

	private IMenuCallObserver menuObserver;

	public StartGraphicOnlineServer(String name1, String name2, int rulesType,
			int portNumber1, int portNumber2) {
		game = new Game(rulesType);
		initialize(name1, name2);

		IRMIClient c1 =

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
		seController = new SettingControllerOnline(game, player1, player2, this);
	}

	public void stageB() {
		shController = new ShootingControllerOnline(player1, player2, game,
				this);
	}

	public void changeStage() {
		stageB();
	}

	public void callMenu() {
		menuObserver.callMenu();
	}
}
