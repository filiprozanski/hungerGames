package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.uniElements.Player;

public interface IShootingController {

	void makeMove(Coordinates coords, Player player);

	void resign(Player player);

	void endGame();
}
