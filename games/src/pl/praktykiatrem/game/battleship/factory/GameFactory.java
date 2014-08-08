/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.factory;

import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;

public class GameFactory {
	private int boardSize_x;
	private int boardSize_y;
	private int shipTypes[];

	public GameFactory(int boardSize_x, int boardSize_y, int shipTypes[]) {
		this.boardSize_x = boardSize_x;
		this.boardSize_y = boardSize_y;
		this.shipTypes = shipTypes;
	}

	public BSPlayerStatus createPlayer() {
		return new BSPlayerStatus(boardSize_x, boardSize_y, shipTypes);
	}
}
