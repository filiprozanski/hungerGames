/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

/**
 * 
 * @author Wiktor
 *
 */
class Coordinate
{
    private int x;
    private int y;
    
    Coordinate(int aX, int aY)
    {
        x = aX;
        y = aY;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public String toString()
    {
        return "X = " + x + "; Y = " + y;
    }
}
