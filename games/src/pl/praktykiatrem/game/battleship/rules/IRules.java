package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

/**
 * interfejs zawiera wszystkie metody, które powinna udostêpniaæ klasa opisuj¹ca
 * zasady gry
 * 
 * @author Filip Ró¿añski
 *
 */
public interface IRules {

	public boolean getCurrentPlayer();

	/**
	 * umieszcza statek na planszy
	 * 
	 * @param p
	 *            gracz ustawiaj¹cy
	 * @param id
	 *            numer identyfikacyjny statku
	 * @param polesNumber
	 *            liczba masztów statku
	 * @param direction
	 *            kierunek ustawiania
	 * @param x
	 *            wspó³rzêdna pozioma
	 * @param y
	 *            wspó³rzêdna pionowa
	 * @return true je¿eli statek mo¿na ustawiæ, false w przeciwnym wypadku
	 */
	public boolean placeShips(PlayerStatus p, int id, int polesNumber,
			Direction direction, int x, int y);

	/**
	 * wykonuje strza³
	 * 
	 * @param p
	 *            gracz do którego strzelamy
	 * @param x
	 *            wspó³rzêdna pozioma
	 * @param y
	 *            wspo³rzêdna pionowa
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
	 *            gracz, o którego statki pytamy
	 * @return liczba statków, które s¹ w grze (s¹ ustawione, ale nie zbite)
	 */
	public int getActiveShipsNumber(PlayerStatus player);

	/**
	 * funkcja przelicza skutecznoœæ strza³ów gracza i zwraca jej wartoœæ
	 * 
	 * @param player
	 *            gracz o którego statystykê pytamy
	 * @param hit
	 *            informacja o tym czy ostatni ruch by³ trafiony, czy nie
	 * @return skutecznoœæ strza³ów
	 */
	public int getAccuracy(PlayerStatus player, boolean hit);

	int getShipID(PlayerStatus player, int x, int y);

	public void resetGame(PlayerStatus player);
}
