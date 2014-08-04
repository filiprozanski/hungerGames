package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;
import java.util.List;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;

public class Computer {
	private ComputerBoard board;
	private Game gameRules;
	private Coordinates coords;
	private List<Coordinates> list = new ArrayList<Coordinates>();

	public Computer(Game gameRules) {
		this.gameRules = gameRules;
		this.board = new ComputerBoard(gameRules);
	}

	public Coordinates getCords() {
		computeShot();
		return coords;
	}

	private void computeShot() {
		if (list.size() > 0) {
			coords = list.get(0);
			board.setHit(coords.getX(), coords.getY());
			list.remove(0);
		} else {
			randShot();
		}
	}

	private void randShot() {
		int x_temp = 0;
		int y_temp = 0;
		while (true) {
			board.printBoard();
			x_temp = Rand.getRandX(gameRules);
			y_temp = Rand.getRandY(gameRules);
			System.out.print(x_temp);
			System.out.print(" ");
			System.out.println(y_temp);
			if (board.getBoard(x_temp, y_temp) == -1) {
				board.setHit(x_temp, y_temp);
				this.coords = new Coordinates(x_temp, y_temp);
				break;
			}
		}
	}

	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++)
			board.setSink(id, arrayList.get(i).getX(), arrayList.get(i).getY());
	}

	public void setHit(int x, int y) {
		if (x - 1 >= 0 && board.getBoard(x - 1, y) == -1)
			list.add(new Coordinates(x - 1, y));
		if (y - 1 >= 0 && board.getBoard(x, y - 1) == -1)
			list.add(new Coordinates(x, y - 1));
		if (x + 1 < gameRules.getBoardSizeH() && board.getBoard(x + 1, y) == -1)
			list.add(new Coordinates(x + 1, y));
		if (y + 1 < gameRules.getBoardSizeV() && board.getBoard(x, y + 1) == -1)
			list.add(new Coordinates(x, y + 1));
	}
}
