package pl.praktykiatrem.game;

public class TestBoard {
	
	public static void main(String[] args) {
	    Board plansza = new Board(10, 10);
	    
	    System.out.println(plansza);
	    
	    BoardDrawing.drawGameBoardForOpponent(plansza);
	    BoardDrawing.drawGameBoardForPlayer(plansza);
	    
	    
    }

}
