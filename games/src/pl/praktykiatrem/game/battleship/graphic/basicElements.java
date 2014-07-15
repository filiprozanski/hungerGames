/*
 * Plik stworzony dnia 15 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.graphic;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class basicElements {
    
    public static void startWindow()
    {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                SimpleFrame frame = new SimpleFrame(900, 600);
                frame.setTitle("Gra w statki");
                frame.setVisible(true);
            }
        });
    }
}

class DrawComponent extends JComponent
{
    public static final int LENGTH = 50;
    
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        g2.draw(new Line2D.Double(450, 0, 450, 25));
    }
}

class SimpleFrame extends JFrame
{   
    public SimpleFrame(int width, int height)
    {
        //Toolkit kit = Toolkit.getDefaultToolkit();
        //Dimension screenSize = kit.getScreenSize();
        
        setSize(width, height);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new DrawComponent());
    }
}