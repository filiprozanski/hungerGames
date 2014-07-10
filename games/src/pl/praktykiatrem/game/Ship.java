package pl.praktykiatrem.game;

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
    
    public void reducePolesNumber()
    {
        polesNumber--;
    }
    
    public int getPolesNumber()
    {
        return polesNumber;
    }
}
