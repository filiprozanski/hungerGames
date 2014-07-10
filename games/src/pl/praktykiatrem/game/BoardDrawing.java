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
        
        for (char[] a : tab)
        {
            for (char b : a)
            {
                if (b == 'M' || b == 'H')
                {
                    System.out.print(b);
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
        
        for (char[] a : tab)
        {
            for (char b : a)
            {
                System.out.print();
            }
            System.out.println();
        }
    }
}
