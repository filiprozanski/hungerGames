/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

import java.util.Scanner;

public class ConsoleInterface implements Comunicatable{
    private static Scanner in = new Scanner(System.in);
    
    public static void showMenu()
    {
        System.out.println("Witamy w grze w statki, za chwile zostaniesz przeniesiony na pole bitwy!");
    }
    
    public static char scanDirection(int polesNumber)
    {
        System.out.println("Ustawiasz " + polesNumber + "-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo)");
        String input = in.next();
        return input.charAt(0);
    }
    
    public static int scanXCoordinate()
    {
        System.out.println("Podaj wspó³rzêdn± poziom±. (1 - 10)\nX = ");
        return in.nextInt();
    }
    
    public static int scanYCoordinate()
    {
        System.out.println("Podaj wspó³rzêdn± pionow±. (1 - 10)\nY = ");
        return in.nextInt();
    }
    
    public static String scanName()
    {
        System.out.print("Podaj swoje imiê: ");
        return in.nextLine();
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
    
    public static boolean isPlaceValid(char direction, int x, int y, int polesNumber)
    {
        if (direction == 'H' || direction == 'h')
        {
            if (x + polesNumber <= 9)
                return true;
            else
                return false;
        }
        else if (direction == 'V' || direction == 'v')
        {
            if (y + polesNumber <= 9)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public static void showCountShips(int shipsNumber){
    	System.out.print("Masz ");
    	System.out.print(shipsNumber);
    	System.out.println(" statków.");    	
    }
    public static void showEnemyCountShips(int shipsNumber){
    	System.out.print("Twój przeciwnik ma ");
    	System.out.print(shipsNumber);
    	System.out.println(" statków.");    	
    }
    
    public static void showYourMove(Player gamer)
    {
    	System.out.println("Twój ruch: " + gamer.getName());
    }
    public static void showYouWon(Player gamer)
    {
    	System.out.println("Wygra³e¶ " + gamer.getName() + "!");
    }
    public static void showYouLost(Player gamer)
    {
    	System.out.println("Przegra³e¶ " + gamer.getName() + "!");
    }
    public static void showGameOver()
    {
    	System.out.println("Koniec gry !");
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
}

