package pl.praktykiatrem.game.battleship.gameComponents;

<<<<<<< HEAD
import java.util.ArrayList;

/**
 * reprezentuje statek ustawiany na planszy
 * @author Filip Ró¿añski
 *
 */
public class Ship {
	private int shipID;
	private int polesNumber;
	/**
	 * przechowuje wszystkie koordynaty, na których statek jest umieszczony
	 */
	private ArrayList<Coordinates> coords;
=======
import java.io.Serializable;
>>>>>>> refs/remotes/origin/sru

public class Ship implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Ship [shipID=" + shipID + ", polesNumber=" + polesNumber + "]";
	}

	private transient int polesNumber;
	private int shipID;

	/**
	 * 
	 * @param polesNumber liczba masztów tworzonego statku
	 */
	public Ship(int polesNumber) {
		this.polesNumber = polesNumber;
		coords = new ArrayList<Coordinates>(polesNumber);
	}

	/**
	 * zmniejsza liczbê masztów
	 * @return je¿eli statek zostaje zatopiony zwraca true
	 */
	public boolean reducePolesNumber() {
		polesNumber--;
		if (polesNumber == 0)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return liczba masztów
	 */
	public int getPolesNumber() {
		return polesNumber;
	}

	/**
	 * 
	 * @return id wybranego statku
	 */
	public int getID() {
		return shipID;
	}
	
	/**
	 * Dodaje do listy koordynaty
	 * @param c dodawane koordynaty
	 */
	void addCoordinate(Coordinates c)
	{
		coords.add(c);
	}
}
