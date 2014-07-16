package pl.praktykiatrem.game.battleship;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import pl.praktykiatrem.game.battleship.graphic.basicElements;
public class TestBoard {
	
	public static void main(String[] args) {	
		//BattleshipGame newGame = new BattleshipGame();
		//newGame.gameInProgress();
	    
	    //basicElements.startWindow();
		
		 SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                BoardDrawing cg = new BoardDrawing();
                BoardDrawing cg2 = new BoardDrawing();

                JFrame f = new JFrame("plansza");
                
                f.getContentPane().add(cg.getBoard());
                f.getContentPane().add(cg2.getBoard());
                
                //ustawianie w³aœciwoœci g³ównego okna
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
                
                //////////////////////////////////
                
                /*BoardDrawing cg2 = new BoardDrawing();
                
                JFrame f2 = new JFrame("plansza");
                
                f2.getContentPane().add(cg2.getBoard());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverflow.com/a/7143398/418556 for demo.
                f2.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f2.pack();
                // ensures the minimum size is enforced.
                f2.setMinimumSize(f2.getSize());
                cg2.fillGameBoard(3, 6, 5);
                f2.setVisible(true);*/
                
            }
        });
        
       
    }
}
