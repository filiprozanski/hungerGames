package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;

public class Hard implements IComputer {
	private ComputerBoard board;
	private Game game;
	private DensityBoard density;
	private CoordsList list;
	private boolean huntMode = false;
	private Coordinates coords = new Coordinates(9, 9);
	private int shotCounter = 0;

	public Hard(Game game) {
		this.game = game;
		this.board = new ComputerBoard(game);
		this.density = new DensityBoard(board, game);
		this.list = new CoordsList(board, game);
	}

	private void print() {
		if (huntMode == false)
			System.out.print("huntMode OFF");
		else if (huntMode == true)
			System.out.print("huntMode ON");
		System.out.print(coords.getX());
		System.out.print(" ");
		System.out.print(coords.getY());
		System.out.print("  cOuNtEr=");
		System.out.println(shotCounter);
		// board.printBoard();
		density.printDensityBoard();
	}

	@Override
	public Coordinates getCords() {
		shotFromList();
		shotCounter++;
		coords = density.getCoords();
		// setMiss(coords.getX(), coords.getY());
		// shotFromList();

		// density.setDensityPlaceUsed(coords.getX(), coords.getY());
		print();
		// return density.getCoords(board);
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
		}
	}

	@Override
	public void setHit(int x, int y) {
		board.setHit(x, y);
	}

	public void setMiss(int x, int y) {
		board.setMiss(x, y);
	}

}
