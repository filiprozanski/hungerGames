package pl.praktykiatrem.game.battleship.ai;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;

public class Medium implements IComputer {
	private ComputerBoard board;
	private Game game;
	private Coordinates coords;
	private CoordsList list;

	public Medium(Game game) {
		this.game = game;
		this.board = new ComputerBoard(game);
		this.list = new CoordsList(board, game);
	}

	@Override
	public Coordinates getCords() {
		discardSingleAreas();
		shotFromList();
		list.removeFromList(coords);
		return coords;
	}

	private void shotFromList() {
		if (list.getListSize() > 0) {
			coords = list.getCoords();
		} else {
			randShot();
		}
	}

	private void randShot() {
		int x_temp = 0;
		int y_temp = 0;
		while (true) {
			x_temp = Rand.getRandX(game);
			y_temp = Rand.getRandY(game);
			if ((x_temp % 2 == 1 && y_temp % 2 == 1)
					|| (x_temp % 2 == 0 && y_temp % 2 == 0))
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
			deleteNeighbors(arrayList.get(i).getX(), arrayList.get(i).getY());
			list.removeFromList(arrayList.get(i));
		}
	}

	@Override
	public void setHit(Coordinates coords) {
		board.setHit(coords);
		discardSingleAreas();
		addInLine(coords);
		addGaps(coords.getX(), coords.getY());
		addNeighbors(coords.getX(), coords.getY());
	}

	private void addGaps(int x, int y) {

		if (board.isHit(new Coordinates(x - 2, y)))
			list.addTopOfList(x - 1, y);
		if (board.isHit(new Coordinates(x + 2, y)))
			list.addTopOfList(x + 1, y);
		if (board.isHit(new Coordinates(x, y - 2)))
			list.addTopOfList(x, y - 1);
		if (board.isHit(new Coordinates(x, y + 2)))
			list.addTopOfList(x, y + 1);
	}

	private void addInLine(Coordinates coords) {
		int x = coords.getX();
		int y = coords.getY();

		if (board.isHit(new Coordinates(x - 1, y))) {
			if (board.isBlank(new Coordinates(x + 1, y)))
				list.addTopOfList(x + 1, y);
			else
				list.addTopOfList(x - 2, y);
		}
		if (board.isHit(new Coordinates(x + 1, y))) {
			if (board.isBlank(new Coordinates(x - 1, y)))
				list.addTopOfList(x - 1, y);
			else
				list.addTopOfList(x + 2, y);
		}
		if (board.isHit(new Coordinates(x, y + 1))) {
			if (board.isBlank(new Coordinates(x, y - 1)))
				list.addTopOfList(x, y - 1);
			else
				list.addTopOfList(x, y + 2);
		}
		if (board.isHit(new Coordinates(x, y - 1))) {
			if (board.isBlank(new Coordinates(x, y + 1)))
				list.addTopOfList(x, y + 1);
			else
				list.addTopOfList(x, y - 2);
		}
	}

	private void addNeighbors(int x, int y) {
		list.addToList(x - 1, y);
		list.addToList(x + 1, y);
		list.addToList(x, y - 1);
		list.addToList(x, y + 1);
	}

	private void discardSingleAreas() {
		for (int i = 0; i < game.getBoardSizeV(); i++)
			for (int j = 0; j < game.getBoardSizeH(); j++)
				if (board.getBoard(new Coordinates(i, j)) == -2
						&& board.getBoard(new Coordinates(i - 1, j)) > -2
						&& board.getBoard(new Coordinates(i + 1, j)) > -2
						&& board.getBoard(new Coordinates(i, j - 1)) > -2
						&& board.getBoard(new Coordinates(i, j + 1)) > -2)
					board.setMiss(new Coordinates(i, j));
	}

	private void deleteNeighbors(int x, int y) {
		list.removeFromList(x - 1, y);
		list.removeFromList(x + 1, y);
		list.removeFromList(x, y - 1);
		list.removeFromList(x, y + 1);
	}

	@Override
	public void setMiss(Coordinates coords) {
		board.setMiss(coords);
	}

}
