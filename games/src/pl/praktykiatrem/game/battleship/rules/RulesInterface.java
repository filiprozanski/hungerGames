package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public interface RulesInterface {

	public boolean getCurrentPlayer();

	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y);

	public boolean makeMove(PlayerStatus p, int x, int y);

	public int getBoardSize_X();

	public int getBoardSize_Y();

	public int getShipsNumber();

	public int[] getShipTypes();
}
