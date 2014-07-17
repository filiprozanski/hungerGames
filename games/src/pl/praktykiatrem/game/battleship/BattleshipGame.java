/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship;

import java.io.FileNotFoundException;

import pl.praktykiatrem.game.battleship.console.BoardDrawing;
import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;
import pl.praktykiatrem.game.battleship.factory.GameFactory;
import pl.praktykiatrem.game.battleship.factory.ShipLoader;

public class BattleshipGame{
    private GameStatus A;
    private GameStatus B;
    private Controller gameControl;
    
    public BattleshipGame(Boolean swing)
    {
        GameFactory start = new GameFactory();
        A = start.createPlayer();
        B = start.createPlayer();
        if(swing==true)
        	gameControl = new Controller(true);
		else
			gameControl = new Controller(false);
        
    }
    
    public void gameInProgress()  
    {
        gameControl.showMenu();      
      
        A.setName(gameControl.scanName(1));
        B.setName(gameControl.scanName(2));
   
        setShips(A);
        setShips(B);
        
        GameStatus currentPlayer = A;
        GameStatus enemy = B;
        int[] cords = {0, 0};
        gameControl.showLegend();
        while (!isGameOver(enemy))
        {	
        	
            BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
            gameControl.showYourMove(currentPlayer);
            cords = pointRifle();
            if (!currentPlayer.makeMove(cords[0], cords[1], enemy))
            {
                gameControl.showMissMessage();
                enemy = currentPlayer;
                currentPlayer = changePlayer(currentPlayer);
            }
            else
            {
                gameControl.showHitMessage();
            }
                
            gameControl.showGameSummary(currentPlayer, enemy);
        }
        gameControl.showGameOver(currentPlayer);
        gameControl.showGameOver();
    }
    
    /**
     * 
     * Metoda <code>pointRifle</code> pobiera wsp�rz�dne do strza�u
     *
     * @return tablica int[], gdzie [0] to wsp�rz�dna x, a [1] to wsp�rz�dna y
     */
    private int[] pointRifle()
    {
        int[] tab = {0, 0};
        
        while (true)
        {
            tab = gameControl.scanCoords();
            if (tab[0] >= 0 && tab[0] <= 9 && tab[1] >= 0 && tab[1] <= 9)
                return tab;
            else
                gameControl.showErrorMessage1();
        }
    }
    
    private GameStatus changePlayer(GameStatus X)
    {
        if (X.getName() == A.getName())
            return B;
        else
            return A;
    }
    
    private boolean isGameOver(GameStatus X)
    {
    	if (X.getShipsNumber()>0)
    		return false;
    	else
    		return true;
    }
    
    private void setShips(GameStatus gamer)
    {
    	try {
        	ShipLoader.initializeShipsFromFile(gamer);
          } catch (FileNotFoundException e) {
        	  ShipLoader.initializeShips(gamer, gameControl);
          }
    }
    
    
}
