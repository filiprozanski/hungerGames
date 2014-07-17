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
	public String scanName(int i);
	public void showErrorMessage1();
	public void showGameSummary(GameStatus g, GameStatus h);
	public void showYourMove(GameStatus gamer);
	public void showGameOver();
	public void showGameOver(GameStatus gamer);
	public int[] scanCoords();    
	public void showHitMessage();
	public void showMissMessage();
	public void showLegend();
}
