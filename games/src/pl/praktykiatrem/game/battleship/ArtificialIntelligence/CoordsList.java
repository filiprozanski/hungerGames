package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;
import java.util.List;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;

public class CoordsList {
	private List<Coordinates> list;
	private Game game;
	private ComputerBoard board;

	public CoordsList(ComputerBoard board, Game game) {
		this.list = new ArrayList<Coordinates>();
		this.game = game;
		this.board = board;
	}

	public Coordinates getCoords() {
		Coordinates temp = list.get(0);
		list.remove(0);
		return temp;
	}

	public int getListSize() {
		return list.size();
	}

	public void addTopOfList(int x, int y) {
		if (x >= 0 && y >= 0 && x < game.getBoardSizeH()
				&& y < game.getBoardSizeV())
			if (board.isBlank(x, y))
				if (!list.contains(new Coordinates(x, y)))
					list.add(0, new Coordinates(x, y));

	}

	public void addToList(int x, int y) {
		if (x >= 0 && y >= 0 && x < game.getBoardSizeH()
				&& y < game.getBoardSizeV())
			if (board.isBlank(x, y))
				if (!list.contains(new Coordinates(x, y)))
					list.add(new Coordinates(x, y));
	}

	public void removeFromList(Coordinates coords) {
		list.remove(coords);
	}
}
