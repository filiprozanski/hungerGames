/*
 * Plik stworzony dnia 10 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import pl.praktykiatrem.game.battleship.gameComponents.Board;


/**
 * 
 *	Klasa <code>BoardDrawing</code> przechowuje statyczne metody do wy¶wietlania w konsoli planszy do gry
 *
 * @author filipr
 * @version 11 lip 2014 12:26:12
 *
 */
public class BoardDrawing {
    /**
     * 
     * Metoda <code>drawGameBoardForOpponent</code> wypisuje w konsoli plansze przeciwnika maskujac ustawione na niej statki, pozosta³e stany wy¶wietlaj± siê normalnie
     *
     * @param gamePlace reprezentuje tablice do wypisania
     */
    public static void drawGameBoardForOpponent(Board gamePlace)
    {
        //Place[][] tab = gamePlace.getGameBoard();
        
        System.out.print(" ");
        for (int i = 0; i < gamePlace.getHorizontalSize(); i++)
            System.out.print(i);
        
        System.out.println();
        
        for (int i = 0; i < gamePlace.getVerticalSize(); i++)
        {
            System.out.print(i);
            for (int j = 0; j < gamePlace.getHorizontalSize(); j++)
            {
                if (!gamePlace.isShipOnPlace(j, i) && !gamePlace.isPlaceActive(j, i))
                    System.out.print("M");
                else if (gamePlace.isShipOnPlace(j, i) && !gamePlace.isPlaceActive(j, i))
                    System.out.print("H");
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }
    
    /**
     * 
     * Metoda <code>drawGameBoardForPlayer</code> wypisuje w konsoli tablicê gracza ze wszystkimi stanami
     *
     * @param gamePlace reprezentuje tablice do wypisania
     */
    public static void drawGameBoardForPlayer(Board gamePlace)
    {
        //Place[][] tab = gamePlace.getGameBoard();
        
        System.out.print(" ");
        for (int i = 0; i < gamePlace.getHorizontalSize(); i++)
            System.out.print(i);
        
        System.out.println();
        
        for (int i = 0; i < gamePlace.getVerticalSize(); i++)
        {
            System.out.print(i);
            for (int j = 0; j < gamePlace.getHorizontalSize(); j++)
            {
                if (gamePlace.isShipOnPlace(j, i) && gamePlace.isPlaceActive(j, i))
                    System.out.print(gamePlace.getShipID(j, i));
                else if (gamePlace.isShipOnPlace(j, i) && !gamePlace.isPlaceActive(j, i))
                    System.out.print("H");
                else if (!gamePlace.isShipOnPlace(j, i) && !gamePlace.isPlaceActive(j, i))
                    System.out.print("M");
                else
                    System.out.print("+");
            }
            System.out.println();
        }
    }
}
