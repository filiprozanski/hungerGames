package pl.praktykiatrem.game.battleship.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pl.praktykiatrem.game.battleship.Controller;
import pl.praktykiatrem.game.battleship.Player;
import pl.praktykiatrem.game.battleship.ValidationInstruments;
import pl.praktykiatrem.game.battleship.console.BoardDrawing;

public class ShipLoader {
    public static void initializeShipsFromFile(Player gamer) throws FileNotFoundException {
        File plik1=new File("src/pl/praktykiatrem/game/battleship/"+gamer.getName()+".txt");
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
    
	public static void initializeShips(Player gamer)
    {
            int polesNumber;
            Controller.showYourMove(gamer);
            polesNumber = 6;
            fetchShipCoords(polesNumber, 0, gamer);
        
            polesNumber = 4;
            
            for (int i = 1; i < 7; i++)
            {
                fetchShipCoords(polesNumber, i, gamer);
                i++;
                fetchShipCoords(polesNumber, i, gamer);
                polesNumber--;
            }
    }
    
    /**
     * 
     * Metoda <code>fetchShipCoords</code> pobiera dane do ustawienia statku
     *
     * @param polesNumber liczba masztów
     * @param id
     * @param gamer gracz, którego statki s± ustawiane
     */
    private static void fetchShipCoords(int polesNumber, int id, Player gamer)
    {
        char dir;
        int[] tab = {0, 0};
        
        while(true) {
            dir = Controller.scanDirection(polesNumber);
            tab = Controller.scanCoords();
            
            if(ValidationInstruments.isPlaceValid(dir, tab[0], tab[1], polesNumber) && gamer.placeShips(id, polesNumber, dir, tab[0], tab[1]))
            {
                BoardDrawing.drawGameBoardForPlayer(gamer.getPlansza());
                break;
            }
            Controller.showErrorMessage1();
        }
    }
    
}
