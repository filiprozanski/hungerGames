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
		System.out.print(coords.getX());
		System.out.print(" ");
		System.out.println(coords.getY());
		board.printBoard();
		board.setMiss(coords.getX(), coords.getY());
		removeFromList(coords);
		return coords;
	}

	private void computeShot() {
		lookForDoubles();
		shotFromList();
	}

	private void shotFromList() {
		if (list.size() > 0) {
			coords = list.get(0);
			board.setMiss(coords.getX(), coords.getY());
			list.remove(0);
		} else {
			randShot();
		}
	}

	private void removeFromList(Coordinates coords) {
		list.remove(coords);
	}

	private void randShot() {
		int x_temp = 0;
		int y_temp = 0;
		while (true) {

			x_temp = Rand.getRandX(gameRules);
			y_temp = Rand.getRandY(gameRules);

			if (board.getBoard(x_temp, y_temp) == -2) {
				// board.setMiss(x_temp, y_temp);
				this.coords = new Coordinates(x_temp, y_temp);
				break;
			}
		}
	}

	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++)
			board.setSink(id + 1, arrayList.get(i).getX(), arrayList.get(i)
					.getY());
		list.clear();
	}

	public void setHit(int x, int y) {
		board.setHit(x, y);
		lookForNeighbors();
	}

	public void setMiss(int x, int y) {
		board.setMiss(x, y);
	}

	private void lookForDoubles() {
		for (int i = 0; i < gameRules.getBoardSizeH() - 1; i++) {
			for (int j = 0; j < gameRules.getBoardSizeV() - 1; j++) {
				if (board.getBoard()[j][i] == 0
						&& board.getBoard()[j + 1][i] == 0) {
					addToList(i, j - 1);
					addToList(i, j + 2);
				} else if (board.getBoard()[j][i] == 0
						&& board.getBoard()[j][i + 1] == 0) {
					addToList(i - 1, j);
					addToList(i + 2, j);
				}
			}
		}
	}

	private void addToList(int x, int y) {
		if (x >= 0 && y >= 0 && x < gameRules.getBoardSizeH()
				&& y < gameRules.getBoardSizeV())
			if (board.getBoard(x, y) == -2)
				list.add(new Coordinates(x, y));
	}

	private void lookForNeighbors() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++) {
			for (int j = 0; j < gameRules.getBoardSizeV(); j++) {
				if (board.getBoard()[j][i] == 0)
					addNeighborhoodToList(i, j);
			}
		}
	}

	private void addNeighborhoodToList(int x, int y) {
		addToList(x - 1, y);

		addToList(x, y - 1);

		addToList(x + 1, y);

		addToList(x, y + 1);
	}
}
