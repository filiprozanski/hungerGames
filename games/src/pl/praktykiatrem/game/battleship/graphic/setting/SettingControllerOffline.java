package pl.praktykiatrem.game.battleship.graphic.setting;

import java.rmi.RemoteException;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnline;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class SettingControllerOffline implements ISettingController {
	private int readyPlayers;
	private GameRules gameRules;
	private StartGraphicOnline supervisor;
	private ISettingPresenterControll pres;

	public SettingControllerOffline(GameRules g, PlayerStatus player,
			StartGraphicOnline supervisor) {
		this.supervisor = supervisor;
		this.gameRules = g;

		pres = new SwingPresenter(g.getConstants(), player, this);
		pres.showFrame();

		readyPlayers = 0;
	}

	@Override
	public void playerIsReady() {
		supervisor.playerIsReady();
	}

	public void closeSettingStage() throws RemoteException {
		pres.closeFrame();
	}

	@Override
	public void playerIsNotReady() {
		readyPlayers = 0;
	}

	@Override
	public boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords) {
		return gameRules.placeShips(player, id, polesNumber, dir, coords);
	}

	@Override
	public int getActiveShipsNumber(PlayerStatus player) {
		return gameRules.getActiveShipsNumber(player);
	}

	@Override
	public boolean displaceShip(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords) {
		return gameRules.displaceShips(player, id, polesNumber, dir, coords);
	}

	@Override
	public void resetGame(PlayerStatus player) {
		gameRules.resetGame(player);
	}

	@Override
	public void placeShipAtRandom(ISettingPresenterControll presenter,
			PlayerStatus player) {
		Direction rand_dir;
		int randX;
		int randY;
		int polesNumber;
		presenter.resetBoard();

		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			polesNumber = gameRules.getShipTypes()[i];
			rand_dir = Rand.getRandDirection();
			while (true) {
				randX = Rand.getRandX(gameRules);
				randY = Rand.getRandY(gameRules);
				if (placeShips(player, i, polesNumber, rand_dir,
						new Coordinates(randX, randY))) {

					presenter.placeShipsOnView(randX, randY, rand_dir, i,
							polesNumber);

					break;
				}
			}
		}
	}
}
