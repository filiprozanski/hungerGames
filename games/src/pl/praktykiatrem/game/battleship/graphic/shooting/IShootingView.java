package pl.praktykiatrem.game.battleship.graphic.shooting;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

/**
 * 
 * Klasa <code>IShootingView</code>
 *
 * interfejs zawiera klasy, kt�rych presenter u�ywa do sterowania widokiem
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
	 * zmienia ikon� na planszy gracza
	 *
	 * @param x
	 *            wsp�rz�dna x pola
	 * @param y
	 *            wsp�rz�dna y pola
	 * @param type
	 *            okre�la typ ikony
	 */
	void changePlaceIcon(int x, int y, int type);

	/**
	 * 
	 * Metoda <code>changeStatus</code>
	 * 
	 * zmienia wy�wietlany label w zale�no�ci od tego, kt�ry gracz wykonuje ruch
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
	 * zmienia stan p�l na planszy przeciwnika
	 *
	 * @param enable
	 *            okre�la typ zmiany (true - odblokuj, false - zablokuj)
	 * @param lockedPlaces
	 *            miejsca podane w li�cie zawsze pozostaj� zablokowane
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
	 *            szeroko�� pola gry
	 * @param sizeV
	 *            wysoko�� pola gry
	 */
	void initialize(int sizeH, int sizeV);

	/**
	 * 
	 * Metoda <code>drawShipLocation</code>
	 * 
	 * u�ywana do wype�nienia planszy gracza ustawionymi przez niego statkami
	 *
	 * @param tab
	 *            tablica koordynat, na kt�rych ustawiony jest statek
	 * @param id
	 *            numer identyfikacyjny ustawianego statku
	 */
	void drawShipLocation(Coordinates[] tab, int id);

	/**
	 * 
	 * Metoda <code>changeBattlePlaceIcon</code>
	 * 
	 * zmienia ikon� pola na planszy przeciwnika po naszym strzale
	 *
	 * @param x
	 *            wsp�rz�dna pozioma pola
	 * @param y
	 *            wsp�rz�dna pionowa pola
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
	 *            wsp�rz�dna pozioma pola
	 * @param y
	 *            wsp�rz�dna pionowa pola
	 */
	void disableBatlleBoardPlace(int x, int y);

	/**
	 * 
	 * Metoda <code>setStats</code>
	 * 
	 * Ustawia wszystkie trzy statystyki widoczne w oknie
	 *
	 * @param playerShips
	 *            liczba statk�w gracza
	 * @param enemyShips
	 *            liczba stat�w przeciwnika
	 * @param accuracy
	 *            skuteczno�� oddawanych strza��w
	 */
	void setStats(int playerShips, int enemyShips, int accuracy);

	/**
	 * 
	 * Metoda <code>setStats</code>
	 * 
	 * ustawua liczb� statk�w gracza i przeciwnika, skuteczno�� pozostawia bez
	 * zmian
	 *
	 * @param playerShips
	 *            liczba statk�w gracza
	 * @param enemyShips
	 *            liczba statk�w przeciwnika
	 */
	void setStats(int playerShips, int enemyShips);

}
