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
	static Comunicatable loadinterface;
	public Controller(Boolean swing)
	{
		//if(swing==true)
			//loadinterface = new SwingInteractions();
		//else
			//loadinterface = new ConsoleInteractions();	
	}
	

	public void showMenu()
	{
		loadinterface.showMenu();
	}
    public char scanDirection(int polesNumber)
    {
    	return loadinterface.scanDirection(polesNumber);
    }
	public String scanName(int i)
	{
		return loadinterface.scanName(i);
	}
	public void showErrorMessage1()
	{
		loadinterface.showErrorMessage1();
	}
	public void showGameSummary(GameStatus g, GameStatus h)
	{
		loadinterface.showGameSummary(g, h);
	}
	public void showYourMove(GameStatus gamer)
	{
		loadinterface.showYourMove(gamer);
	}
	public void showGameOver()
	{
		loadinterface.showGameOver();
	}
	public void showGameOver(GameStatus gamer)
	{
		loadinterface.showGameOver(gamer);
	}
	public int[] scanCoords()
	{
		return loadinterface.scanCoords();
	}
	public void showHitMessage()
	{
		loadinterface.showHitMessage();
	}
	public void showMissMessage()
	{
		loadinterface.showMissMessage();
	}
	public void showLegend()
	{
		loadinterface.showLegend();
	}
	}
