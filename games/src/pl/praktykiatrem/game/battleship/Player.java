/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

import pl.praktykiatrem.game.battleship.elements.Board;
import pl.praktykiatrem.game.battleship.elements.Place;
import pl.praktykiatrem.game.battleship.elements.Ship;


public class Player {
    private Board plansza;
    private Ship[] ships;
    private int shipsNumber;
    private String name;
    
    public Player()
    {
        shipsNumber = 7;
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
            	if (!plansza.getPlaceFromGameBoard(x + i, y).isShipOnPlace())
            	{
            	    plansza.getPlaceFromGameBoard(x + i, y).putShipOnPlace();
            	    plansza.getPlaceFromGameBoard(x + i, y).setShipID(id);
            	}
            	else
            		return false;
        }
        else if (direction == 'V' || direction == 'v')
        {
        	for(int i = 0; i < polesNumber; i++)
        		if (!plansza.getPlaceFromGameBoard(x, y + i).isShipOnPlace())
        		{
        		    plansza.getPlaceFromGameBoard(x, y + i).putShipOnPlace();
        		    plansza.getPlaceFromGameBoard(x, y + i).setShipID(id);
        		}
        		else
        			return false;
        }
        
        return true;
    }
    
    public boolean makeMove(int x, int y, Player enemy)
    {
        //if (ValidationInstruments.isPlaceClear(enemy.getPlansza().gameBoard, x, y))
        if (!enemy.getPlansza().getPlaceFromGameBoard(x, y).isShipOnPlace())
        {
            enemy.getPlansza().getPlaceFromGameBoard(x, y).takeOut();
            return false;
        }
        else
        {
            Place placeUnderMove = enemy.getPlansza().getPlaceFromGameBoard(x, y);
            if(placeUnderMove.isShipOnPlace() && placeUnderMove.isPlaceInGame())
            {
                int shipID = placeUnderMove.getShipId();
                placeUnderMove.takeOut();
                ships[shipID].reducePolesNumber();
                if (ships[shipID].isShipSunk())
                    enemy.reduceShipsNumber();
                return true;
            }
            else
            	return false;
        }
    }
    
    public void reduceShipsNumber()
    {
        shipsNumber--;
    }
}

