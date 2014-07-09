package pl.praktykiatrem.game;

public class TestBoard {
	
	public static void main(String[] args) {
        Board plansza = new Board(5, 5);
        
        System.out.println(plansza);
        
        String testowy;
        System.out.println("Test Console");
        testowy = ConsoleInterface.scanString();
        
        System.out.println(testowy);
    }

}
