/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.console;

import java.util.Scanner;

import pl.praktykiatrem.game.battleship.Comunicatable;
import pl.praktykiatrem.game.battleship.Player;

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
    
    public void showMenu()
    {
        System.out.println("Witamy w grze w statki, za chwile zostaniesz przeniesiony na pole bitwy!");
    }
    
    public void showChooseInterface()
    {
        System.out.println("Wybierz 1 aby wczytaæ statki z pliku lub 2 aby wpisaæ je rêcznie");
    }
    
    public int scanInterafaceChoice()
    {
        System.out.print("Decyzja: ");
        int input = in.nextInt();
        return input;
    }
    
    public char scanDirection(int polesNumber)
    {
        System.out.print("Ustawiasz " + polesNumber + "-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo):");
        String input = in.next();
        return input.charAt(0);
    }
    
    public int scanXCoordinate()
    {
        System.out.print("Podaj wspó³rzêdn± poziom±. (0 - 9)\nX = ");
        return in.nextInt();
    }
    
    public int scanYCoordinate()
    {
        System.out.print("Podaj wspó³rzêdn± pionow±. (0 - 9)\nY = ");
        return in.nextInt();
    }
    
    public String[] scanName()
    {
        String[] names = new String[2];
        
        System.out.print("Podaj imiê Gracz I: ");
        names[0] = in.next();
        System.out.print("Podaj imiê Gracz II: ");
        names[1] = in.next();
        
        return names;
    }
    
    public void showErrorMessage1()
    {
        System.out.println("Poda³e¶ b³êdne dane, spróbuj jeszcze raz.");
    }
    
    public void clearConsole()
    {
    	for (int i = 0; i < 20; i++)
    		System.out.println();
    }
    
    public static void showCountShips(Player g){
    	System.out.print(g.getName() + " ma ");
    	System.out.print(g.getShipsNumber());
    	System.out.println(" statków.");    	
    }
    
	public void showGameSummary(Player g, Player h)
	{
		ConsoleInteractions.showCountShips(g);
		ConsoleInteractions.showCountShips(h);
	}
	
    public void showYourMove(Player gamer)
    {
    	System.out.println("Twój ruch: " + gamer.getName());
    }
    
    public void showGameOver()
    {
    	System.out.println("Koniec gry !");
    }
    
    public void showGameOver(Player gamer)
    {
   		System.out.println("Wygra³: " + gamer.getName());		
    }
    
    public int[] scanDropCoords()
    {
        int[] tab = {0, 0};
        
        System.out.print("Podaj kordynaty uderzenia:\nX = ");
        tab[0] = in.nextInt();
        System.out.print("Y = ");
        tab[1] = in.nextInt();
        
        return tab;
    }
    
    public void showHitMessage()
    {
        System.out.println("Brawo! Trafi³e¶!");
    }
    
    public void showMissMessage()
    {
        System.out.println("Pud³o...");
    }
    
    public void showLegend()
    {
    	System.out.println("LEGENDA:");
    	System.out.println("\"+\" pole na które nie oddano strza³u");
    	System.out.println("\"0-6\" numer statku");
    	System.out.println("\"H\" trafiony maszt statku");
    	System.out.println("\"M\" chybione pole");
    }
}

