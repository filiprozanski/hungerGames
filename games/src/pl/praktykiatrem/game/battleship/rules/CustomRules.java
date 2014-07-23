package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.Board;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public class CustomRules implements RulesInterface {

	private static final int BOARDSIZE_X = 10;
	private static final int BOARDSIZE_Y = 10;
	private static final int SHIPSNUMBER = 7;
	private static final int SHIPTYPES[] = { 6, 4, 4, 3, 3, 2, 2 };

	private boolean putShipOnPlace(Board plansza, int id, int x, int y) {
		if (!plansza.isShipOnPlace(x, y)) {
			plansza.placeShip(x, y, id);
			return true;
		} else
			return false;

	}

	@Override
	public boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y) {
		Board plansza = player.getPlansza();
		int x_temp = x;
		int y_temp = y;
		player.setShip(id, polesNumber);
		for (int i = 0; i < polesNumber; i++) {
			if (direction == Direction.HORIZONTAL)
				x_temp = x + i;
			else if (direction == Direction.VERTICAL)
				y_temp = y + i;
			if (!putShipOnPlace(plansza, id, x_temp, y_temp))
				return false;
		}
		return true;
	}

	@Override
	public boolean makeMove(PlayerStatus enemy, int x, int y) {
		// if (ValidationInstruments.isPlaceClear(enemy.getPlansza().gameBoard,
		// x, y))
		if (!enemy.getPlansza().isShipOnPlace(x, y)) {
			enemy.getPlansza().takeOut(x, y);
			return false;
		} else {
			if (enemy.getPlansza().isShipOnPlaceAndActive(x, y)) {
				int shipID = enemy.getPlansza().getShipID(x, y);
				enemy.takeOutShip(x, y);
				enemy.reducePolesNumber(x, y);
				if (enemy.getShip(shipID).isShipSunk())
					enemy.reduceShipsNumber();
				return true;
			} else
				return false;
		}
	}

	@Override
	public boolean getCurrentPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getBoardSize_X() {
		return BOARDSIZE_X;
	}

	@Override
	public int getBoardSize_Y() {
		return BOARDSIZE_Y;
	}

	@Override
	public int getShipsNumber() {
		return SHIPSNUMBER;
	}

	@Override
	public int[] getShipTypes() {
		return SHIPTYPES;
	}
}
