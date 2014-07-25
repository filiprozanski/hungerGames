/*
 * Plik stworzony dnia 11 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import pl.praktykiatrem.game.battleship.rules.Direction;

public class ValidationInstruments {
	/**
	 * 
	 * Metoda <code>isPlaceValid</code> sprawdza, czy statek o danych
	 * parametrach mie�ci si� na polu gry
	 *
	 * @param direction
	 *            okre�la czy statek b�dzie poziomo 'h', czy pionowo 'v'
	 * @param x
	 *            wsp�rz�dna pozioma pocz�tku statku
	 * @param y
	 *            wsp�rz�dna pionowa pocz�tku statku
	 * @param polesNumber
	 *            liczba maszt�w (wielko�� statku)
	 * @return true je�eli statek zmie�ci si� na planszy, false je�eli nie
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
