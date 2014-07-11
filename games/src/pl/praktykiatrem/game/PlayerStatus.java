/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class PlayerStatus {
	public void showCountShips(Player g)
    {	
		ConsoleInterface.showCountShips(g.getShipsNumber());
    }
	public void showYourMove(Player g)
    {
    	ConsoleInterface.showYourMove(g);
    }
	public void doGameSummary(Player g, Player h)
	{
		showCountShips(g);
		showCountShips(h);
	}
}
