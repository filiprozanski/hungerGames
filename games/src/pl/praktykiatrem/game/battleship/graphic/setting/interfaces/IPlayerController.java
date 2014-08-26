package pl.praktykiatrem.game.battleship.graphic.setting.interfaces;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.uniElements.enums.Direction;

public interface IPlayerController extends IController {
	@Override
	void playerIsReady();

	@Override
	void playerIsNotReady();

	@Override
	boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords);

	@Override
	int getActiveShipsNumber(PlayerStatus player);

	@Override
	boolean displaceShip(PlayerStatus player, int id, int polesNumber,
			Direction dir, Coordinates coords);

	@Override
	void resetGame(PlayerStatus player);

	@Override
	public void placeShipAtRandom(ISettingPresenterControll presenter,
			PlayerStatus player);
}
