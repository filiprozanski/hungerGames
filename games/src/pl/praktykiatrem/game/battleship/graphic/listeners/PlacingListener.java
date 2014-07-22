package pl.praktykiatrem.game.battleship.graphic.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.praktykiatrem.game.battleship.graphic.ShipButton;

public class PlacingListener implements ActionListener {
	private int x;
	private int y;
	
    public PlacingListener( int x, int y ) {
    	this.x = x;
    	this.y = y;
    }
    
    public void actionPerformed(ActionEvent evt) {
    	ShipButton source = (ShipButton) evt.getSource();
    	
    	if (source.getCallNumber() == 0)
    	{
    		source.setPlaceIcon(2, x, y);
    		source.changeCallNumber();
    	}
    	else if (source.getCallNumber() == 1)
    	{
    		source.setPlaceIcon(2, x, y);
    		source.changeCallNumber();
    	}
    	else
    	{
    		source.setIcon(null);
    		source.changeCallNumber();
    	}
    }
}
