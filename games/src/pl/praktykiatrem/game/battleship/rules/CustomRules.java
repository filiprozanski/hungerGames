package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.PlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Board;

public class CustomRules implements RulesInterface{
	private boolean putShipOnPlace(Board plansza, int id, int x, int y)
    {
    	if (!plansza.isShipOnPlace(x, y))
		{
		    plansza.placeShip(x, y, id);
		    return true;
		}
		else
			return false;
    	
    }
    public boolean placeShips(PlayerStatus player, int id, int polesNumber, char direction, int x, int y)
    {
    	Board plansza = player.getPlansza();
    	int x_temp=x;
    	int y_temp=y;
        player.setShip(id, polesNumber);
        for(int i = 0; i < polesNumber; i++){
        	if (direction == Direction.HORIZONTAL.getAbbreviation())
        		x_temp=x+i;
        	else if (direction == Direction.VERTICAL.getAbbreviation())
        		y_temp=y+i;
        	if(!putShipOnPlace(plansza, id, x_temp, y_temp))
    			return false;   
        }
        return true;
    }
    
    public boolean makeMove(PlayerStatus enemy, int x, int y)
    {
        //if (ValidationInstruments.isPlaceClear(enemy.getPlansza().gameBoard, x, y))
        if (!enemy.getPlansza().isShipOnPlace(x, y))
        {
            enemy.getPlansza().takeOut(x, y);
            return false;
        }
        else
        {
            if(enemy.getPlansza().isShipOnPlaceAndActive(x, y))
            {
                int shipID = enemy.getPlansza().getShipID(x, y);
                enemy.takeOutShip(x, y);
                enemy.getShip(shipID).reducePolesNumber();
                if (enemy.getShip(shipID).isShipSunk())
                    enemy.reduceShipsNumber();
                return true;
            }
            else
            	return false;
        }
    }
}
