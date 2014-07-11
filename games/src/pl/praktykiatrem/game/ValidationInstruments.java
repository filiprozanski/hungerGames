/*
 * Plik stworzony dnia 11 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class ValidationInstruments {
    public static boolean isPlaceClear(char[][] tab, int x, int y)
    {
        if (tab[y][x] == 'c' || tab[y][x] == 'C')
            return true;
        else
            return false;
    }
    
    public static boolean isShipOnPlace(char[][] tab, int x, int y, int id)
    {
        char cShipID = tab[y][x];
        int shipID = (int)cShipID-48;
        if(shipID >= 0 && shipID <= 6)
            return true;
        else
            return false;
    }
    
    /**
     * 
     * Metoda <code>isPlaceValid</code> sprawdza, czy statek o danych parametrach mie�ci si� na polu gry 
     *
     * @param direction okre�la czy statek b�dzie poziomo 'h', czy pionowo 'v'
     * @param x wsp�rz�dna pozioma pocz�tku statku
     * @param y wsp�rz�dna pionowa pocz�tku statku
     * @param polesNumber liczba maszt�w (wielko�� statku)
     * @return true je�eli statek zmie�ci si� na planszy, false je�eli nie
     */
    public static boolean isPlaceValid(char direction, int x, int y, int polesNumber)
    {
        if (direction == 'H' || direction == 'h')
        {
            if (x + polesNumber <= 10)
                return true;
            else
                return false;
        }
        else if (direction == 'V' || direction == 'v')
        {
            if (y + polesNumber <= 10)
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
