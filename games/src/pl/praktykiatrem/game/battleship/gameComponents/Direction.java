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
}
