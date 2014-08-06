package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;

public class Medium implements IComputer {
	private ComputerBoard board;
	private Game game;
	private Coordinates coords = new Coordinates(9, 9);
	private CoordsList list;
	private int shotCounter = 0;

	public Medium(Game game) {
		this.game = game;
		this.board = new ComputerBoard(game);
		this.list = new CoordsList(board, game);
	}

	private void print() {
		System.out.print(coords.getX());
		System.out.print(" ");
		System.out.print(coords.getY());
		System.out.print("  cOuNtEr=");
		System.out.println(shotCounter);
		board.printBoard();
	}

	@Override
	public Coordinates getCords(boolean hit) {
		computeShot();
		shotFromList();
		board.setMiss(coords.getX(), coords.getY());
		list.removeFromList(coords);
		shotCounter++;
		// print();
		return coords;
	}

	private void computeShot() {

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
			// if ((x_temp % 2 == 1 && y_temp % 2 == 1)
			// || (x_temp % 2 == 0 && y_temp % 2 == 0))
			if (board.getBoard(x_temp, y_temp) == -1) {
				this.coords = new Coordinates(x_temp, y_temp);
				break;
			}
		}
	}

	@Override
	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			board.setMiss(arrayList.get(i).getX(), arrayList.get(i).getY());
			list.removeFromList(arrayList.get(i));
		}
	}

	@Override
	public void setHit(int x, int y) {
		board.setHit(x, y);
		if (board.isHit(x - 1, y)) {
			if (board.isBlank(x + 1, y))
				list.addTopOfList(x + 1, y);
			else
				list.addTopOfList(x - 2, y);
		}
		if (board.isHit(x + 1, y)) {
			if (board.isBlank(x - 1, y))
				list.addTopOfList(x - 1, y);
			else
				list.addTopOfList(x + 2, y);
		}
		if (board.isHit(x, y + 1)) {
			if (board.isBlank(x, y - 1))
				list.addTopOfList(x, y - 1);
			else
				list.addTopOfList(x, y + 2);
		}
		if (board.isHit(x, y - 1)) {
			if (board.isBlank(x, y + 1))
				list.addTopOfList(x, y + 1);
			else
				list.addTopOfList(x, y - 2);
		}
	}

	public void setMiss(int x, int y) {
		board.setMiss(x, y);
	}

}
