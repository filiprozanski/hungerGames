/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class NewGame {
    private Player A;
    private Player B;
    
    public NewGame()
    {
        initializePlayers();
    }
    
    private void initializePlayers()
    {
        A.setName(ConsoleInterface.scanString());
        B.setName(ConsoleInterface.scanString());
    }
}
