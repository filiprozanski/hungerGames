package pl.praktykiatrem.game.battleship.ai;

import java.util.ArrayList;
import java.util.List;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.rules.GameRules;

public class CoordsList {
	private List<Coordinates> list;
	private GameRules game;
	private ComputerBoard board;

	public CoordsList(ComputerBoard board, GameRules game) {
		this.list = new ArrayList<Coordinates>();
		this.game = game;
		this.board = board;
	}

	public Coordinates getCoords() {
		Coordinates temp = list.get(0);
		list.remove(0);
		return temp;
	}

	public Coordinates getCoords(int index) {
		return list.get(index);
	}

	public int getListSize() {
		return list.size();
	}

	public void addTopOfList(int x, int y) {
		if (x >= 0 && y >= 0 && x < game.getBoardSizeV()
				&& y < game.getBoardSizeH())
			if (board.isBlank(new Coordinates(x, y)))
				if (!list.contains(new Coordinates(x, y)))
					list.add(0, new Coordinates(x, y));

	}

	public void addToList(int x, int y) {
		if (x >= 0 && y >= 0 && x < game.getBoardSizeV()
				&& y < game.getBoardSizeH())
			if (board.isBlank(new Coordinates(x, y)))
				if (!list.contains(new Coordinates(x, y)))
					list.add(new Coordinates(x, y));
	}

	public void removeFromList(Coordinates coords) {
		list.remove(coords);
	}

	public void removeFromList(int x, int y) {
		list.remove(new Coordinates(x, y));
	}

	public void clear() {
		list.clear();

	}
}
