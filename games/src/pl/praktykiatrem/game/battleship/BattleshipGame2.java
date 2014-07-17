package pl.praktykiatrem.game.battleship;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;






import pl.praktykiatrem.game.battleship.console.ConsoleInteractions;
import pl.praktykiatrem.game.battleship.factory.GameFactory;
import pl.praktykiatrem.game.battleship.factory.ShipLoader;
import pl.praktykiatrem.game.battleship.BoardDrawing;

public class BattleshipGame2 {
	private Icon optionIcon = UIManager.getIcon("FileView.computerIcon");
	public JFrame frame;
	private JTextArea trackerA;
	private JTextArea trackerB;
	private GameStatus A;
    private GameStatus B;
    private Controller gameControl;
    
    public BattleshipGame2(Boolean swing)
    {
        GameFactory start = new GameFactory();
        A = start.createPlayer();
        B = start.createPlayer();
        initialize();
        if(swing==true)
        	gameControl = new Controller(true);
		else
			gameControl = new Controller(false);        
    }
    	
	private String dialogPopUp(int id){
		String name = (String)JOptionPane.showInputDialog(frame
                , "Podaj imiê " + id + " gracza:"
                , "Text Field Dialog", JOptionPane.QUESTION_MESSAGE
                , optionIcon, null, null);
		return name;		
	}
    
    /**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		BoardDrawing g1 = new BoardDrawing();
		BoardDrawing g2 = new BoardDrawing();
		BoardDrawing g3 = new BoardDrawing();
		BoardDrawing g4 = new BoardDrawing();
		trackerA = new JTextArea("PlayerA:");
		trackerB = new JTextArea("PlayerB:");
		
		frame = new JFrame("BattleShips of \"hungerGames\" productions");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 3, 0, 0));
		
		frame.add(g1.getBoard());
		frame.add(g2.getBoard());
		frame.add(trackerA);
		frame.add(g3.getBoard());
		frame.add(g4.getBoard());
		frame.add(trackerB);
		frame.pack();
		frame.setMinimumSize(frame.getSize());
	}
	public void gameInProgress()  
    {
        gameControl.showMenu();      
        
        
        A.setName(dialogPopUp(1));
        B.setName(dialogPopUp(2));
   
        setShips(A);
        setShips(B);
        
        GameStatus currentPlayer = A;
        GameStatus enemy = B;
        int[] cords = {0, 0};
        /*
        gameControl.showLegend();
        while (!isGameOver(enemy))
        {	
        	
            BoardDrawing.drawGameBoardForOpponent(enemy.getPlansza());
            gameControl.showYourMove(currentPlayer);
            cords = pointRifle();
            if (!currentPlayer.makeMove(cords[0], cords[1], enemy))
            {
                gameControl.showMissMessage();
                enemy = currentPlayer;
                currentPlayer = changePlayer(currentPlayer);
            }
            else
            {
                gameControl.showHitMessage();
            }
                
            gameControl.showGameSummary(currentPlayer, enemy);
        }
        gameControl.showGameOver(currentPlayer);
        gameControl.showGameOver();*/
    }
    
    /**
     * 
     * Metoda <code>pointRifle</code> pobiera wspó³rzêdne do strza³u
     *
     * @return tablica int[], gdzie [0] to wspó³rzêdna x, a [1] to wspó³rzêdna y
     */
    private int[] pointRifle()
    {
        int[] tab = {0, 0};
        
        while (true)
        {
            tab = gameControl.scanCoords();
            if (tab[0] >= 0 && tab[0] <= 9 && tab[1] >= 0 && tab[1] <= 9)
                return tab;
            else
                gameControl.showErrorMessage1();
        }
    }
    
    private GameStatus changePlayer(GameStatus X)
    {
        if (X.getName() == A.getName())
            return B;
        else
            return A;
    }
    
    private boolean isGameOver(GameStatus X)
    {
    	if (X.getShipsNumber()>0)
    		return false;
    	else
    		return true;
    }
    
    private void setShips(GameStatus gamer)
    {
    	try {
        	ShipLoader.initializeShipsFromFile(gamer);
          } catch (FileNotFoundException e) {
        	  ShipLoader.initializeShips(gamer, gameControl);
          }
    }
    

}
