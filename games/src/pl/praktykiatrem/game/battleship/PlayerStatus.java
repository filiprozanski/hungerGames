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


public class PlayerStatus {
    private Board plansza;
    private Ship[] ships;
    private int shipsNumber;
    private Player gamer;
    //private String name;
    
    public PlayerStatus()
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
    
    public void reduceShipsNumber()
    {
        shipsNumber--;
    }
    
    public void setShip(int id, int polesNumber)
    {
    	ships[id] = new Ship(polesNumber);
    }
    
    public Ship getShip(int id)
    {
    	return ships[id];
    }
    
    public void takeOutShip(int x, int y)
    {
    	plansza.takeOut(x, y);
    }
}

