package pl.praktykiatrem.game.battleship;
import java.awt.EventQueue;


public class TestBoard2 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleshipGame2 window = new BattleshipGame2(true);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
		});
		BattleshipGame newGame = new BattleshipGame(true);
		newGame.gameInProgress();
	}

}
