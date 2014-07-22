/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import java.io.FileNotFoundException;

import pl.praktykiatrem.game.battleship.factory.GameFactory;
import pl.praktykiatrem.game.battleship.factory.ShipLoader;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.battleship.rules.Game;

public class BattleshipGame{
    private PlayerStatus A;
    private PlayerStatus B;
    private ConsoleInteractions gameControl;
    private Game gameRules;
    private ShipLoader load;
    
    public BattleshipGame(Boolean swing)
    {
        GameFactory start = new GameFactory();
        A = start.createPlayer();
        B = start.createPlayer();
        	gameControl = new ConsoleInteractions();
		gameRules = new Game();
		load = new ShipLoader(gameRules);
        
    }
    
    public void gameInProgress()  
    {
        gameControl.showMenu();      
      
        A.setName(gameControl.scanName(1));
        B.setName(gameControl.scanName(2));
   
        setShips(A);
        setShips(B);
        
        PlayerStatus currentPlayer = A;
        PlayerStatus enemy = B;
        int[] cords = {0, 0};
        gameControl.showLegend();
        while (!isGameOver(enemy))
        {	
        	
            BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
            gameControl.showYourMove(currentPlayer);
            cords = pointRifle();
            if (!gameRules.makeMove(enemy, cords[0], cords[1]))
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
     * Metoda <code>pointRifle</code> pobiera wspó³rzêdne do strza³u
     *
     * @return tablica int[], gdzie [0] to wspó³rzêdna x, a [1] to wspó³rzêdna y
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
    
    private PlayerStatus changePlayer(PlayerStatus X)
    {
        if (X.getName() == A.getName())
            return B;
        else
            return A;
    }
    
    private boolean isGameOver(PlayerStatus X)
    {
    	if (X.getShipsNumber()>0)
    		return false;
    	else
    		return true;
    }
    
    private void setShips(PlayerStatus gamer)
    {
    	try {
        	load.initializeShipsFromFile(gamer);
          } catch (FileNotFoundException e) {
        	  load.initializeShips(gamer, gameControl, gameRules);
          }
    }
    
    
}
