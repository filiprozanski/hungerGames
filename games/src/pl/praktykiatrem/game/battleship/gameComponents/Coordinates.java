package pl.praktykiatrem.game.battleship.gameComponents;

/**
 * Klasa przechowuje koordynaty
 * 
 * @author Filip Ró¿añski
 *
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * 
     * @param x
     *            wspó³rzêdna x
     * @param y
     *            wspó³rzêdna y
     */
    public Coordinates(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * 
     * @return wspó³rzêdna x
     */
    public int getX() {
	return x;
    }

    /**
     * 
     * @return wspo³rzêdna y
     */
    public int getY() {
	return y;
    }

    /**
     * porównuje dwa obiekty typu Coordinates
     * 
     * @param other
     * @return zwraca true je¿eli obie wspó³rzêdne s¹ takie same
     */
    public boolean equals(Coordinates other) {
	if (this.x == other.x && this.y == other.y)
	    return true;
	else
	    return false;
    }

    public int hashCode() {
	return x * 10 + y;
    }
}
