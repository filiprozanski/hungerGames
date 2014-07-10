/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class PlayerStatus {
	private Player gamer;
	
	public PlayerStatus(Player g)
    
	public void showCountShips()
    {	
		ConsoleInterface.showCountShips(gamer.getShipsNumber());
    }
	public void showYourMove()
    {
    	ConsoleInterface.showYourMove(gamer);
    }
	public void showYouWon()
    {
    	ConsoleInterface.showYouWon(gamer);
    }
	public void showYouLost()
    {
    	ConsoleInterface.showYouLost(gamer);
    }
	
	public void doGameSummary()
	{
		showCountShips();
	}
}
