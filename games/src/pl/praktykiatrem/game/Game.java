/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Game{
    private Player A;
    private Player B;
    
    public Game()
    {
        GameFactory start = new GameFactory();
        A = start.getA();
        B = start.getB();
    }
    
    public void gameInProgress() throws FileNotFoundException
    {
        ConsoleInterface.showMenu();
        A.setName(ConsoleInterface.scanName());
        B.setName(ConsoleInterface.scanName());
       
        initializeShipsFromFile(A);
        initializeShipsFromFile(B);
        //initializeShips(A);
        //initializeShips(B);
        
        Player currentPlayer = A;
        Player enemy = B;
        int[] cords = {0, 0};
        
        while (!isGameOver(enemy))
        {	
        	
            BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
            PlayerStatus.showYourMove(currentPlayer);
            cords = Coordinate.pointRifle();
            if (!currentPlayer.makeMove(cords[0], cords[1], enemy))
            {
                ConsoleInterface.clearConsole();
                ConsoleInterface.showMissMessage();
                enemy = currentPlayer;
                currentPlayer = changePlayer(currentPlayer);
            }
            else
            {
                ConsoleInterface.clearConsole();
                ConsoleInterface.showHitMessage();
            }
                
            PlayerStatus.doGameSummary(currentPlayer, enemy);
        }
        ConsoleInterface.showGameOver(currentPlayer);
        ConsoleInterface.showGameOver();
    }
    
    private Player changePlayer(Player X)
    {
        if (X.getName() == A.getName())
            return B;
        else
            return A;
    }
    
    private boolean isGameOver(Player X)
    {
    	if (X.getShipsNumber()>0)
    		return false;
    	else
    		return true;
    }
    
    private void initializeShips(Player gamer)
    {
            int polesNumber;
            ConsoleInterface.showYourMove(gamer);
            polesNumber = 6;
            Coordinate.fetchShipCoords(polesNumber, 0, gamer);
        
            polesNumber = 4;
            
            for (int i = 1; i < 7; i++)
            {
                Coordinate.fetchShipCoords(polesNumber, i, gamer);
                i++;
                Coordinate.fetchShipCoords(polesNumber, i, gamer);
                polesNumber--;
            }
    }
    
    public void initializeShipsFromFile(Player gamer) throws FileNotFoundException {
        File plik1=new File("src/pl/praktykiatrem/game/"+gamer.getName()+".txt");
        Scanner odczyt=new Scanner(plik1);
        String temp;
        while (odczyt.hasNextLine()){
        	for(int i=0;i<7;i++){    	
        		temp=odczyt.nextLine();
        		gamer.placeShips(i, (int)temp.charAt(0)-48, temp.charAt(2), (int)temp.charAt(4)-48, (int)temp.charAt(6)-48);    		
        	
        	}
        }
        odczyt.close();  
          		 
    }
}
