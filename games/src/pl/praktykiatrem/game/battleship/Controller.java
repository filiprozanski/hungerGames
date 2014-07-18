/*
 * Plik stworzony dnia 14 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

import javax.swing.text.StyledEditorKit.BoldAction;

import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;


public class Controller {
	GameStatus A = new GameStatus();
	GameStatus B = new GameStatus();
	
	public void setName(String name, int id)
	{
		switch (id)
		{
		case 1:
			A.setName(name);
			break;
		case 2:
			B.setName(name);
			break;
		}
	}
}
