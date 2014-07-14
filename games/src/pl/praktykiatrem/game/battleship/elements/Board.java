package pl.praktykiatrem.game.battleship.elements;


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
    private Place[][] gameBoard;
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
   
    public Board(int horizontal, int vertical)
    {
        horizontalSize = horizontal;
        verticalSize = vertical;
        
        gameBoard = new Place[verticalSize][horizontalSize];
        fillGameBoard();
    }
    
    private void fillGameBoard()
    {
        for (int i = 0; i < verticalSize; i++)
        {
            for (int j = 0; j < horizontalSize; j++)
                gameBoard[j][i] = new Place();
        }
    }
    

    public Place getPlaceFromGameBoard(int horizontal, int vertical){
        return gameBoard[vertical][horizontal];
    }
    
    public Place[][] getGameBoard()
    {
        return gameBoard;
    }
}
