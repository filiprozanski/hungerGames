/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class BoardDrawing {
    public static void drawGameBoardForOpponent(Board gamePlace)
    {
        char[][] tab = gamePlace.gameBoard;
        
        System.out.print(" ");
        for (int i = 0; i < gamePlace.getPlacesHorizontal(); i++)
            System.out.print(i);
        
        System.out.println();
        
        for (int i = 0; i < gamePlace.getPlacesVertical(); i++)
        {
            System.out.print(i);
            for (int j = 0; j < gamePlace.getPlacesHorizontal(); j++)
            {
                if (tab[i][j] == 'M' || tab[i][j] == 'H')
                {
                    System.out.print(tab[i][j]);
                }
                else
                {
                    System.out.print("C");
                }
            }
            System.out.println();
        }
    }
    
    public static void drawGameBoardForPlayer(Board gamePlace)
    {
        char[][] tab = gamePlace.gameBoard;
        
        System.out.print(" ");
        for (int i = 0; i < gamePlace.getPlacesHorizontal(); i++)
            System.out.print(i);
        
        System.out.println();
        
        for (int i = 0; i < gamePlace.getPlacesVertical(); i++)
        {
            System.out.print(i);
            for (int j = 0; j < gamePlace.getPlacesHorizontal(); j++)
            {
                System.out.print(tab[i][j]);
            }
            System.out.println();
        }
    }
}
