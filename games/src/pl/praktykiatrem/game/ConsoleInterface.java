/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

import java.util.Scanner;

public class ConsoleInterface {
    private static Scanner in = new Scanner(System.in);
    
    public static char scanDirection()
    {
        System.out.println("Ustawiasz " + polesNumber + "-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo)");
        return in.nextLine().at
    }
    
    public static int scanInt()
    {
        return in.nextInt();
    }
    
    public static void printMessage(String message)
    {
        System.out.print(message);
    }
    
    public static void printlnMessage(String message)
    {
        System.out.println(message);
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
}

