package pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting;

import pl.praktykiatrem.game.battleship.components.Coordinates;

public interface IShootingPlayerController {
	void makeMove(Coordinates coords);

	void resign();

	void endGame();

	void setHint();

	Coordinates[] getCoordsTable(int i);

	void startGame(boolean b, int shipsNumber);
}
