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

public class BattleshipGame{
    private Player A;
    private Player B;
    
    public BattleshipGame()
    {
        GameFactory start = new GameFactory();
        A = start.getA();
        B = start.getB();
    }
    
    public void gameInProgress() throws FileNotFoundException
    {
        Controller.showMenu();
        Controller.showChooseInterface();
        int decision = Controller.scanInterafaceChoice();
        ShipLoadingInterface loader;
        
        switch (decision)
        {
            case 1:
                loader = new FileShipLoader();
                break;
            case 2:
                loader = new ManualShipLoader();
                break;
            default:
                loader = new ManualShipLoader();
        }
        
        String[] names = Controller.scanName();
        A.setName(names[0]);
        B.setName(names[1]);
       
        loader.initializeShips(A);
        loader.initializeShips(B);
        
        Player currentPlayer = A;
        Player enemy = B;
        int[] cords = {0, 0};
        Controller.showLegend();
        while (!isGameOver(enemy))
        {	
        	
            BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
            Controller.showYourMove(currentPlayer);
            cords = pointRifle();
            if (!currentPlayer.makeMove(cords[0], cords[1], enemy))
            {
                Controller.clearConsole();
                Controller.showMissMessage();
                enemy = currentPlayer;
                currentPlayer = changePlayer(currentPlayer);
            }
            else
            {
                Controller.clearConsole();
                Controller.showHitMessage();
            }
                
            Controller.showGameSummary(currentPlayer, enemy);
        }
        Controller.showGameOver(currentPlayer);
        Controller.showGameOver();
    }
    
    /**
     * 
     * Metoda <code>pointRifle</code> pobiera wspó³rzêdne do strza³u
     *
     * @return tablica int[], gdzie [0] to wspó³rzêdna x, a [1] to wspó³rzêdna y
     */
    private static int[] pointRifle()
    {
        int[] tab = {0, 0};
        
        while (true)
        {
            tab = Controller.scanDropCoords();
            if (tab[0] >= 0 && tab[0] <= 9 && tab[1] >= 0 && tab[1] <= 9)
                return tab;
            else
                Controller.showErrorMessage1();
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
