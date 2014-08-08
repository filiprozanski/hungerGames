/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import pl.praktykiatrem.game.battleship.gameComponents.BSBoard;

/**
 * 
 * Klasa <code>BoardDrawing</code> przechowuje statyczne metody do wy¶wietlania
 * w konsoli planszy do gry
 *
 * @author hungerGames
 *
 */
public class BoardDrawing {
	/**
	 * 
	 * Metoda <code>drawGameBoardForOpponent</code> wypisuje w konsoli plansze
	 * przeciwnika maskujac ustawione na niej statki, pozosta³e stany
	 * wy¶wietlaj± siê normalnie
	 *
	 * @param gamePlace
	 *            tablica do wypisania w konsoli
	 */
	public static void drawGameBoardForOpponent(BSBoard gamePlace) {
		System.out.print(" ");
		for (int i = 0; i < gamePlace.getHorizontalSize(); i++)
			System.out.print(i);

		System.out.println();

		for (int i = 0; i < gamePlace.getVerticalSize(); i++) {
			System.out.print(i);
			for (int j = 0; j < gamePlace.getHorizontalSize(); j++) {
				if (!gamePlace.isShipOnPlace(i, j)
						&& !gamePlace.isPlaceActive(i, j))
					System.out.print("M");
				else if (gamePlace.isShipOnPlace(i, j)
						&& !gamePlace.isPlaceActive(i, j))
					System.out.print("H");
				else
					System.out.print("+");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * Metoda <code>drawGameBoardForPlayer</code> wypisuje w konsoli tablicê
	 * gracza ze wszystkimi stanami
	 *
	 * @param gamePlace
	 *            reprezentuje tablice do wypisania
	 */
	public static void drawGameBoardForPlayer(BSBoard gamePlace) {
		// BSPlace[][] tab = gamePlace.getGameBoard();

		System.out.print(" ");
		for (int i = 0; i < gamePlace.getHorizontalSize(); i++)
			System.out.print(i);

		System.out.println();

		for (int i = 0; i < gamePlace.getVerticalSize(); i++) {
			System.out.print(i);
			for (int j = 0; j < gamePlace.getHorizontalSize(); j++) {
				if (gamePlace.isShipOnPlace(i, j)
						&& gamePlace.isPlaceActive(i, j))
					System.out.print(gamePlace.getShipID(i, j));
				else if (gamePlace.isShipOnPlace(i, j)
						&& !gamePlace.isPlaceActive(i, j))
					System.out.print("H");
				else if (!gamePlace.isShipOnPlace(i, j)
						&& !gamePlace.isPlaceActive(i, j))
					System.out.print("M");
				else
					System.out.print("+");
			}
			System.out.println();
		}
	}
}
