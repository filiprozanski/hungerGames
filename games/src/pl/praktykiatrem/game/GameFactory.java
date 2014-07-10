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
    }
    
    private void initializeShips()
    {
        int polesNumber;
        
            polesNumber = 6;
            doDirtyStuff(polesNumber, 0);
        
            polesNumber = 4;
        
            for (int i = 1; i < 7; i++)
            {
                doDirtyStuff(polesNumber, i);
                i++;
                doDirtyStuff(polesNumber, i);
                polesNumber--;
            }
    }
    
    private void doDirtyStuff(int polesNumber, int id)
    {
        char dir;
        int x;
        int y;
        
        while(true) {
            ConsoleInterface.printlnMessage("Ustawiasz " + polesNumber + "-masztowiec.\nPodaj kierunek. (h-poziomo, v-pionowo)");
            dir = ConsoleInterface.scanString().charAt(0);
            ConsoleInterface.printlnMessage("Podaj wspó³rzêdn± poziom±. (1 - 10)");
            x = ConsoleInterface.scanInt();
            ConsoleInterface.printlnMessage("Podaj wspó³rzêdn± pionow±. (1 - 10)");
            y = ConsoleInterface.scanInt();
        
            if(ConsoleInterface.isPlaceValid(dir, x, y, polesNumber))
            {
                A.placeShips(id, polesNumber, dir, x, y);
                break;
            }
        }
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
