/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

import java.util.Scanner;

/**
 * 
 *	Klasa <code>ConsoleInteractions</code> zawiera funkcje statyczne wy¶wietlaj±ce w konsoli komunikaty, oraz pobieraj±ce od u¿ytkownika niezbêdnê dane
 *
 * @author filipr
 * @version 11 lip 2014 12:36:18
 *
 */
public class ConsoleInteractions implements Comunicatable{
    private static Scanner in = new Scanner(System.in);
    
    public static void showMenu()
    {
        System.out.println("Witamy w grze w statki, za chwile zostaniesz przeniesiony na pole bitwy!");
    }
    
    public static void showChooseInterface()
    {
        System.out.println("Wybierz 1 aby wczytaæ statki z pliku lub 2 aby wpisaæ je rêcznie");
    }
    
    public static int scanInterafaceChoice()
    {
        System.out.print("Decyzja: ");
        int input = in.nextInt();
        return input;
    }
    
    public static char scanDirection(int polesNumber)
    {
        System.out.print("Ustawiasz " + polesNumber + "-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo):");
        String input = in.next();
        return input.charAt(0);
    }
    
    public static int scanXCoordinate()
    {
        System.out.print("Podaj wspó³rzêdn± poziom±. (0 - 9)\nX = ");
        return in.nextInt();
    }
    
    public static int scanYCoordinate()
    {
        System.out.print("Podaj wspó³rzêdn± pionow±. (0 - 9)\nY = ");
        return in.nextInt();
    }
    
    public static String scanName()
    {
        System.out.print("Podaj swoje imiê: ");
        return in.next();
    }
    
    public static void showErrorMessage1()
    {
        System.out.println("Poda³e¶ b³êdne dane, spróbuj jeszcze raz.");
    }
    
    public static void clearConsole()
    {
    	for (int i = 0; i < 20; i++)
    		System.out.println();
    }
    
    public static void showCountShips(Player g){
    	System.out.print(g.getName() + " ma ");
    	System.out.print(g.getShipsNumber());
    	System.out.println(" statków.");    	
    }
    
	public static void showGameSummary(Player g, Player h)
	{
		ConsoleInteractions.showCountShips(g);
		ConsoleInteractions.showCountShips(h);
	}
	
    public static void showYourMove(Player gamer)
    {
    	System.out.println("Twój ruch: " + gamer.getName());
    }
    
    public static void showGameOver()
    {
    	System.out.println("Koniec gry !");
    }
    
    public static void showGameOver(Player gamer)
    {
   		System.out.println("Wygra³: " + gamer.getName());		
    }
    
    public static int[] scanDropCoords()
    {
        int[] tab = {0, 0};
        
        System.out.print("Podaj kordynaty uderzenia:\nX = ");
        tab[0] = in.nextInt();
        System.out.print("Y = ");
        tab[1] = in.nextInt();
        
        return tab;
    }
    
    public static void showHitMessage()
    {
        System.out.println("Brawo! Trafi³e¶!");
    }
    
    public static void showMissMessage()
    {
        System.out.println("Pud³o...");
    }
}

