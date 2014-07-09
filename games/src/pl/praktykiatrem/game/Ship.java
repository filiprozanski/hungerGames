package pl.praktykiatrem.game;

import java.util.Scanner;

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
    
    public Coordinate getDirection()
    {
        return null;
    }
}

/*public class Ship {
	private Coordinate[] position;
	private int polesNumber;
	private boolean isInGame;
	private Coordinate direction;
	private Board gameBoard;
	
	Ship(int polesNumber, Board gB)
	{
		this.polesNumber = polesNumber;
		position = new Coordinate[polesNumber];
		isInGame = false;
		gameBoard = gB;
	}
	
	public void placeShip()
	{
		Scanner scan = new Scanner(System.in);
		int x;
		int y;
		
		System.out.print("x = ");
		x = scan.nextInt();
		System.out.print("y = ");
		y = scan.nextInt();
		if (gameBoard.setPlace(x, y))
		{
			position[0] = new Coordinate(x, y);
		}
		
		System.out.println();
		System.out.print("x = ");
		x = scan.nextInt();
		System.out.print("y = ");
		y = scan.nextInt();
		if (doSecondMoveValidation(x, y))
		{
			position[1] = new Coordinate(x, y);
		}
		
		setDirection();
		
		for (int i = 2; i < polesNumber; i++)
		{
			System.out.println();
			System.out.print("x = ");
			x = scan.nextInt();
			System.out.print("y = ");
			y = scan.nextInt();
			if (doMoveValidation(x, y))
			{
				position[i] = new Coordinate(x, y);
			}
		}
		
		isInGame = true;
	}
	
	private boolean doSecondMoveValidation(int x, int y)
	{
		if (((x == position[0].getX()) || (y == position[0].getY())) && gameBoard.setPlace(x, y)) //!!!!!
			return true;
		else
			return false;
	}
	
	private boolean doMoveValidation(int x, int y)
	{
		if (((x == direction.getX()) || (y == direction.getY())) && gameBoard.setPlace(x, y))
			return true;
		else
			return false;
	}
	
	private void setDirection() //mo¿e jaki¶ b³±d warto dodaæ
	{
		if (position[0].getX() == position[1].getX())
			direction = new Coordinate(position[0].getX(), -1);
		else
			direction = new Coordinate(-1, position[0].getY());
	}
	
	public String toString()
	{
		return "Masztow: " + polesNumber + "\n" + position[0];
	}
}*/