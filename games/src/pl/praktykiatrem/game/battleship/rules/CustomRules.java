package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.BSBoard;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;

/**
 * klasa opisuj¹ca wszystkie zasady gry
 * 
 * @author Filip Ró¿añski
 *
 */
public class CustomRules extends Rules {
	private boolean putShipOnPlace(BSBoard plansza, int id, int x, int y) {
		if (!plansza.isShipOnPlace(x, y)) {
			plansza.placeOnBoard(x, y, id);
			return true;
		} else
			return false;

	}

	private boolean takeShipOfPlace(BSBoard plansza, int id, int x, int y) {
		if (plansza.getShipID(x, y) == id) {
			plansza.resetPlace(x, y);
			return true;
		} else
			return false;
	}

	@Override
	public boolean shipPlacingValidation(BSBoard plansza, int polesNumber,
			Direction dir, int x, int y) {
		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_V)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_H)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (plansza.isShipOnPlace(x, y + i))
					return false;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (plansza.isShipOnPlace(x + i, y))
					return false;
			}
		}
		return true;
	}

	public boolean shipPlacingValidation(ComputerBoard board, int polesNumber,
			Direction dir, int x, int y) {
		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_H)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_V)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(x, y + i) || board.isSunk(x, y + i)) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(x + i, y) || board.isSunk(x + i, y)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean shipDisplacingValidation(BSBoard plansza, int polesNumber,
			Direction dir, int x, int y) {
		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_V)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_H)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (!plansza.isShipOnPlace(x, y + i))
					return false;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (!plansza.isShipOnPlace(x + i, y))
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean placeShips(BSPlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y) {
		BSBoard plansza = player.getPlansza();
		if (shipPlacingValidation(plansza, polesNumber, direction, x, y)
				&& !player.getShip(id).isShipSet()) {

			int x_temp = x;
			int y_temp = y;
			player.getShip(id).setShipSet(true);
			player.increaseShipsNumber();

			for (int i = 0; i < polesNumber; i++) {
				if (direction == Direction.HORIZONTAL)
					y_temp = y + i;
				else if (direction == Direction.VERTICAL)
					x_temp = x + i;
				if (!putShipOnPlace(plansza, id, x_temp, y_temp))
					return false;
				else
					player.addCoordinates(id, new Coordinates(x_temp, y_temp));
			}
			return true;
		} else
			return false;
	}

	@Override
	public boolean displaceShips(BSPlayerStatus player, int id,
			int polesNumber, Direction direction, int x, int y) {
		BSBoard plansza = player.getPlansza();
		if (shipDisplacingValidation(plansza, polesNumber, direction, x, y)
				&& player.getShip(id).isShipSet()) {
			int x_temp = x;
			int y_temp = y;
			player.getShip(id).setShipSet(false);
			player.decreaseShipsNumber();
			for (int i = 0; i < polesNumber; i++) {
				if (direction == Direction.HORIZONTAL)
					y_temp = y + i;
				else if (direction == Direction.VERTICAL)
					x_temp = x + i;
				if (!takeShipOfPlace(plansza, id, x_temp, y_temp))
					return false;
				else
					player.removeCoordinate(id, new Coordinates(x_temp, y_temp));
			}
			return true;
		} else
			return false;
	}

	@Override
	public int makeMove(BSPlayerStatus enemy, int x, int y) {
		if (!enemy.getPlansza().isShipOnPlace(x, y)) {
			enemy.getPlansza().takeOut(x, y);
			return 0;
		} else {
			if (enemy.getPlansza().isShipOnPlaceAndActive(x, y)) {
				enemy.takeOutShip(x, y);
				if (enemy.reducePolesNumber(x, y) == 0) {
					enemy.reduceShipsNumber();
					return 2;
				}
				return 1;
			} else
				return 0;
		}
	}
}
