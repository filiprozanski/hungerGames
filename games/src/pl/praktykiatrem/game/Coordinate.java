/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

/**
 * 
 *	Klasa <code>Coordinate</code> zawiera funkcje statyczne obs³uguj±ce pobieranie wspó³rzêdnych do ustawiania i strzelania
 *
 *
 * @author filipr
 * @version 11 lip 2014 12:41:12
 *
 */
class Coordinate
{
    /**
     * 
     * Metoda <code>pointRifle</code> pobiera wspó³rzêdne do strza³u
     *
     * @return tablica int[], gdzie [0] to wspó³rzêdna x, a [1] to wspó³rzêdna y
     */
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
    
    /**
     * 
     * Metoda <code>fetchShipCoords</code> pobiera dane do ustawienia statku
     *
     * @param polesNumber liczba masztów
     * @param id
     * @param gamer gracz, którego statki s± ustawiane
     */
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
