package pl.praktykiatrem.game.battleship.console;

/**
 * 
 * Klasa <code>TesBoard</code> s³u¿y do uruchamiania gry w statki w konsoli.
 *
 * @author hungerGames
 *
 */
public class TestBoard {

	public static void main(String[] args) {
		BattleshipGame newGame = new BattleshipGame(false);
		newGame.gameInProgress();
	}
}