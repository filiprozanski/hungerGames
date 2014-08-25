package pl.praktykiatrem.game.battleship.components;

import java.io.Serializable;

/**
 * Klasa przechowuje koordynaty
 * 
 * @author Filip Ró¿añski
 *
 */
public class Coordinates implements Serializable {
	private static final long serialVersionUID = -2943213395648364828L;
	private int x;
	private int y;

	public Coordinates() {

	}

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
	public boolean equals(Object other) {
		Coordinates other2 = (Coordinates) other;

		if (this.x == other2.getX() && this.y == other2.getY())
			return true;
		else
			return false;
	}

	public int hashCode() {
		return this.x * 10 + this.y;
	}
}
