package pl.praktykiatrem.game;

/**
 * 
 *	Klasa <code>Board</code> reprezentuje planszê do gry
 *
 * @author filipr
 * @version 11 lip 2014 12:31:46
 *
 */
public class Board {
    /**
     * tablica dwuwymiarowa[y][x] przechowujaca stany poszczególnych pól
     * C - puste pole; '0' - '6' - ustawiony statek; M - oddany niecelny strza³; H - oddany celny strza³
     */
    public char[][] gameBoard;
    private int placesHorizontal;
    private int placesVertical;

    public int getPlacesVertical()
    {
        return placesVertical;
    }

    public int getPlacesHorizontal()
    {
        return placesHorizontal;
    }
   
    Board(int horizontal, int vertical)
    {
        placesHorizontal = horizontal;
        placesVertical = vertical;
        
        gameBoard = new char[placesVertical][placesHorizontal];
        fillZeroGameBoard();
    }
    
    private void fillZeroGameBoard()
    {
        for (int i = 0; i < placesVertical; i++)
        {
            for (int j = 0; j < placesHorizontal; j++)
                gameBoard[j][i] = 'C';
        }
    }
    

    public int getState(int horizontal, int vertical){
        return gameBoard[vertical][horizontal];
    }
}
