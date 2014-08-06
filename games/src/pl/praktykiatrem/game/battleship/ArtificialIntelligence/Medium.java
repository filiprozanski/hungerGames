package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.util.ArrayList;
import java.util.List;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.rules.Game;
import pl.praktykiatrem.game.battleship.rules.Rand;

public class Medium implements Computer {
	private ComputerBoard board;
	private Game gameRules;
	private Coordinates coords = new Coordinates(9, 9);
	private List<Coordinates> list = new ArrayList<Coordinates>();
	private int shotCounter = 0;
	private int doubleCounter = 0;

	public Medium(Game gameRules) {
		this.gameRules = gameRules;
		this.board = new ComputerBoard(gameRules);
	}

	private void print() {
		System.out.print(coords.getX());
		System.out.print(" ");
		System.out.print(coords.getY());
		System.out.print("  cOuNtEr=");
		System.out.println(shotCounter);

		board.printBoard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.praktykiatrem.game.battleship.ArtificialIntelligence.Computer1#getCords
	 * ()
	 */
	@Override
	public Coordinates getCords(boolean hit) {
		computeShot();

		board.setMiss(coords.getX(), coords.getY());
		removeFromList(coords);
		shotCounter++;
		print();
		return coords;
	}

	private void computeShot() {
		discardSingleAreas();
		if (doubleCounter == 2)
			discardDoubleAreas();

		if (shotCounter > 40 && shotCounter % 2 == 1)
			lookForBiggestArea();
		if (shotCounter == 30)
			checkOutCorners();
		lookForTrio();
		lookForNeighbors();
		lookForDoubles();
		lookForTriples();
		shotFromList();
	}

	private void lookForTrio() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++)
			for (int j = 0; j < gameRules.getBoardSizeV(); j++)
				if (board.getBoard(i, j) == 0 && board.getBoard(i, j + 1) == 0
						&& board.getBoard(i, j + 2) == 0) {
					addTopOfList(i, j + 1);
				} else if (board.getBoard(i, j) == 0
						&& board.getBoard(i + 1, j) == 0
						&& board.getBoard(i + 2, j) == 0) {
					addTopOfList(i + 1, j);

				}
	}

	private void shotFromList() {

		if (list.size() > 0) {
			coords = list.get(0);
			// board.setMiss(coords.getX(), coords.getY());
			list.remove(0);
			// } else if (shotCounter < 1) {
			// randShot();
		} else {
			randShot();
		}
	}

	private void lookForBiggestArea() {
		int max_size = 0;
		int temp_size = 0;
		int x = 0;
		int y = 0;
		for (int i = 0; i < gameRules.getBoardSizeH(); i++)
			for (int j = 0; j < gameRules.getBoardSizeV(); j++) {
				for (int k = j; k < gameRules.getBoardSizeV(); k++) {
					if (board.getBoard(i, k) != -2
							|| k >= gameRules.getBoardSizeV() - 1) {
						temp_size = k - j;
						break;
					}
				}
				if (temp_size > max_size) {
					max_size = temp_size;
					x = i;
					y = (j + j + max_size) / 2;
				}
			}

		for (int i = 0; i < gameRules.getBoardSizeV(); i++)
			for (int j = 0; j < gameRules.getBoardSizeH(); j++) {
				for (int k = j; k < gameRules.getBoardSizeH(); k++) {
					if (board.getBoard(k, i) != -2
							|| k >= gameRules.getBoardSizeH() - 1) {
						temp_size = k - j;
						break;
					}
				}
				if (temp_size > max_size) {
					max_size = temp_size;
					y = i;
					x = (j + j + max_size) / 2;
				}
			}

		// System.out.print("test= ");
		// System.out.println(max_size);
		addToList(x, y);
	}

	private void removeFromList(Coordinates coords) {
		list.remove(coords);
	}

	private void checkOutCorners() {
		addToList(0, 0);
		addToList(gameRules.getBoardSizeH() - 1, 0);
		addToList(0, gameRules.getBoardSizeV() - 1);
		addToList(gameRules.getBoardSizeH() - 1, gameRules.getBoardSizeH() - 1);

	}

	private void removeFromList(int x, int y) {
		list.remove(new Coordinates(x, y));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.praktykiatrem.game.battleship.ArtificialIntelligence.Computer1#setSink
	 * (int, java.util.ArrayList)
	 */
	@Override
	public void setSink(int id, ArrayList<Coordinates> arrayList) {
		for (int i = 0; i < arrayList.size(); i++)
			board.setSink(id + 1, arrayList.get(i).getX(), arrayList.get(i)
					.getY());
		if (arrayList.size() == 2) {
			doubleCounter++;
			System.out.print("Zbi³em dwójkê");
		}
		// list.clear();
		lookForNeighbors();
		lookForDoubles();
		lookForTriples();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.praktykiatrem.game.battleship.ArtificialIntelligence.Computer1#setHit
	 * (int, int)
	 */
	@Override
	public void setHit(int x, int y) {
		board.setHit(x, y);
		lookForNeighbors();
		lookForDoubles();
		lookForTriples();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pl.praktykiatrem.game.battleship.ArtificialIntelligence.Computer1#setMiss
	 * (int, int)
	 */
	@Override
	public void setMiss(int x, int y) {
		board.setMiss(x, y);
	}

	private void discardSingleAreas() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++)
			for (int j = 0; j < gameRules.getBoardSizeV(); j++)
				if (board.getBoard(i, j) == -2 && board.getBoard(i - 1, j) > -2
						&& board.getBoard(i + 1, j) > -2
						&& board.getBoard(i, j - 1) > -2
						&& board.getBoard(i, j + 1) > -2)
					board.setAvoid(i, j);
	}

	private void discardDoubleAreas() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++)
			for (int j = 0; j < gameRules.getBoardSizeV(); j++)
				if (board.getBoard(i, j) == -2) {
					if (board.getBoard(i - 1, j) == -1 // otoczenie
							&& board.getBoard(i + 1, j) == -2// góra
							&& board.getBoard(i, j - 1) == -1
							&& board.getBoard(i, j + 1) == -1
							&& board.getBoard(i + 2, j) == -1) {
						board.setAvoid(i, j);
						board.setAvoid(i + 1, j);
					} else if (board.getBoard(i - 1, j) == -1 // otoczenie
							&& board.getBoard(i + 1, j) == -1
							&& board.getBoard(i, j - 1) == -2// lewo
							&& board.getBoard(i, j + 1) == -1
							&& board.getBoard(i, j - 2) == -1) {
						board.setAvoid(i, j);
						board.setAvoid(i, j - 1);
					} else if (board.getBoard(i - 1, j) == -1 // otoczenie
							&& board.getBoard(i + 1, j) == -1
							&& board.getBoard(i, j - 1) == -1
							&& board.getBoard(i, j + 1) == -2
							&& board.getBoard(i, j + 2) == -1) {// prawo
						board.setAvoid(i, j);
						board.setAvoid(i, j + 1);
					} else if (board.getBoard(i - 1, j) == -2 // otoczenie
							&& board.getBoard(i + 1, j) == -1
							&& board.getBoard(i, j - 1) == -1
							&& board.getBoard(i, j + 1) == -1
							&& board.getBoard(i - 2, j) == -1) {
						board.setAvoid(i, j);
						board.setAvoid(i - 1, j);
					}
				}
	}

	private void lookForDoubles() {
		for (int i = 0; i < gameRules.getBoardSizeH() - 1; i++) {
			for (int j = 0; j < gameRules.getBoardSizeV() - 1; j++) {
				if (board.getBoard()[j][i] == 0
						&& board.getBoard()[j + 1][i] == 0) {
					addTopOfList(i, j - 1);
					addTopOfList(i, j + 2);
					removeFromList(i - 1, j);
					removeFromList(i + 1, j);
					removeFromList(i - 1, j + 1);
					removeFromList(i + 1, j + 1);

				} else if (board.getBoard()[j][i] == 0
						&& board.getBoard()[j][i + 1] == 0) {
					addTopOfList(i - 1, j);
					addTopOfList(i + 2, j);
					removeFromList(i, j - 1);
					removeFromList(i, j + 1);
					removeFromList(i + 1, j - 1);
					removeFromList(i + 1, j + 1);
				}
			}
		}
	}

	private void lookForTriples() {
		for (int i = 0; i < gameRules.getBoardSizeH(); i++) {
			for (int j = 0; j < gameRules.getBoardSizeV(); j++) {
				if (board.getBoard(i, j) == 0 && board.getBoard(i, j + 2) == 0) {
					addTopOfList(i, j + 1);
				} else if (board.getBoard(i, j) == 0
						&& board.getBoard(i + 2, j) == 0) {
					addTopOfList(i + 1, j);
				}
			}
		}
	}

	private void addTopOfList(int x, int y) {
		if (x >= 0 && y >= 0 && x < gameRules.getBoardSizeH()
				&& y < gameRules.getBoardSizeV())
			if (board.getBoard(x, y) == -2)
				if (!list.contains(new Coordinates(x, y)))
					list.add(0, new Coordinates(x, y));

	}

	private void addToList(int x, int y) {
		if (x >= 0 && y >= 0 && x < gameRules.getBoardSizeH()
				&& y < gameRules.getBoardSizeV())
			if (board.getBoard(x, y) == -2)
				if (!list.contains(new Coordinates(x, y)))
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
