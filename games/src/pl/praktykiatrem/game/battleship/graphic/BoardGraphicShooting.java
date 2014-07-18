package pl.praktykiatrem.game.battleship.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pl.praktykiatrem.game.battleship.Controller;

public class BoardGraphicShooting extends JPanel {
	private static final int SIZEH = 10;
	private static final int SIZEV = 10;
    protected JButton[][] place = new JButton[SIZEH][SIZEV];
    protected Image[] elements = new Image[4];
    protected Controller control;
    
    public BoardGraphicShooting(Controller control)
    {
    	super(new GridLayout(SIZEH + 1, SIZEV + 1));
    	this.control = control;
        place = new JButton[SIZEH][SIZEV];
        elements = new Image[4];
    	setSize(330, 330);
    	initializeBoard();
    }
    
    public Dimension getPreferredSize()
    {
    	return new Dimension(330, 330);
    }
    
    public Dimension getMinimumDimension()
    {
    	return new Dimension(330, 330);
    }
    
    public Dimension getMaximDimension()
    {
    	return new Dimension(330, 330);
    }
    
    private void initializeBoard()
    {
    	createImages();
    	
    	setBackground(Color.LIGHT_GRAY);
    	fillGameBoard();
    	drawNumbers();	
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
    
    private void fillGameBoard()
    {
    	Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < place.length; i++)
        {
            for (int j = 0; j < place[i].length; j++)
            {
                ShipButton b = new ShipButton();
                b.addActionListener(new PlaceListener(j,i));
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.WHITE);                
                place[j][i] = b;
            }
        }
    }
    
    private void drawNumbers()
    {
    	add(new JLabel(""));
        // pierwszy rz±d cyfr
        for (int i = 0; i < SIZEH; i++) 
            add(new JLabel("" + i,SwingConstants.CENTER));
        
        // pierwsza kolumna to SwingConstant cyfry
        for (int i = 0; i < SIZEH; i++) {
            for (int j = 0; j < SIZEV; j++) {
                switch (j) {
                    case 0:
                        add(new JLabel("" + i,SwingConstants.CENTER));
                    default:
                        add(place[j][i]);
                }
            }
        }
    }
    
    private class PlaceListener implements ActionListener {
    	private int x;
    	private int y;
        private PlaceListener( int x, int y ) {
        	this.x = x;
        	this.y = y;
        }
        
        public void actionPerformed(ActionEvent evt) {
        	if (control.killEmAll(x, y))
        		setPlaceIcon(3,x,y);
        	else
        		setPlaceIcon(0,x,y);
        }
    }
    
    private void setPlaceIcon(int type, int x, int y) {       
        place[x][y].setIcon(new ImageIcon(elements[type]));
    }
}
