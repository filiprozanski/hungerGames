package pl.praktykiatrem.game;

public class TestBoard {
	
	public static void main(String[] args) {
	    Board plansza = new Board(10, 10);
	    
	    System.out.println(plansza);
	    
	    BoardDrawing.drawGameBoardForOpponent(plansza);
	    System.out.println();
	    BoardDrawing.drawGameBoardForPlayer(plansza);
	    System.out.println();
	    
	    Player filip = new Player("Filip");
	    filip.placeShips(1, 6, 'H', 5, 2);
	    
	    Player wiktor = new Player("Filip");
        wiktor.placeShips(1, 6, 'H', 2, 2);
	    
	    BoardDrawing.drawGameBoardForOpponent(filip.getPlansza());
	    System.out.println();
	    BoardDrawing.drawGameBoardForPlayer(filip.getPlansza());
	    System.out.println();
	    
	    //filip.makeMove(x, y, plansza)
    }

}
