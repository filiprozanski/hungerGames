package pl.praktykiatrem.game.battleship.gameComponents;

/**
 * Klasa przechowuje koordynaty
 * 
 * @author Filip R�a�ski
 *
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * 
     * @param x
     *            wsp�rz�dna x
     * @param y
     *            wsp�rz�dna y
     */
    public Coordinates(int x, int y) {
	this.x = x;
	this.y = y;
    }

    /**
     * 
     * @return wsp�rz�dna x
     */
    public int getX() {
	return x;
    }

    /**
     * 
     * @return wspo�rz�dna y
     */
    public int getY() {
	return y;
    }

    /**
     * por�wnuje dwa obiekty typu Coordinates
     * 
     * @param other
     * @return zwraca true je�eli obie wsp�rz�dne s� takie same
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
