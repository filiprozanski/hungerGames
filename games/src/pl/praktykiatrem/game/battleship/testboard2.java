package pl.praktykiatrem.game.battleship;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;


public class testboard2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testboard2 window = new testboard2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//BattleshipGame newGame = new BattleshipGame(false);
				//newGame.gameInProgress();
			}
		});
	}

	/**
	 * Create the application.
	 */
	public testboard2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		BoardDrawing g1 = new BoardDrawing();
		BoardDrawing g2 = new BoardDrawing();
		BoardDrawing g3 = new BoardDrawing();
		BoardDrawing g4 = new BoardDrawing();
		frame = new JFrame("BattleShips of \"hungerGames\" productions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
		frame.add(g1.getBoard());
		frame.add(g2.getBoard());
		frame.add(g3.getBoard());
		frame.add(g4.getBoard());
		frame.pack();
		frame.setMinimumSize(frame.getSize());
	}

}
