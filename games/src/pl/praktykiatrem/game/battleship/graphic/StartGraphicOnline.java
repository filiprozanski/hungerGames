package pl.praktykiatrem.game.battleship.graphic;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.SettingControllerOffline;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rmi.RMIClient;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.menu.IMenuCallObserver;

public class StartGraphicOnline implements Serializable, IShootingController {
	private static final long serialVersionUID = 1604629930082397823L;

	private PlayerStatus player;

	private Game game;

	private ShootingPresenter shPresenter;

	private SettingControllerOffline seController;

	private IMenuCallObserver menuObserver;

	private RMIClient client;

	public StartGraphicOnline(String name, IMenuCallObserver menuObserver)
			throws RemoteException {
		this.menuObserver = menuObserver;

		try {
			client = new RMIClient(this);
		} catch (NotBoundException e) {
			System.out.println("Client creating");
			e.printStackTrace();
		}

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

		player = new PlayerStatus(sizeX, sizeY, shipsType);
		player.setName(name);
	}

	public void stageA() {
		seController = new SettingControllerOffline(game, player, this);
	}

	public void stageB() {
		// shController = new ShootingController(player, game, this);
	}

	public void changeStage(boolean start) {
		try {
			seController.closeSettingStage();
			startShootingStage(start);
		} catch (RemoteException e) {
			System.out.println("closeSetting");
			e.printStackTrace();
		}
	}

	@Override
	public void endGame() {
		menuObserver.callMenu();
	}

	public void playerIsReady() {
		try {
			client.setPlayer(player);
		} catch (RemoteException e) {
			System.out.println("setReady");
			e.printStackTrace();
		}
	}

	public PlayerStatus getPlayer() {
		return player;
	}

	@Override
	public boolean makeMove(PlayerStatus player, int x, int y) {
		try {
			return client.makeMove(player, x, y);
		} catch (RemoteException e) {
			System.out.println("makeMove");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void resign(PlayerStatus player) {
		try {
			client.resign(player);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setHint() {
		// TODO Auto-generated method stub
	}

	public void hitSetting(int x, int y, int playerShips, int enemyShips,
			int accuracy) {
		shPresenter.setStats(playerShips, enemyShips, accuracy);
		shPresenter.changeBattlePlaceIcon(x, y, 2);

	}

	public void losePoleSetting(int x, int y, int playerShips, int enemyShips) {
		shPresenter.setStats(playerShips, enemyShips);
		shPresenter.changeStateIcon(x, y, 0);
	}

	public void shipSunkSetting(Coordinates[] list, int id) {
		shPresenter.changeShipState(id);
		shPresenter.drawShip(list);
	}

	public void missSetting(int playerShips, int x, int y, int enemyShips,
			int accuracy) {
		shPresenter.changeStatus(false);
		shPresenter.changeBattlePlaceIcon(x, y, 1);
		shPresenter.setStats(playerShips, enemyShips, accuracy);

	}

	private void startShootingStage(boolean start) {
		shPresenter = new ShootingPresenter(game, player, this, start);
		shPresenter.setStats(game.getShipsNumber(), game.getShipsNumber());
		shPresenter.showFrame();
	}

	public void allowToMove(int x, int y) {
		shPresenter.changeStateIcon(x, y, 1);
		shPresenter.changeStatus(true);
	}

	public void gameOver(boolean isWinner) {
		shPresenter.gameOver(isWinner);
		shPresenter.changeGiveUpButtonLabel();
	}

	public void drawLeftShips(ArrayList<Place> leftShips) {
		for (Place place : leftShips) {
			shPresenter.fchangeIcon(place.getX(), place.getY(),
					place.getShipId() + 1);
		}
	}
}
