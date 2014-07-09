/*
 * Plik stworzony dnia 9 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game;

public class GameFactory {
    private Player A;
    private Player B;
    
    public GameFactory()
    {
        initializePlayers();
    }
    
    private void initializePlayers()
    {
        A = new Player(ConsoleInterface.scanString());
        B = new Player(ConsoleInterface.scanString());
        char dir;
        int x;
        int y;
        int polesNumber;
        
        polesNumber = 6;
        ConsoleInterface.printlnMessage("Ustawiasz 6-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo)");
        dir = ConsoleInterface.scanString().charAt(0);
        ConsoleInterface.printlnMessage("Podaj wspó³rzêdn± poziom±. (1 - 10)");
        x = ConsoleInterface.scanInt();
        ConsoleInterface.printlnMessage("Podaj wspó³rzêdn± pionow±. (1 - 10)");
        y = ConsoleInterface.scanInt();
        
        if(ConsoleInterface.isPlaceValid(dir, x, y, polesNumber))
        {
            
        }
        
        //A.placeShips();
        //B.placeShips();
    }
    
    public Player getA()
    {
        return A;
    }
    
    public Player getB()
    {
        return B;
    }
}
