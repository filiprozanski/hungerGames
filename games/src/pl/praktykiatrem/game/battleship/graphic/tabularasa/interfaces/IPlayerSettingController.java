package pl.praktykiatrem.game.battleship.graphic.tabularasa.interfaces;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.setting.interfaces.ISettingController;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public interface IPlayerSettingController extends ISettingController {
	@Override
	void playerIsReady();

	@Override
	void playerIsNotReady();

	boolean placeShips(int id, int polesNumber, Direction dir,
			Coordinates coords);

	int getActiveShipsNumber();

	boolean displaceShip(int id, int polesNumber, Direction dir,
			Coordinates coords);

	void resetGame();

	public void placeShipAtRandom();
}
