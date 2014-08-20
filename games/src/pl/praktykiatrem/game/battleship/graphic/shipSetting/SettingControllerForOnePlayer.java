package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.rmi.RemoteException;
import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class SettingControllerForOnePlayer implements ISettingController {
	private int readyPlayers;
	private Game gameRules;
	private StartGraphicForOnePlayer supervisor;
	private ISettingPresenterControll pres1;
	private ISettingPresenterControll pres2;

	public SettingControllerForOnePlayer(Game g, BSPlayerStatus player1,
			BSPlayerStatus player2, StartGraphicForOnePlayer supervisor)
			throws RemoteException {
		this.supervisor = supervisor;
		this.gameRules = g;

		pres1 = new SettingPresenter(g.getConstants(), player1, this);
		pres2 = new SettingPresenter(g.getConstants(), player2, this, 1);

		pres1.showFrame();

		readyPlayers = 0;
	}

	@Override
	public void playerIsReady() {
		pres1.closeFrame();

		supervisor.changeStage();
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
		presenter.resetBoard();

		for (int i = 0; i < gameRules.getShipsNumber(); i++) {
			polesNumber = gameRules.getShipTypes()[i];
			rand_dir = Rand.getRandDirection();
			while (true) {
				randX = Rand.getRandX(gameRules);
				randY = Rand.getRandY(gameRules);
				if (placeShips(player, i, polesNumber, rand_dir, randX, randY)) {
					presenter.placeShipsOnView(randX, randY, rand_dir, i,
							polesNumber);

					break;
				}
			}
		}
	}

}
