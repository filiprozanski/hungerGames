/*
 * Plik stworzony dnia 14 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;


public class Controller {
	static Comunicatable loadinterface = new ConsoleInteractions();

	public static void showMenu()
	{
		loadinterface.showMenu();
	}
	public static void showChooseInterface()
	{
		loadinterface.showChooseInterface();
	}
    public static int scanInterafaceChoice()
    {
    	return loadinterface.scanInterafaceChoice();
    }
    public static char scanDirection(int polesNumber)
    {
    	return loadinterface.scanDirection(polesNumber);
    }
    public static int scanXCoordinate()
    {
    	return loadinterface.scanXCoordinate();
    }
    public static int scanYCoordinate()
    {
    	return loadinterface.scanYCoordinate();
    }
	public static String[] scanName()
	{
		return loadinterface.scanName();
	}
	public static void showErrorMessage1()
	{
		loadinterface.showErrorMessage1();
	}
	public static void clearConsole()
	{
		loadinterface.clearConsole();
	}
	public static void showGameSummary(Player g, Player h)
	{
		loadinterface.showGameSummary(g, h);
	}
	public static void showYourMove(Player gamer)
	{
		loadinterface.showYourMove(gamer);
	}
	public static void showGameOver()
	{
		loadinterface.showGameOver();
	}
	public static void showGameOver(Player gamer)
	{
		loadinterface.showGameOver(gamer);
	}
	public static int[] scanDropCoords()
	{
		return loadinterface.scanDropCoords();
	}
	public static void showHitMessage()
	{
		loadinterface.showHitMessage();
	}
	public static void showMissMessage()
	{
		loadinterface.showMissMessage();
	}
	public static void showLegend()
	{
		loadinterface.showLegend();
	}
	}
