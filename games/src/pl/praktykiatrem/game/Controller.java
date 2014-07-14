/*
 * Plik stworzony dnia 14 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class Controller {
	static Comunicatable loadinterface = new ConsoleInteractions();

	public static void showMenu()
	{
		loadinterface.showMenu();
	}
	public void showChooseInterface()
	{
		loadinterface.showChooseInterface();
	}
    public int scanInterafaceChoice()
    {
    	return loadinterface.scanInterafaceChoice();
    }
    public char scanDirection(int polesNumber)
    {
    	return loadinterface.scanDirection(polesNumber);
    }
    public int scanXCoordinate()
    {
    	return loadinterface.scanXCoordinate();
    }
    public int scanYCoordinate()
    {
    	return loadinterface.scanYCoordinate();
    }
	public String scanName()
	{
		return loadinterface.scanName();
	}
	public void showErrorMessage1()
	{
		loadinterface.showErrorMessage1();
	}
	public void clearConsole()
	{
		loadinterface.clearConsole();
	}
	public void showGameSummary(Player g, Player h)
	{
		loadinterface.showGameSummary(g, h);
	}
	public void showYourMove(Player gamer)
	{
		loadinterface.showYourMove(gamer);
	}
	public void showGameOver()
	{
		loadinterface.showGameOver();
	}
	public void showGameOver(Player gamer)
	{
		loadinterface.showGameOver(gamer);
	}
	public int[] scanDropCoords()
	{
		return loadinterface.scanDropCoords();
	}
	public void showHitMessage()
	{
		loadinterface.showHitMessage();
	}
	public void showMissMessage()
	{
		loadinterface.showMissMessage();
	}
	}
