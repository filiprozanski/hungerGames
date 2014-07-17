package pl.praktykiatrem.game.battleship.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pl.praktykiatrem.game.battleship.Controller;
import pl.praktykiatrem.game.battleship.GameStatus;
import pl.praktykiatrem.game.battleship.ValidationInstruments;
import pl.praktykiatrem.game.battleship.console.BoardDrawing;

public class ShipLoader {
    public static void initializeShipsFromFile(GameStatus gamer) throws FileNotFoundException {
        File plik1=new File("src/pl/praktykiatrem/game/battleship/"+gamer.getName()+".txt");
        Scanner odczyt=new Scanner(plik1);
        String temp;
        while (odczyt.hasNextLine()){
            for(int i=0;i<7;i++){       
                temp=odczyt.nextLine().toUpperCase();
                gamer.placeShips(i, (int)temp.charAt(0)-48, temp.charAt(2), (int)temp.charAt(4)-48, (int)temp.charAt(6)-48);            
            
            }
        }
        odczyt.close();   
    }
    
	public static void initializeShips(GameStatus gamer, Controller gameControl)
    {
            int polesNumber;
            gameControl.showYourMove(gamer);
            polesNumber = 6;
            fetchShipCoords(polesNumber, 0, gamer, gameControl);
        
            polesNumber = 4;
            
            for (int i = 1; i < 7; i++)
            {
                fetchShipCoords(polesNumber, i, gamer, gameControl);
                i++;
                fetchShipCoords(polesNumber, i, gamer, gameControl);
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
    private static void fetchShipCoords(int polesNumber, int id, GameStatus gamer, Controller gameControl)
    {
        char dir;
        int[] tab = {0, 0};
        
        while(true) {
            dir = gameControl.scanDirection(polesNumber);
            tab = gameControl.scanCoords();
            
            if(ValidationInstruments.isPlaceValid(dir, tab[0], tab[1], polesNumber) && gamer.placeShips(id, polesNumber, dir, tab[0], tab[1]))
            {
                BoardDrawing.drawGameBoardForPlayer(gamer.getPlansza());
                break;
            }
            gameControl.showErrorMessage1();
        }
    }
    
}
