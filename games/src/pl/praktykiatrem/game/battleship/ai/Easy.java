package pl.praktykiatrem.game.battleship.ai;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.rules.GameRules;
import pl.praktykiatrem.game.battleship.rules.Rand;

public class Easy implements IComputer {
	private ComputerBoard board;
	private GameRules game;
	private Coordinates coords;

	public Easy(GameRules game) {
		this.game = game;
		this.board = new ComputerBoard(game);
	}

	@Override
	public Coordinates getCords() {
		randShot();
		return coords;
	}

	private void randShot() {
		int x_temp = 0;
		int y_temp = 0;
		while (true) {
			x_temp = Rand.getRandX(game);
			y_temp = Rand.getRandY(game);
			// if ((x_temp % 2 == 1 && y_temp % 2 == 1)
			// || (x_temp % 2 == 0 && y_temp % 2 == 0))
			if (board.getBoard(new Coordinates(x_temp, y_temp)) == -1) {
				this.coords = new Coordinates(x_temp, y_temp);
				break;
			}
		}
	}

	@Override
	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			board.setMiss(new Coordinates(arrayList.get(i).getX(), arrayList
					.get(i).getY()));
		}
	}

	@Override
	public void setHit(Coordinates coords) {
		board.setMiss(coords);
	}

	@Override
	public void setMiss(Coordinates coords) {
		board.setMiss(coords);
	}
}