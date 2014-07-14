package pl.praktykiatrem.game.battleship;
import java.io.FileNotFoundException;
public class TestBoard {
	
	public static void main(String[] args)throws FileNotFoundException {	
		BattleshipGame newGame = new BattleshipGame();
		newGame.gameInProgress();
    }
}
