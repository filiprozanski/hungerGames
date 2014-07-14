/*
 * Plik stworzony dnia 9 lip 2014 przez filiprbla bla
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

class Place
{
    private int shipID;
    private boolean shipOnPlace;
    private boolean isInGame;
    
    Place()
    {
        shipOnPlace = false;
        isInGame = true;
    }
    
    public void putShipOnPlace()
    {
        shipOnPlace = true;
    }
    
    public boolean isShipOnPlace()
    {
        return shipOnPlace;
    }
    
    public boolean isPlaceInGame()
    {
        return isInGame;
    }
    
    public void setShipID(int ID)
    {
        shipID = ID;
    }
    
    public int getShipId()
    {
        return shipID;
    }
    
    public void takeOut()
    {
        isInGame = false;
    }
}
