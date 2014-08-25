package pl.praktykiatrem.game.battleship.graphic.setting.interfaces;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public interface ISettingController {

	void playerIsReady();

	void playerIsNotReady();

	ArrayList<Coordinates> getCoordsList(PlayerStatus player, int id);

	boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords);

	int getActiveShipsNumber(PlayerStatus player);

	boolean displaceShip(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords);

	void resetGame(PlayerStatus player);

	public void placeShipAtRandom(ISettingPresenterControll presenter,
			PlayerStatus player);

}
