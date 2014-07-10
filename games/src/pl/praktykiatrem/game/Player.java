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
            plansza.gameBoard[x][y]=(char)(id+'0');
            for(int i=1;i<polesNumber;i++)
        		plansza.gameBoard[x+i][y]=(char)(id+'0');
        }
        else if (direction == 'V' || direction == 'v')
        {
        	plansza.gameBoard[x][y]=(char)(id+'0');
        	for(int i=1;i<polesNumber;i++)
        		plansza.gameBoard[x][y+i]=(char)(id+'0');
        }
    }
    
    public boolean makeMove(int x, int y, Board plansza)
    {
        if (plansza.gameBoard[y][x] == 'c' || plansza.gameBoard[y][x] == 'C')
        {
            plansza.gameBoard[y][x] = 'M';
            return false;
        }
        else
        {
            char shipID = plansza.gameBoard[y][x];
            switch (shipID)
            {
            case '1':
                plansza.gameBoard[y][x] = 'H';
                return true;
            case '2':
                plansza.gameBoard[y][x] = 'H';
                return true;
            case '3':
                plansza.gameBoard[y][x] = 'H';
                return true;
            case '4':
                plansza.gameBoard[y][x] = 'H';
                return true;
            case '5':
                plansza.gameBoard[y][x] = 'H';
                return true;
            case '6':
                plansza.gameBoard[y][x] = 'H';
                return true;
            case '7':
                plansza.gameBoard[y][x] = 'H';
                return true;
            default:
                return false;
            }
        }
    }
}

