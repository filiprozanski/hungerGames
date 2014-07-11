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
 *	Klasa <code>ConsoleInterface</code> zawiera funkcje statyczne wy¶wietlaj±ce w konsoli komunikaty, oraz pobieraj±ce od u¿ytkownika niezbêdnê dane
 *
 * @author filipr
 * @version 11 lip 2014 12:36:18
 *
 */
public class ConsoleInterface implements Comunicatable{
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
        System.out.print("Podaj wspó³rzêdn± poziom±. (1 - 10)\nX = ");
        return in.nextInt();
    }
    
    public static int scanYCoordinate()
    {
        System.out.print("Podaj wspó³rzêdn± pionow±. (1 - 10)\nY = ");
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
    
    /**
     * 
     * Metoda <code>isPlaceValid</code> sprawdza, czy statek o danych parametrach mie¶ci siê na polu gry 
     *
     * @param direction okre¶la czy statek bêdzie poziomo 'h', czy pionowo 'v'
     * @param x wspó³rzêdna pozioma pocz±tku statku
     * @param y wspó³rzêdna pionowa pocz±tku statku
     * @param polesNumber liczba masztów (wielko¶æ statku)
     * @return true je¿eli statek zmie¶ci siê na planszy, false je¿eli nie
     */
    public static boolean isPlaceValid(char direction, int x, int y, int polesNumber)
    {
        if (direction == 'H' || direction == 'h')
        {
            if (x + polesNumber <= 10)
                return true;
            else
                return false;
        }
        else if (direction == 'V' || direction == 'v')
        {
            if (y + polesNumber <= 10)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    public static void showCountShips(Player g){
    	System.out.print(g.getName() + " ma ");
    	System.out.print(g.getShipsNumber());
    	System.out.println(" statków.");    	
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

