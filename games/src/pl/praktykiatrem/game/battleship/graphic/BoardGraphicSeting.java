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
import pl.praktykiatrem.game.battleship.graphic.listeners.PlacingListener;

public class BoardGraphicSeting extends JPanel {
	private static final int SIZEH = 10;
	private static final int SIZEV = 10;
    protected ShipButton[][] place = new ShipButton[SIZEH][SIZEV];
    protected Controller control;
    
    public BoardGraphicSeting(Controller control)
    {
    	super(new GridLayout(SIZEH + 1, SIZEV + 1));
    	this.control = control;
        place = new ShipButton[SIZEH][SIZEV];
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
    	setBackground(Color.LIGHT_GRAY);
    	fillGameBoard();
    	drawNumbers();	
    }
    
    public void enableButtons()
    {
    	for (int i = 0; i < SIZEH; i++)
    	{
    		for (int j = 0; j < SIZEV; j++)
    			if (place[j][i].isShipSet() == false)
    				place[j][i].setEnabled(true);
    	}
    }
    
    public void disableButtons()
    {
    	for (int i = 0; i < SIZEH; i++)
    	{
    		for (int j = 0; j < SIZEV; j++)
    			place[j][i].setEnabled(false);
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
                b.addActionListener(new PlacingListener(j, i)); 
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                b.setBackground(Color.WHITE);
                b.setEnabled(false);
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
}
