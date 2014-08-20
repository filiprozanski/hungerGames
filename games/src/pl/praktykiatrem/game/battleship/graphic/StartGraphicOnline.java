package pl.praktykiatrem.game.battleship.graphic;

import java.io.Serializable;
import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerOffline;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rmi.RMIClient;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

public class StartGraphicOnline implements Serializable, IShootingController {
	private static final long serialVersionUID = 1604629930082397823L;

	private BSPlayerStatus player;

	private Game game;

	private ShootingPresenter shPresenter;

	private SettingControllerOffline seController;

	private IMenuCallObserver menuObserver;

	private RMIClient client;

	public StartGraphicOnline(String name) throws RemoteException {
		System.out.println("Klient Start");

		client = new RMIClient(this);

		initialize(name);

		stageA();
	}

	public void initialize(String name) {
		try {
			game = client.getGame();
		} catch (RemoteException e) {
			System.out.println("getGame");
			e.printStackTrace();
		}

		int sizeX = game.getBoardSizeV();
		int sizeY = game.getBoardSizeH();
		int[] shipsType = game.getShipTypes();

		player = new BSPlayerStatus(sizeX, sizeY, shipsType);
		player.setName(name);
	}

	public void stageA() {
		seController = new SettingControllerOffline(game, player, this);
	}

	public void stageB() {
		// shController = new ShootingController(player, game, this);
	}

	@Override
	public void changeStage() {
		try {
			seController.closeSettingStage();
		} catch (RemoteException e) {
			System.out.println("closeSetting");
			e.printStackTrace();
		}
	}

	@Override
	public void callMenu() {
		menuObserver.callMenu();
	}

	public void playerIsReady() {
		try {
			server.setPlayer(this);
		} catch (RemoteException e) {
			System.out.println("setReady");
			e.printStackTrace();
		}
	}

	public BSPlayerStatus getPlayer() {
		return player;
	}

	@Override
	public boolean makeMove(PlayerStatus player, int x, int y) {
		return server.makeMove(player, x, y);
	}

	@Override
	public void resign(PlayerStatus player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHint() {
		// TODO Auto-generated method stub

	}
}