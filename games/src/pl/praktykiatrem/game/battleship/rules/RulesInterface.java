package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.PlayerStatus;

public interface RulesInterface {
	public boolean placeShips(PlayerStatus p, int id, int polesNumber, char direction, int x, int y);
	public boolean makeMove(PlayerStatus p, int x, int y);
}
