package pl.praktykiatrem.game.battleship;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;

public class menu extends JPanel implements ActionListener {
	private Image img;
    final static String GAME = "Plansza";
    final static String MENU = "Menu";
    final static String GoToGame = "Przejd¼ do gry.";
    final static String GoToMenu = "Przejd¼ do menu.";
    final static String CREDITS = "O programie";
	 
	  public menu() {
	    // load the background image
	    try {
	      img = ImageIO.read(new File("src/pl/praktykiatrem/game/battleship/background.png"));
	    } catch(IOException e) {
	      e.printStackTrace();
	    }
	    initialize();
	  }
	 
	  private void initialize() {
	    	//menu.setLayout());
	        
	    	JButton buttonGoToGame = new JButton(GoToGame);
	        JButton buttonGoToMenu = new JButton(GoToMenu);
	        JButton buttonGoToCredits = new JButton(CREDITS);
	        
	        buttonGoToGame.addActionListener(new main());
	        buttonGoToCredits.addActionListener(new main());
	        
	        buttonGoToCredits.setAlignmentX(100);
	        buttonGoToCredits.setAlignmentY(100);
	        
	        add(buttonGoToGame);
	        add(buttonGoToCredits);

	}

	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    // paint the background image and scale it to fill the entire space
	    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	  }

	@Override
	public void actionPerformed(ActionEvent arg0) {
				
	}
}
