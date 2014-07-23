/*
 * Plik stworzony dnia 11 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.rules;

public class ValidationInstruments {
	public static boolean isPlaceClear(char[][] tab, int x, int y) {
		if (tab[y][x] == 'c' || tab[y][x] == 'C')
			return true;
		else
			return false;
	}

	public static boolean isShipOnPlace(char[][] tab, int x, int y, int id) {
		char cShipID = tab[y][x];
		int shipID = (int) cShipID - 48;
		if (shipID >= 0 && shipID <= 6)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * Metoda <code>isPlaceValid</code> sprawdza, czy statek o danych
	 * parametrach mie¶ci siê na polu gry
	 *
	 * @param direction
	 *            okre¶la czy statek bêdzie poziomo 'h', czy pionowo 'v'
	 * @param x
	 *            wspó³rzêdna pozioma pocz±tku statku
	 * @param y
	 *            wspó³rzêdna pionowa pocz±tku statku
	 * @param polesNumber
	 *            liczba masztów (wielko¶æ statku)
	 * @return true je¿eli statek zmie¶ci siê na planszy, false je¿eli nie
	 */
	public static boolean isPlaceValid(Direction direction, int x, int y,
			int polesNumber) {
		if (direction == Direction.HORIZONTAL) {
			if (x + polesNumber <= 10)
				return true;
			else
				return false;
		} else if (direction == Direction.VERTICAL) {
			if (y + polesNumber <= 10)
				return true;
			else
				return false;
		} else
			return false;
	}
}
