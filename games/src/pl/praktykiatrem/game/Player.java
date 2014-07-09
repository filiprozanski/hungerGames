/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class Player {
    private Board plansza;
    private Ship[] ships;
    private int shipsNumber;
    private String name;
    
    public Player(String name)
    {
        shipsNumber = 7;
        this.name = name;
        plansza = new Board(10, 10);
        ships = new Ship[shipsNumber];
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
  
    public void placeShips(int id, int polesNumber, char direction, int x, int y)
    {
        ships[id-1] = new Ship(polesNumber);
        if (direction == 'H' || direction == 'h')
        {
            //plansza = new Board(10,10);
            plansza.gameBoard[x][y]=id+48;
        }
        else if (direction == 'V' || direction == 'v')
        {
           
        }
        
    }
}
