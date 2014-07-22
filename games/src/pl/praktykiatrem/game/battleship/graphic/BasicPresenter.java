package pl.praktykiatrem.game.battleship.graphic;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public class BasicPresenter implements IBasicPresenter {
	char direction;
	int x;
	int y;
	int polesNumber;
	int id;
	Game gameRules;
	PlayerStatus player;

	public BasicPresenter() {
		gameRules = new Game();
	}

	@Override
	public void shipChoiceDone(int polesNumber, int id) {
		this.polesNumber = polesNumber;
	}

	@Override
	public void putInVerticalDirection(int x, int y) {
		direction = 'v';
		
		if(gameRules.placeShips(player, id, y, direction, x, y))
			
		
	}

	@Override
	public void putInHorizontalDirection(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearLastPlacing(int x, int y) {
		// TODO Auto-generated method stub

	}
}
