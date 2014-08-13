package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.rmi.RemoteException;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnlineServer;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class SettingControllerOnline implements ISettingController {
	private int readyPlayers;
	private Game gameRules;
	private StartGraphicOnlineServer supervisor;
	private ISettingPresenterControll pres1;
	private ISettingPresenterControll pres2;

	public SettingControllerOnline(Game g, BSPlayerStatus player1,
			BSPlayerStatus player2, StartGraphicOnlineServer supervisor) {
		this.supervisor = supervisor;
		this.gameRules = g;

		try {
			pres1 = new SettingPresenter(g.getConstants(), player1, this);
			pres2 = new SettingPresenter(g.getConstants(), player2, this);
			pres1.showFrame();
			pres2.showFrame();
		} catch (RemoteException e) {
			System.out.println("controllerset constructor");
			e.printStackTrace();
			System.exit(0);
		}

		readyPlayers = 0;
	}

	/*
	 * public ISettingView getView(int p) { switch (p) { case 1: return
	 * pres1.getView(); case 2: return pres2.getView(); default: return null; }
	 * }
	 */

	@Override
	public void playerIsReady() {
		readyPlayers++;
		if (readyPlayers == 2) {
			try {
				pres1.closeFrame();
				pres2.closeFrame();
			} catch (RemoteException e) {
				System.out.println("player is ready");
				e.printStackTrace();
				System.exit(0);
			}
			supervisor.changeStage();
		}
	}

	@Override
	public void playerIsNotReady() {
		readyPlayers = 0;
	}

	@Override
	public ArrayList<Coordinates> getCoordsList(BSPlayerStatus player, int id) {
		return gameRules.getCoordsList(player, id);
	}

	@Override
	public boolean placeShips(BSPlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y) {
		return gameRules.placeShips(player, id, polesNumber, dir, x, y);
	}

	@Override
	public int getActiveShipsNumber(BSPlayerStatus player) {
		return gameRules.getActiveShipsNumber(player);
	}

	@Override
	public boolean displaceShip(BSPlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y) {
		return gameRules.displaceShips(player, id, polesNumber, dir, x, y);
	}

	@Override
	public void resetGame(PlayerStatus player) {
		gameRules.resetGame(player);
	}

	@Override
	public void placeShipAtRandom(ISettingPresenterControll presenter,
			BSPlayerStatus player) {
		Direction rand_dir;
		int randX;
		int randY;
		int polesNumber;
		try {
			presenter.resetBoard();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			polesNumber = gameRules.getShipTypes()[i];
			rand_dir = Rand.getRandDirection();
			while (true) {
				randX = Rand.getRandX(gameRules);
				randY = Rand.getRandY(gameRules);
				if (placeShips(player, i, polesNumber, rand_dir, randX, randY)) {
					try {
						presenter.placeShipsOnView(randX, randY, rand_dir, i,
								polesNumber);
					} catch (RemoteException e) {
						System.out.println("placeShipAtRandom");
						e.printStackTrace();
						System.exit(0);
					}
					break;
				}
			}
		}
	}
}
