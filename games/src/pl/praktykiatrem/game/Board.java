package pl.praktykiatrem.game;

public class Board {
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
            {
                gameBoard[j][i] = 'C';
            }
        }
    }
    public int getState(int horizontal, int vertical){
        return gameBoard[horizontal][vertical];
    }
    
    public String toString()
    {
        String wyjscie = "";
        
        for (int i = 0; i < placesHorizontal; i++)
        {
            wyjscie = wyjscie + "-";
        }
        
        wyjscie = wyjscie + "\n";
        
        for (int i = 0; i < placesVertical; i++)
        {
            for (int j = 0; j < placesHorizontal; j++)
                wyjscie = wyjscie + gameBoard[i][j];
            wyjscie = wyjscie + "\n";
        }
        
        for (int i = 0; i < placesHorizontal; i++)
        {
            wyjscie = wyjscie + "-";
        }
        
        wyjscie = wyjscie + "\n";
        
        return wyjscie;
    }
}
