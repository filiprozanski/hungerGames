package pl.praktykiatrem.game.battleship;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.border.*;
import javax.imageio.ImageIO;

import java.io.File;

public class BoardDrawing {

    private JButton[][] place = new JButton[10][10];
    private Image[] elements = new Image[4];
    private JPanel board;    
    public static final int sizeH = 10;
    public static final int sizeV = 10;

    BoardDrawing() {
        initializeBoard();
    }

    public final void initializeBoard() {
        // stwórz obrazki statków z plików
        createImages();

        //template, grid
        board = new JPanel(new GridLayout(11, 11)) {        	
        };
        
        //board.setBorder(new CompoundBorder(new EmptyBorder(8,8,8,8), new LineBorder(Color.PINK)));
        Color backGround = new Color(255,255,0);
        board.setBackground(backGround);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(backGround);
        boardConstrain.add(board);
    
        class PlaceListener implements ActionListener {
        	private final int x;
        	private final int y;
            private PlaceListener( int x, int y ) {
            	this.x = x;
            	this.y = y;
            }
            public void actionPerformed(ActionEvent evt) {
                fillGameBoard(2,x,y);
            }
        }
        
        // stwórz poszczególne pola
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                JButton b = new JButton();
                b.addActionListener(new PlaceListener(j,i));
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.WHITE);                
                place[j][i] = b;
            }
        }
        
        board.add(new JLabel(""));
        // pierwszy rz±d cyfr
        for (int i = 0; i < 10; i++) 
            board.add(new JLabel("" + i,SwingConstants.CENTER));
        
        // pierwsza kolumna to SwingConstant cyfry
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (j) {
                    case 0:
                        board.add(new JLabel("" + i,SwingConstants.CENTER));
                    default:
                        board.add(place[j][i]);
                }
            }
        }
    }


	public void someNonStandardEntryPoint() {
		JFrame board = new JFrame();
		
	}


    public final JComponent getBoard() {
    	
        return board;
    }

    private final void createImages() {
        try {
        	elements[0] = ImageIO.read(new File("src/pl/praktykiatrem/game/battleship/1.PNG"));
        	elements[1] = ImageIO.read(new File("src/pl/praktykiatrem/game/battleship/2.PNG"));
        	elements[2] = ImageIO.read(new File("src/pl/praktykiatrem/game/battleship/3.PNG"));
        	elements[3] = ImageIO.read(new File("src/pl/praktykiatrem/game/battleship/4.PNG"));        	
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

   public void fillGameBoard(int type, int x, int y) {       
            place[x][y].setIcon(new ImageIcon(elements[type]));
   }

  
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                BoardDrawing cg = new BoardDrawing();

                JFrame f = new JFrame("plansza");
                
                f.getContentPane().add(cg.getBoard());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                cg.fillGameBoard(3, 6, 5);
                f.setVisible(true);
                
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}