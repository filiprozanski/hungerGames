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
    
    private void initializeShips(Player gamer)
    {
        int polesNumber;
        
            polesNumber = 6;
            fetchShipCoords(polesNumber, 0, gamer);
        
            polesNumber = 4;
        
            for (int i = 1; i < 7; i++)
            {
                fetchShipCoords(polesNumber, i, gamer);
                i++;
                fetchShipCoords(polesNumber, i, gamer);
                polesNumber--;
            }
    }
    
    private void fetchShipCoords(int polesNumber, int id, Player gamer)
    {
        char dir;
        int x;
        int y;
        
        while(true) {
            dir = ConsoleInterface.scanDirection(polesNumber);
            x = ConsoleInterface.scanXCoordinate();
            y = ConsoleInterface.scanYCoordinate();
        
            if(ConsoleInterface.isPlaceValid(dir, x, y, polesNumber))
            {
                gamer.placeShips(id, polesNumber, dir, x, y);
                break;
            }
            
            ConsoleInterface.showErrorMessage1();
        }
    }
}
