package pl.praktykiatrem.game.battleship;

public class Coords {
	char direction;
	int x;
	int y;

	public Coords() {
	}

	public Coords(char d, int x, int y) {
		direction = d;
		this.x = x;
		this.y = y;
	}
}
