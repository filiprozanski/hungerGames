/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

//import pl.praktykiatrem.game.battleship.gameComponents.Place;
import pl.praktykiatrem.game.battleship.gameComponents.Board;
import pl.praktykiatrem.game.battleship.gameComponents.Ship;


public class GameStatus {
    private Board plansza;
    private Ship[] ships;
    private int shipsNumber;
    private Player gamer;
    //private String name;
    
    public GameStatus()
    {
        shipsNumber = 7;
        plansza = new Board(10, 10);
        ships = new Ship[shipsNumber];
        gamer = new Player();
    }
    
    public String getName()
    {
        //return name;
        return gamer.getName();
    }
    
    public void setName(String name)
    {
        gamer.setName(name);
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
    	if (!plansza.isShipOnPlace(x, y))
		{
		    plansza.placeShip(x, y, id);
		    return true;
		}
		else
			return false;
    	
    }
    public boolean placeShips(int id, int polesNumber, char direction, int x, int y)
    {
    	int x_temp=x;
    	int y_temp=y;
        ships[id] = new Ship(polesNumber);
        for(int i = 0; i < polesNumber; i++){
        	if (direction == Direction.HORIZONTAL.getAbbreviation())
        		x_temp=x+i;
        	else if (direction == Direction.VERTICAL.getAbbreviation())
        		y_temp=y+i;
        	if(!putShipOnPlace(id, x_temp, y_temp))
    			return false;   
        }
        return true;
    }
    
    public boolean makeMove(int x, int y, GameStatus enemy)
    {
        //if (ValidationInstruments.isPlaceClear(enemy.getPlansza().gameBoard, x, y))
        if (!enemy.getPlansza().isShipOnPlace(x, y))
        {
            enemy.getPlansza().takeOut(x, y);
            return false;
        }
        else
        {
            if(enemy.getPlansza().isShipOnPlaceAndActive(x, y))
            {
                int shipID = enemy.getPlansza().getShipID(x, y);
                enemy.getPlansza().takeOut(x, y);
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

enum Direction
{
    HORIZONTAL('H'), VERTICAL('V');
    
    private char abbreviation;
    
    private Direction(char abb)
    {
        abbreviation = abb;
    }
    
    public char getAbbreviation()
    {
        return abbreviation;
    }
}

