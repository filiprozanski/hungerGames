/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class PlayerStatus {
	public static void showCountShips(Player g)
    {	
		ConsoleInterface.showCountShips(g);
    }
	public static void showYourMove(Player g)
    {
    	ConsoleInterface.showYourMove(g);
    }
	public static void doGameSummary(Player g, Player h)
	{
		showCountShips(g);
		showCountShips(h);
	}
}
