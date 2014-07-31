package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

/**
 * interfejs zawiera wszystkie metody, kt�re powinna udost�pnia� klasa opisuj�ca
 * zasady gry
 * 
 * @author Filip R�a�ski
 *
 */
public interface IRules {

	public boolean getCurrentPlayer();

	/**
	 * umieszcza statek na planszy
	 * 
	 * @param p
	 *            gracz ustawiaj�cy
	 * @param id
	 *            numer identyfikacyjny statku
	 * @param polesNumber
	 *            liczba maszt�w statku
	 * @param direction
	 *            kierunek ustawiania
	 * @param x
	 *            wsp�rz�dna pozioma
	 * @param y
	 *            wsp�rz�dna pionowa
	 * @return true je�eli statek mo�na ustawi�, false w przeciwnym wypadku
	 */
	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y);

	/**
	 * wykonuje strza�
	 * 
	 * @param p
	 *            gracz do kt�rego strzelamy
	 * @param x
	 *            wsp�rz�dna pozioma
	 * @param y
	 *            wspo�rz�dna pionowa
	 * @return
	 */
	public int makeMove(PlayerStatus p, int x, int y);

	/**
	 * usuwa statki z planszy
	 * 
	 * @param player
	 * @param id
	 * @param polesNumber
	 * @param direction
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean displaceShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y);

	public int getBoardSize_H();

	public int getBoardSize_V();

	public int[] getShipTypes();

	public int getShipsNumber();

	/**
	 * @param player
	 *            gracz, o kt�rego statki pytamy
	 * @return liczba statk�w, kt�re s� w grze (s� ustawione, ale nie zbite)
	 */
	public int getActiveShipsNumber(PlayerStatus player);

	/**
	 * funkcja przelicza skuteczno�� strza��w gracza i zwraca jej warto��
	 * 
	 * @param player
	 *            gracz o kt�rego statystyk� pytamy
	 * @param hit
	 *            informacja o tym czy ostatni ruch by� trafiony, czy nie
	 * @return skuteczno�� strza��w
	 */
	public int getAccuracy(PlayerStatus player, boolean hit);

	int getShipID(PlayerStatus player, int x, int y);

	public void resetGame(PlayerStatus player);
}
