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
    
    private boolean putShipOnPlace(int id, int x, int y)
    {
    	if (!plansza.getPlaceFromGameBoard(x, y).isShipOnPlace())
		{
		    plansza.getPlaceFromGameBoard(x, y).setShipOnPlace();
		    plansza.getPlaceFromGameBoard(x, y).setShipID(id);
		}
		else
			return false;
    	return true;
    }
    public boolean placeShips(int id, int polesNumber, char direction, int x, int y)
    {
    	int x_temp=x;
    	int y_temp=y;
        ships[id] = new Ship(polesNumber);
        for(int i = 0; i < polesNumber; i++){
        	if (direction == 'H' || direction == 'h')
        		x_temp=x+i;
        	else if (direction == 'V' || direction == 'v')
        		y_temp=y+i;
        	if(!putShipOnPlace(id, x_temp, y_temp))
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

