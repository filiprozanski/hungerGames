package pl.praktykiatrem.game.battleship.graphic.shipSetting.interfaces;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public interface ISettingController {

	void playerIsReady();

	void playerIsNotReady();

	ArrayList<Coordinates> getCoordsList(PlayerStatus player, int id);

	boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y);

	int getActiveShipsNumber(PlayerStatus player);

	boolean displaceShip(PlayerStatus player, int id, int polesNumber,
			Direction dir, int x, int y);

	void resetGame(PlayerStatus player);

	public void placeShipAtRandom(ISettingPresenterControll presenter,
			PlayerStatus player);

}
