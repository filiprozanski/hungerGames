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
       
        initializeShips(A);
        initializeShips(B);
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
    
    /**
     * 
     * Metoda <code>pointRifle</code> pobiera wsp�rz�dne do strza�u
     *
     * @return tablica int[], gdzie [0] to wsp�rz�dna x, a [1] to wsp�rz�dna y
     */
    private static int[] pointRifle()
    {
        int[] tab = {0, 0};
        
        while (true)
        {
            tab = ConsoleInterface.scanDropCoords();
            if (tab[0] >= 0 && tab[0] <= 9 && tab[1] >= 0 && tab[1] <= 9)
                return tab;
        }
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
    
    
}
