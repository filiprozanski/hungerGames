package pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

public interface ISettingController {

	void playerIsReady();

	void playerIsNotReady();

	ArrayList<Coordinates> getCoordsList(BSPlayerStatus player, int id);

	boolean placeShips(BSPlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y);

	int getActiveShipsNumber(BSPlayerStatus player);

	boolean displaceShip(BSPlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y);

	void resetGame(PlayerStatus player);

	public void placeShipAtRandom(ISettingPresenterControll presenter,
			BSPlayerStatus player);

}
