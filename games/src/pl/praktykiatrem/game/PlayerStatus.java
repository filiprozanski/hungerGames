/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class PlayerStatus {
    
	public static void showCountShips(int shipsNumber)
    {	
		ConsoleInterface.showCountShips(shipsNumber);
    }
	public static void showEnemyCountShips(int shipsNumber)
    {	
		ConsoleInterface.showEnemyCountShips(shipsNumber);
    }
	public static void showYourMove(Player p)
    {
    	ConsoleInterface.showYourMove(p);
    }
	public static void showYouWon(Player p)
    {
    	ConsoleInterface.showYouWon(p);
    }
	public static void showYouLost(Player p)
    {
    	ConsoleInterface.showYouLost(p);
    }
}
