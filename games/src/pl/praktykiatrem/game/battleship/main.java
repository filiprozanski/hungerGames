package pl.praktykiatrem.game.battleship;
 
/*
 * CardLayoutDemo.java
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.prefs.BackingStoreException;

import javax.imageio.ImageIO;
import javax.swing.*;

import pl.praktykiatrem.game.battleship.graphic.*;

public class main extends JPanel implements ActionListener {
    JPanel cards; //a panel that uses CardLayout
    final static String GAME = "Plansza";
    final static String MENU = "Menu";
    final static String GoToGame = "Przejd¼ do gry.";
    final static String GoToMenu = "Przejd¼ do menu.";
    final static String CREDITS = "O programie";
    private Image img;
    
    public main() {    		
    	inicialize();
    }
   
    
    public void inicialize() {
    	Controller controller=new Controller();
    	menu menu = new menu();
    	Background credits = new Background("doge.jpg");
    	JPanel game = new JPanel();
    	BoardGraphicSeting board = new BoardGraphicSeting(controller);
    	
    	//menu.setLayout());
        
    	JButton buttonGoToGame = new JButton(GoToGame);
        JButton buttonGoToMenu = new JButton(GoToMenu);
        JButton buttonGoToCredits = new JButton(CREDITS);
        
        buttonGoToGame.addActionListener(this);
        buttonGoToMenu.addActionListener(this);
        buttonGoToCredits.addActionListener(this);
        
        buttonGoToCredits.setAlignmentX(100);
        buttonGoToCredits.setAlignmentY(100);
        
        buttonGoToGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        
       
        
        game.add(board, BorderLayout.CENTER);
        game.add(buttonGoToMenu, BorderLayout.PAGE_END);
        
         
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(menu, MENU);
        cards.add(game, GAME);
        cards.add(credits, CREDITS);
         
        //pane.add(comboBoxPane, BorderLayout.PAGE_START);
        add(cards, BorderLayout.CENTER);
    }
     
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
        
    }
    
    public void actionPerformed(ActionEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        if((String)evt.getActionCommand() == GoToGame) 
        	cl.show(cards, GAME);
        if((String)evt.getActionCommand() == GoToMenu) 
        	cl.show(cards, MENU);
        if((String)evt.getActionCommand() == CREDITS) 
        	cl.show(cards, CREDITS);
    }
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame out = new JFrame("Battleships");
        main demo = new main();
        out.add(demo);
        //Display the window.
        out.pack();
        out.setVisible(true);
    }
     
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}