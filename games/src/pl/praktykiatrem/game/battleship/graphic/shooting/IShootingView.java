package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

/**
 * 
 * Klasa <code>IShootingView</code>
 *
 * interfejs zawiera klasy, których presenter u¿ywa do sterowania widokiem
 *
 * @author filipr
 * @version 30 lip 2014 14:49:24
 *
 */
public interface IShootingView {

	/**
	 * 
	 * Metoda <code>changePlaceIcon</code>
	 * 
	 * zmienia ikonê na planszy gracza
	 *
	 * @param x
	 *            wspó³rzêdna x pola
	 * @param y
	 *            wspó³rzêdna y pola
	 * @param type
	 *            okre¶la typ ikony
	 */
	void changePlaceIcon(int x, int y, int type);

	/**
	 * 
	 * Metoda <code>changeStatus</code>
	 * 
	 * zmienia wy¶wietlany label w zale¿no¶ci od tego, który gracz wykonuje ruch
	 *
	 * @param ready
	 *            aktualny status gracza (true - wykonuje ruch, false - czeka)
	 */
	void changeStatus(boolean ready);

	/**
	 * 
	 * Metoda <code>disableAllPlayerBoardPlaces</code>
	 * 
	 * deaktywuje wszystkie pola na planszy gracza
	 *
	 */
	void disableAllPlayerBoardPlaces();

	/**
	 * 
	 * Metoda <code>changeStateAllEnemyBoardPlaces</code>
	 *
	 * zmienia stan pól na planszy przeciwnika
	 *
	 * @param enable
	 *            okre¶la typ zmiany (true - odblokuj, false - zablokuj)
	 * @param lockedPlaces
	 *            miejsca podane w li¶cie zawsze pozostaj± zablokowane
	 */
	void changeStateAllEnemyBoardPlaces(boolean enable,
			ArrayList<Coordinates> lockedPlaces);

	/**
	 * 
	 * Metoda <code>initialize</code>
	 *
	 * inicjalizuje okno gry
	 *
	 * @param sizeH
	 *            szeroko¶æ pola gry
	 * @param sizeV
	 *            wysoko¶æ pola gry
	 */
	void initialize(int sizeH, int sizeV);

	/**
	 * 
	 * Metoda <code>drawShipLocation</code>
	 * 
	 * u¿ywana do wype³nienia planszy gracza ustawionymi przez niego statkami
	 *
	 * @param tab
	 *            tablica koordynat, na których ustawiony jest statek
	 * @param id
	 *            numer identyfikacyjny ustawianego statku
	 */
	void drawShipLocation(Coordinates[] tab, int id);

	/**
	 * 
	 * Metoda <code>changeBattlePlaceIcon</code>
	 * 
	 * zmienia ikonê pola na planszy przeciwnika po naszym strzale
	 *
	 * @param x
	 *            wspó³rzêdna pozioma pola
	 * @param y
	 *            wspó³rzêdna pionowa pola
	 * @param type
	 *            rodzaj ikony
	 */
	void changeBattlePlaceIcon(int x, int y, int type);

	/**
	 * 
	 * Metoda <code>disableBatlleBoardPlace</code>
	 * 
	 * deaktywuje podane pole na planszy przeciwnika
	 *
	 * @param x
	 *            wspó³rzêdna pozioma pola
	 * @param y
	 *            wspó³rzêdna pionowa pola
	 */
	void disableBatlleBoardPlace(int x, int y);

	/**
	 * 
	 * Metoda <code>setStats</code>
	 * 
	 * Ustawia wszystkie trzy statystyki widoczne w oknie
	 *
	 * @param playerShips
	 *            liczba statków gracza
	 * @param enemyShips
	 *            liczba statów przeciwnika
	 * @param accuracy
	 *            skuteczno¶æ oddawanych strza³ów
	 */
	void setStats(int playerShips, int enemyShips, int accuracy);

	/**
	 * 
	 * Metoda <code>setStats</code>
	 * 
	 * ustawua liczbê statków gracza i przeciwnika, skuteczno¶æ pozostawia bez
	 * zmian
	 *
	 * @param playerShips
	 *            liczba statków gracza
	 * @param enemyShips
	 *            liczba statków przeciwnika
	 */
	void setStats(int playerShips, int enemyShips);

}
