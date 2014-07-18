package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShipButton extends JButton {
	private int callNumber;
	
	public int getCallNumber()
	{
		return callNumber;
	}
	
	public void changeCallNumber()
	{
		if (callNumber == 2)
			callNumber = 0;
		else
			callNumber = callNumber + 1;
	}
	
	public void setPlaceIcon(int type, int x, int y) {
		ShipIcons.createImages();
        setIcon(ShipIcons.getIcon(type));
    }
}
