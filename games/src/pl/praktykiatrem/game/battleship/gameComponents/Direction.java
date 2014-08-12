package pl.praktykiatrem.game.battleship.gameComponents;

public enum Direction {
	HORIZONTAL, VERTICAL;

	public static Direction getDirection(char a) {
		if (a == 'h' || a == 'H') {
			return HORIZONTAL;
		} else {
			return VERTICAL;
		}
	}

	public static Direction getOpposite(Direction dir) {
		if (dir == VERTICAL) {
			return HORIZONTAL;
		} else {
			return VERTICAL;
		}
	}

}
