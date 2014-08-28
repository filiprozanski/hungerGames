package pl.praktykiatrem.game.battleship.graphic.tabularasa.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.ShootResult;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.uniElements.Player;

public interface IPlayerController {
	Player getPlayer();

	void startGame(boolean b, int shipsNumber);

	void gameOver(boolean isWinner);

	void drawLeftShips(ArrayList<Coordinates> coords);

	ArrayList<Coordinates> getLeftShips();

	void setGameRules(GameRules gameRules);

	ShootResult makeShot(Coordinates coords);

	void setHit(Coordinates coords);

	void setSink();

	void setMiss(Coordinates coords);
}