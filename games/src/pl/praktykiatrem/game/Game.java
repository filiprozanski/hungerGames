/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class Game {
    private Player A;
    private Player B;
    private boolean gameOver;
    
    public Game() {
        GameFactory start = new GameFactory();
        A = start.getA();
        B = start.getB();
        
        gameOver = false;
    }
    
    public void gameInProgress()
    {
        
    }
}
