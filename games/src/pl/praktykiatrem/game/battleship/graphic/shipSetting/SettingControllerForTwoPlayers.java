package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForTwoPlayers;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingController;
import pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces.ISettingPresenterControll;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public class SettingControllerForTwoPlayers implements ISettingController {
	private int readyPlayers;
	private Game gameRules;
	private StartGraphicForTwoPlayers supervisor;
	private ISettingPresenterControll pres1;
	private ISettingPresenterControll pres2;

	public SettingControllerForTwoPlayers(Game g, PlayerStatus player1,
			PlayerStatus player2, StartGraphicForTwoPlayers supervisor) {
		this.supervisor = supervisor;
		this.gameRules = g;
		pres1 = new SettingPresenter(g.getConstants(), player1, this);
		pres2 = new SettingPresenter(g.getConstants(), player2, this);

		pres1.showFrame();
		pres2.showFrame();

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
			pres1.closeFrame();
			pres2.closeFrame();

			supervisor.changeStage();
		}
	}

	@Override
	public void playerIsNotReady() {
		readyPlayers = 0;
	}

	@Override
	public ArrayList<Coordinates> getCoordsList(PlayerStatus player, int id) {
		return gameRules.getCoordsList(player, id);
	}

	@Override
	public boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y) {
		return gameRules.placeShips(player, id, polesNumber, dir, x, y);
	}

	@Override
	public int getActiveShipsNumber(PlayerStatus player) {
		return gameRules.getActiveShipsNumber(player);
	}

	@Override
	public boolean displaceShip(PlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y) {
		return gameRules.displaceShips(player, id, polesNumber, dir, x, y);
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
				if (placeShips(player, i, polesNumber, rand_dir, randX, randY)) {

					presenter.placeShipsOnView(randX, randY, rand_dir, i,
							polesNumber);

					break;
				}
			}
		}
	}
}
