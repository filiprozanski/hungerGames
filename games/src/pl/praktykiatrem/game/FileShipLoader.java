/*
 * Plik stworzony dnia 11 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileShipLoader implements ShipLoadingInterface {
    public void initializeShips(Player gamer) throws FileNotFoundException {
        File plik1=new File("src/pl/praktykiatrem/game/"+gamer.getName()+".txt");
        Scanner odczyt=new Scanner(plik1);
        String temp;
        while (odczyt.hasNextLine()){
            for(int i=0;i<7;i++){       
                temp=odczyt.nextLine();
                gamer.placeShips(i, (int)temp.charAt(0)-48, temp.charAt(2), (int)temp.charAt(4)-48, (int)temp.charAt(6)-48);            
            
            }
        }
        odczyt.close();   
    }
}
