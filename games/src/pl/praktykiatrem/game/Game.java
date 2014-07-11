/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class Game{
    private Player A;
    private Player B;
    
    public Game()
    {
        GameFactory start = new GameFactory();
        A = start.getA();
        B = start.getB();
    }
    
    public void gameInProgress()
    {
        ConsoleInterface.showMenu();
        A.setName(ConsoleInterface.scanName());
        B.setName(ConsoleInterface.scanName());
        ConsoleInterface.showYourMove(A);
        initializeShips(A);
        ConsoleInterface.showYourMove(B);
        initializeShips(B);
        
        Player currentPlayer = A;
        Player enemy = B;
        int[] cords = {0, 0};
        
        while (!isGameOver(enemy))
        {	
        	ConsoleInterface.clearConsole();
            BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
            PlayerStatus.showYourMove(currentPlayer);
            cords = Coordinate.pointRifle();
            if (!currentPlayer.makeMove(cords[0], cords[1], enemy))
            {
                ConsoleInterface.showMissMessage();
                enemy = currentPlayer;
                currentPlayer = changePlayer(currentPlayer);
            }
            else
                ConsoleInterface.showHitMessage();
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
}
