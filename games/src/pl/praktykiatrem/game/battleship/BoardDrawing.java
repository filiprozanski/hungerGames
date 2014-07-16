package pl.praktykiatrem.game.battleship;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

import javax.imageio.ImageIO;
import java.io.File;

public class BoardDrawing {

    private final JPanel gui = new JPanel(new BorderLayout(0, 0));
    private JButton[][] place = new JButton[10][10];
    private Image[] elements = new Image[4];
    private JPanel board;    
    public static final int sizeH = 10;
    public static final int sizeV = 10;

    /**
     * @wbp.parser.entryPoint
     */
    BoardDrawing() {
        initializeGui();
    }

    public final void initializeGui() {
        // create the images for the chess pieces
        createImages();

        // set up the main GUI
        gui.setBorder(new EmptyBorder(0, 0, 0, 0));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("test") {

            @Override
            public void actionPerformed(ActionEvent e) {
               setupNewGame();
            }
        };
        tools.add(newGameAction);


        gui.add(new JLabel("?"), BorderLayout.LINE_START);
        //template, grid
        board = new JPanel(new GridLayout(11, 11)) {

        	//powiêksza planszê razem z ekranem
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        board.setBorder(new CompoundBorder(new EmptyBorder(8,8,8,8), new LineBorder(Color.BLACK)));
        // Set the BG to be ochre
        Color ochre = new Color(255,255,0);
        board.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(board);
        gui.add(boardConstrain);

        // stwórz poszczególne pola
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < place.length; i++) {
            for (int j = 0; j < place[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.WHITE);                
                place[j][i] = b;
            }
        }

        /*
         * fill the board
         */
        board.add(new JLabel(""));
        // pierwszy rz±d cyfr
        for (int i = 0; i < 10; i++) 
            board.add(new JLabel("" + i,SwingConstants.CENTER));
        
        // fill the black non-pawn piece row
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

    public final JComponent getGui() {
        return gui;
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

   private final void setupNewGame() {
       
        // set up the black pieces
        for (int i = 0; i < sizeH; i++) {
            place[i][0].setIcon(new ImageIcon(elements[0]));
        }        
        for (int i = 0; i < sizeH; i++) {
            place[i][1].setIcon(new ImageIcon(elements[1]));
        }
        for (int i = 0; i < sizeH; i++) {
            place[i][8].setIcon(new ImageIcon(elements[2]));
        }
        for (int i = 0; i < sizeH; i++) {
            place[i][9].setIcon(new ImageIcon(elements[3]));
        }
        
    }

  
    /**
     * @wbp.parser.entryPoint
     */
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                BoardDrawing cg = new BoardDrawing();

                JFrame f = new JFrame("plansza");
                f.getContentPane().add(cg.getGui());
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
                f.setVisible(true);
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}