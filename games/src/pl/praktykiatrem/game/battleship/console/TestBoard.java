package pl.praktykiatrem.game.battleship.console;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestBoard {
	
	public static void main(String[] args) {	
		BattleshipGame newGame = new BattleshipGame(false);
		newGame.gameInProgress();
    }
}