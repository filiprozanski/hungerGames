package pl.praktykiatrem.game.battleship.elements;

public class Ship
{
    private int polesNumber;
    //private Coordinate direction;
    
    public Ship(int polesNumber)
    {
        this.polesNumber = polesNumber;
    }
    
    public void determineDirection()
    {
        
    }
    
    public boolean reducePolesNumber()
    {
        polesNumber--;
        if (polesNumber == 0)
            return true;
        else
            return false;
    }
    
    public int getPolesNumber()
    {
        return polesNumber;
    }
    
    public boolean isShipSunk()
    {
        if (polesNumber == 0)
            return true;
        else
            return false;
    }
}
