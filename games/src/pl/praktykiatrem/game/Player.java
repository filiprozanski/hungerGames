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
    private PlayerStatus stats;
    
    public Player(String name)
    {
        shipsNumber = 7;
        this.name = name;
        plansza = new Board(10, 10);
        ships = new Ship[shipsNumber];
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getShipsNumber()
    {
    	return shipsNumber;
    }
    
    public Board getPlansza()
    {
        return plansza;
    }
  
    public boolean placeShips(int id, int polesNumber, char direction, int x, int y)
    {
        ships[id] = new Ship(polesNumber);
        if (direction == 'H' || direction == 'h')
        {
            for(int i = 0; i < polesNumber; i++)
            	if (plansza.gameBoard[y][x + i] == 'C')
            	{
            		plansza.gameBoard[y][x + i]=(char)(id+'0');
            	}
            	else
            		return false;
        }
        else if (direction == 'V' || direction == 'v')
        {
        	for(int i = 0; i < polesNumber; i++)
        		if (plansza.gameBoard[y + i][x] == 'C')
        		{
        			plansza.gameBoard[y + i][x]=(char)(id+'0');
        		}
        		else
        			return false;
        }
        
        return true;
    }
    
    public int makeMove(int x, int y, Board plansza)
    {
        if (plansza.gameBoard[y][x] == 'c' || plansza.gameBoard[y][x] == 'C')
        {
            plansza.gameBoard[y][x] = 'M';
            return 0;
        }
        else
        {
            char cShipID = plansza.gameBoard[y][x];
            int shipID = (int)cShipID-50;
            if(shipID >= 0 && shipID <= 6)
            {
            	plansza.gameBoard[y][x] = 'H';
                ships[shipID].reducePolesNumber();
                if (ships[shipID].isShipSunk())
                    reduceShipsNumber();
                return 1;
            }
            else 
            {
            	return 0;
            }
        }
    }
    
    private void reduceShipsNumber()
    {
        shipsNumber--;
    }
}

