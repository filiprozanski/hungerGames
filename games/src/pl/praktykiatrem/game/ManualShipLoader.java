/*
 * Plik stworzony dnia 11 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class ManualShipLoader implements ShipLoadingInterface {
    public void initializeShips(Player gamer)
    {
            int polesNumber;
            ConsoleInterface.showYourMove(gamer);
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
    
    /**
     * 
     * Metoda <code>fetchShipCoords</code> pobiera dane do ustawienia statku
     *
     * @param polesNumber liczba masztów
     * @param id
     * @param gamer gracz, którego statki s± ustawiane
     */
    private static void fetchShipCoords(int polesNumber, int id, Player gamer)
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
