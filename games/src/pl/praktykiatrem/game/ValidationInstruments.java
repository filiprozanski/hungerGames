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
}
