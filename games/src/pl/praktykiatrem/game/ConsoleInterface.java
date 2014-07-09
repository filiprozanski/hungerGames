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
    
    public static String scanString()
    {
        return in.nextLine();
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
}
