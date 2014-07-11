/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

class Coordinate
{
    public static int[] pointRifle()
    {
        int[] tab = {0, 0};
        
        while (true)
        {
            tab = ConsoleInterface.scanDropCoords();
            if (tab[0] >= 0 && tab[0] <= 9 && tab[1] >= 0 && tab[1] <= 9)
                return tab;
        }
    }
    
    public static void fetchShipCoords(int polesNumber, int id, Player gamer)
    {
        char dir;
        int x;
        int y;
        
        while(true) {
            dir = ConsoleInterface.scanDirection(polesNumber);
            x = ConsoleInterface.scanXCoordinate();
            y = ConsoleInterface.scanYCoordinate();
        
            if(ConsoleInterface.isPlaceValid(dir, x, y, polesNumber) && gamer.placeShips(id, polesNumber, dir, x, y))
            {
                BoardDrawing.drawGameBoardForPlayer(gamer.getPlansza());
                break;
            }
            ConsoleInterface.showErrorMessage1();
        }
    }
}
