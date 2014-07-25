package pl.praktykiatrem.game.battleship.gameComponents;

/**
 * Klasa przechowuje koordynaty
 * @author Filip Ró¿añski
 *
 */
public class Coordinates {
	private int x;
	private int y;
	
	/**
	 * 
	 * @param x wspó³rzêdna x
	 * @param y wspó³rzêdna y
	 */
	public Coordinates(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return wspó³rzêdna x
	 */
	public int getX()
	{
		return x;
	}
	
	/**
	 * 
	 * @return wspo³rzêdna y
	 */
	public int getY()
	{
		return y;
	}
}
