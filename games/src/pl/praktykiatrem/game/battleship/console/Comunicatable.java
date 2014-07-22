/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public interface Comunicatable {	 
    public void showMenu();
    public char scanDirection(int polesNumber);
	public String scanName(int i);
	public void showErrorMessage1();
	public void showGameSummary(PlayerStatus g, PlayerStatus h);
	public void showYourMove(PlayerStatus gamer);
	public void showGameOver();
	public void showGameOver(PlayerStatus gamer);
	public int[] scanCoords();    
	public void showHitMessage();
	public void showMissMessage();
	public void showLegend();
}