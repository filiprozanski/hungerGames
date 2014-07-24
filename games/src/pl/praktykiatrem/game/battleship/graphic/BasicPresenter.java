package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Direction;
import pl.praktykiatrem.game.battleship.rules.Game;

public class BasicPresenter implements IBasicPresenter {
	int x;
	int y;
	int polesNumber;
	int id;
	Game gameRules;
	PlayerStatus player;

	public BasicPresenter(Game gameRules) {
		this.gameRules = gameRules;
	}

	@Override
	public void shipChoiceDone(int polesNumber, int id) {
		this.polesNumber = polesNumber;
	}

	@Override
	public void placeShip(int x, int y, int freq) {
		switch (freq) {
		case 1:
			putInHorizontalDirection(x, y);
			break;
		case 2:
			clearLastChoice(x, y, polesNumber, Direction.HORIZONTAL);
			putInVerticalDirection(x, y);
			break;
		case 0:
			clearLastChoice(x, y, polesNumber, Direction.VERTICAL);
			break;
		}
	}

	private void putInVerticalDirection(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.VERTICAL,
				x, y))
			;

	}

	private void putInHorizontalDirection(int x, int y) {
		if (gameRules.placeShips(player, id, polesNumber, Direction.HORIZONTAL,
				x, y))
			;

	}

	public void clearLastChoice(int x, int y, int polesNumber, Direction dir) {
		// TODO Auto-generated method stub

	}
}
