package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;

public class Computer implements IComputer {
	private ComputerBoard board;
	private Game game;
	private Coordinates coords = new Coordinates(9, 9);
	private CoordsList list;
	private int shotCounter = 0;

	public Computer(Game game) {
		this.game = game;
		this.board = new ComputerBoard(game);
		this.list = new CoordsList(board, game);
	}

	@Override
	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++)
			board.setMiss(arrayList.get(i).getX(), arrayList.get(i).getY());
	}

	@Override
	public void setHit(int x, int y) {
		board.setHit(x, y);
	}

	public void setMiss(int x, int y) {
		board.setMiss(x, y);
	}

	@Override
	public Coordinates getCords(boolean hit) {
		return null;
	}
}
