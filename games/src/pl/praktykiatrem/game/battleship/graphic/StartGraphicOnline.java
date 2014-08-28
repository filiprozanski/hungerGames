package pl.praktykiatrem.game.battleship.graphic;

import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.Place;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.density.HintController;
import pl.praktykiatrem.game.battleship.graphic.setting.SettingControllerOffline;
import pl.praktykiatrem.game.battleship.graphic.shooting.ShootingPresenter;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingController;
import pl.praktykiatrem.game.battleship.rmi.RMIClient;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.menu.CloseStatus;
import pl.praktykiatrem.game.menu.IMenuCallObserver;

public class StartGraphicOnline implements Serializable, IShootingController {
	private static final long serialVersionUID = 1604629930082397823L;

	private PlayerStatus player;

	private GameRules game;

	private ShootingPresenter shPresenter;

	private SettingControllerOffline seController;

	private IMenuCallObserver menuObserver;

	private RMIClient client;

	private HintController hint;

	public StartGraphicOnline(String name, IMenuCallObserver menuObserver) {
		this.menuObserver = menuObserver;

		try {
			client = new RMIClient(this);
		} catch (NotBoundException | RemoteException e) {
			System.out.println("Client creating");
			connectionErrorOccured();
			return;
		}

		initialize(name);

		hint = new HintController(game);

		stageA();
	}

	public void initialize(String name) {
		try {
			game = client.getGame();
		} catch (RemoteException e) {
			System.out.println("getGame");
			connectionErrorOccured();
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

	public void changeStage(boolean start) {
		try {
			seController.closeSettingStage();
			startShootingStage(start);
		} catch (RemoteException e) {
			System.out.println("closeSetting");
			connectionErrorOccured();
			e.printStackTrace();
		}
	}

	@Override
	public void endGame() {
		menuObserver.callMenu(CloseStatus.NORMAL);
	}

	public void connectionErrorOccured() {
		menuObserver.callMenu(CloseStatus.CONNECTION_ERROR);
	}

	public void playerIsReady() {
		try {
			client.setPlayer(player);
		} catch (RemoteException e) {
			System.out.println("setReady");
			connectionErrorOccured();
			e.printStackTrace();
		}
	}

	public PlayerStatus getPlayer() {
		return player;
	}

	@Override
	public boolean makeMove(PlayerStatus player, Coordinates coords) {
		try {
			return client.makeMove(player, coords);
		} catch (RemoteException e) {
			System.out.println("makeMove");
			connectionErrorOccured();
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void resign(PlayerStatus player) {
		try {
			client.resign(player);
		} catch (RemoteException e) {
			System.out.println("resign");
			connectionErrorOccured();
			e.printStackTrace();
		}
	}

	@Override
	public void setHint() {
		hint.setHint(!hint.isHintOn());
	}

	public void hitSetting(Coordinates coords, int playerShips, int enemyShips,
			int accuracy) {
		shPresenter.setStats(playerShips, enemyShips, accuracy);
		shPresenter.changeBattlePlaceIcon(coords, 2);
		hint.setHit(coords);
	}

	public void losePoleSetting(Coordinates coords, int playerShips,
			int enemyShips) {
		shPresenter.setStats(playerShips, enemyShips);
		shPresenter.changeStateIcon(coords, 0);
	}

	public void shipSunkSetting(Coordinates[] list, int id, int eNumber,
			int pNumber, int accuracy) {
		shPresenter.setStats(pNumber, eNumber, accuracy);
		shPresenter.changeShipState(id);
		shPresenter.drawShip(list);
		hint.setSink(id, new ArrayList<Coordinates>(Arrays.asList(list)));
	}

	public void missSetting(int playerShips, Coordinates coords,
			int enemyShips, int accuracy) {
		shPresenter.changeStatus(false);
		shPresenter.changeBattlePlaceIcon(coords, 1);
		shPresenter.setStats(playerShips, enemyShips, accuracy);
		hint.setMiss(coords);

	}

	private void startShootingStage(boolean start) {
		shPresenter = new ShootingPresenter(game, player, this, start);
		shPresenter.setStats(game.getShipsNumber(), game.getShipsNumber());
		shPresenter.showFrame();
	}

	public void allowToMove(Coordinates coords) {
		shPresenter.changeStateIcon(coords, 1);
		shPresenter.changeStatus(true);
	}

	public void gameOver(boolean isWinner) {
		shPresenter.gameOver(isWinner);
		shPresenter.changeGiveUpButtonLabel();
	}

	public void drawLeftShips(ArrayList<Place> leftShips) {
		for (Place place : leftShips) {
			shPresenter.fchangeIcon(
					new Coordinates(place.getX(), place.getY()),
					place.getShipId() + 1);
		}
	}
}
