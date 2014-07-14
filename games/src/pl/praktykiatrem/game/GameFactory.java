/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class GameFactory {
    private Player A;
    private Player B;
    
    public GameFactory()
    {
        initializePlayers();
    }
    
    private void initializePlayers()
    {
        A = new Player(/*ConsoleInteractions.scanName()*/);
        B = new Player(/*ConsoleInteractions.scanName()*/);
    }
    
    public Player getA()
    {
        return A;
    }
    
    public Player getB()
    {
        return B;
    }
}
