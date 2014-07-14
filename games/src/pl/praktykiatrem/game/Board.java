package pl.praktykiatrem.game;

/**
 * 
 *	Klasa <code>Board</code> reprezentuje plansz� do gry
 *
 * @author filipr
 * @version 11 lip 2014 12:31:46
 *
 */
public class Board {
    /**
     * tablica dwuwymiarowa[y][x] przechowujaca stany poszczeg�lnych p�l
     * C - puste pole; '0' - '6' - ustawiony statek; M - oddany niecelny strza�; H - oddany celny strza�
     */
    public char[][] gameBoard;
    private int horizontalSize;
    private int verticalSize;

    public int getVerticalSize()
    {
        return verticalSize;
    }

    public int getHorizontalSize()
    {
        return horizontalSize;
    }
   
    Board(int horizontal, int vertical)
    {
        horizontalSize = horizontal;
        verticalSize = vertical;
        
        gameBoard = new char[verticalSize][horizontalSize];
        fillZeroGameBoard();
    }
    
    private void fillZeroGameBoard()
    {
        for (int i = 0; i < verticalSize; i++)
        {
            for (int j = 0; j < horizontalSize; j++)
                gameBoard[j][i] = 'C';
        }
    }
    

    public int getState(int horizontal, int vertical){
        return gameBoard[vertical][horizontal];
    }
}
