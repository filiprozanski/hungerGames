package pl.praktykiatrem.game.battleship;

import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.Icon;

import pl.praktykiatrem.game.battleship.Comunicatable;
import pl.praktykiatrem.game.battleship.PlayerStatus;


public class SwingInteractions implements Comunicatable{
    private static Scanner in = new Scanner(System.in);
    private Icon optionIcon = UIManager.getIcon("FileView.computerIcon");
    public void showMenu()
    {
        System.out.println("Witamy w grze w statki, za chwile zostaniesz przeniesiony na pole bitwy!");
    }
    
    public char scanDirection(int polesNumber)
    {
        System.out.print("Ustawiasz " + polesNumber + "-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo):");
        String input = in.next().toUpperCase();
        return input.charAt(0);
    }
    
    public String scanName(int gamerNumber)
    {
        return "";
    }
    
    public void showErrorMessage1()
    {
        System.out.println("Poda�e� b��dne dane, spr�buj jeszcze raz.");
    }
    
    private void clearConsole()
    {
    	for (int i = 0; i < 20; i++)
    		System.out.println();
    }
    
    public static void showCountShips(PlayerStatus g){
    	System.out.print(g.getName() + " ma ");
    	System.out.print(g.getShipsNumber());
    	System.out.println(" statk�w.");    	
    }
    
	public void showGameSummary(PlayerStatus g, PlayerStatus h)
	{
		SwingInteractions.showCountShips(g);
		SwingInteractions.showCountShips(h);
	}
	
    public void showYourMove(PlayerStatus gamer)
    {
    	System.out.println("Tw�j ruch: " + gamer.getName());
    }
    
    public void showGameOver()
    {
    	System.out.println("Koniec gry !");
    }
    
    public void showGameOver(PlayerStatus gamer)
    {
   		System.out.println("Wygra�: " + gamer.getName());		
    }
    
    public int[] scanCoords()
    {
        int[] tab = {0, 0};
        
        System.out.println("Podaj kordynaty uderzenia: ");
        
        while (true)
        {
            System.out.print("X = ");
            
            if (in.hasNextInt())
            {
                tab[0] = in.nextInt();
                break;
            }
            else
            {
                System.out.println("Podaj liczb� ca�kowit�!");
                in.next();
            }
        }
        
        while (true)
        {
            System.out.print("Y = ");
            
            if (in.hasNextInt())
            {
                tab[1] = in.nextInt();
                break;
            }
            else
            {
                System.out.println("Podaj liczb� ca�kowit�!");
                in.next();
            }
        }
        
        return tab;
    }
    
    public void showHitMessage()
    {
    	clearConsole();
        System.out.println("Brawo! Trafi�e�!");
    }
    
    public void showMissMessage()
    {
    	clearConsole();
        System.out.println("Pud�o...");
    }
    
    public void showLegend()
    {
    	System.out.println("LEGENDA:");
    	System.out.println("\"+\" pole na kt�re nie oddano strza�u");
    	System.out.println("\"0-6\" numer statku");
    	System.out.println("\"H\" trafiony maszt statku");
    	System.out.println("\"M\" chybione pole");
    }
}

