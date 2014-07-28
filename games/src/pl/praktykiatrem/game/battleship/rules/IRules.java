package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public interface IRules {

	public boolean getCurrentPlayer();

	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y);

	public boolean makeMove(PlayerStatus p, int x, int y);

	public boolean displaceShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y);

	public int getBoardSize_H();

	public int getBoardSize_V();

	public int[] getShipTypes();
}
