package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.ArtificialIntelligence.ComputerBoard;
import pl.praktykiatrem.game.battleship.gameComponents.Board;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.Direction;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

/**
 * klasa opisuj¹ca wszystkie zasady gry
 * 
 * @author Filip Ró¿añski
 *
 */
public class OriginalRules extends Rules {

	private boolean putShipOnPlace(Board plansza, int id, int x, int y) {
		if (!plansza.isShipOnPlace(x, y)) {
			plansza.placeShip(x, y, id);
			return true;
		} else
			return false;

	}

	private boolean takeShipOfPlace(Board plansza, int id, int x, int y) {
		if (plansza.getShipID(x, y) == id) {
			plansza.displaceShip(x, y);
			return true;
		} else
			return false;
	}

	private boolean shipPlacingValidation(Board plansza, int polesNumber,
			Direction dir, int x, int y) {
		int x_temp = 0;
		int y_temp = 0;
		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_V)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_H)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber + 2; i++) {
				for (int j = 0; j < 3; j++) {
					x_temp = x + j - 1;
					y_temp = y + i - 1;
					if ((x + j - 1) >= 0 && (x + j - 1) < BOARDSIZE_V
							&& (y + i - 1) >= 0 && (y + i - 1) < BOARDSIZE_H)
						if (plansza.isShipOnPlace(x_temp, y_temp))
							return false;
				}
			}
		} else {
			for (int i = 0; i < polesNumber + 2; i++) {
				for (int j = 0; j < 3; j++) {
					x_temp = x + i - 1;
					y_temp = y + j - 1;
					if (x_temp >= 0 && x_temp < BOARDSIZE_V && y_temp >= 0
							&& y_temp < BOARDSIZE_H)
						if (plansza.isShipOnPlace(x_temp, y_temp))
							return false;
				}
			}
		}
		return true;
	}

	public boolean shipPlacingValidation(ComputerBoard board, int polesNumber,
			Direction dir, int x, int y) {
		int x_temp = 0;
		int y_temp = 0;
		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_V)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_H)
				return false;
		}

		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber + 2; i++) {
				for (int j = 0; j < 3; j++) {
					x_temp = x + j - 1;
					y_temp = y + i - 1;
					if ((x + j - 1) >= 0 && (x + j - 1) < BOARDSIZE_V
							&& (y + i - 1) >= 0 && (y + i - 1) < BOARDSIZE_H)
						if (board.isSunk(x_temp, y_temp))
							return false;
				}
			}
		} else {
			for (int i = 0; i < polesNumber + 2; i++) {
				for (int j = 0; j < 3; j++) {
					x_temp = x + i - 1;
					y_temp = y + j - 1;
					if (x_temp >= 0 && x_temp < BOARDSIZE_V && y_temp >= 0
							&& y_temp < BOARDSIZE_H)
						if (board.isSunk(x_temp, y_temp))
							return false;
				}
			}
		}

		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(x, y + i)) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(x + i, y)) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean shipDisplacingValidation(Board plansza, int polesNumber,
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
	public boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y) {
		Board plansza = player.getPlansza();
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
	public boolean displaceShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, int x, int y) {
		Board plansza = player.getPlansza();
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
	public int makeMove(PlayerStatus enemy, int x, int y) {
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

	@Override
	public void resetGame(PlayerStatus player) {
		player.resetStatus();
	}

}
