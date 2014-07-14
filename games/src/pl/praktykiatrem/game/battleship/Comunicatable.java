/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

public interface Comunicatable {	 
    public void showMenu();
    public char scanDirection(int polesNumber);
	public String[] scanName();
	public void showErrorMessage1();
	public void clearConsole();
	public void showGameSummary(Player g, Player h);
	public void showYourMove(Player gamer);
	public void showGameOver();
	public void showGameOver(Player gamer);
	public int[] scanCoords();    
	public void showHitMessage();
	public void showMissMessage();
	public void showLegend();
}
