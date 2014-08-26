package pl.praktykiatrem.game.battleship.rules;

import java.io.Serializable;

import pl.praktykiatrem.game.battleship.ai.ComputerBoard;
import pl.praktykiatrem.game.battleship.components.Board;
import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.battleship.components.PlayerStatus;
import pl.praktykiatrem.game.battleship.components.ShootResult;
import pl.praktykiatrem.game.uniElements.enums.Direction;

/**
 * klasa opisuj¹ca wszystkie zasady gry
 * 
 * @author Filip Ró¿añski
 *
 */
public class CustomRules extends Rules implements Serializable {
	private static final long serialVersionUID = -2745861399581281629L;
	private final int BOARDSIZE_H = 20;
	private final int BOARDSIZE_V = 20;
	private final int SHIPTYPES[] = { 7, 2, 1, 1, 2, 3 };
	private final GameConstants constants = new GameConstants(BOARDSIZE_V,
			BOARDSIZE_H, SHIPTYPES);

	@Override
	public int getBoardSize_H() {
		return BOARDSIZE_H;
	}

	@Override
	public int getBoardSize_V() {
		return BOARDSIZE_V;
	}

	@Override
	public int[] getShipTypes() {
		return SHIPTYPES;
	}

	@Override
	public int getShipsNumber() {
		return SHIPTYPES.length;
	}

	@Override
	public int getShipTypes(int id) {
		return SHIPTYPES[id];
	}

	@Override
	public GameConstants getConstants() {
		return constants;
	}

	private boolean putShipOnPlace(Board plansza, int id, Coordinates coords) {
		if (!plansza.isShipOnPlace(coords)) {
			plansza.placeOnBoard(coords, id);
			return true;
		} else
			return false;

	}

	private boolean takeShipOfPlace(Board plansza, int id, Coordinates coords) {
		if (plansza.getShipID(coords) == id) {
			plansza.resetPlace(coords);
			return true;
		} else
			return false;
	}

	@Override
	public boolean shipPlacingValidation(Board plansza, int polesNumber,
			Direction dir, Coordinates coords) {
		int x = coords.getX();
		int y = coords.getY();

		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_H)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_V)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (plansza.isShipOnPlace(new Coordinates(x, y + i)))
					return false;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (plansza.isShipOnPlace(new Coordinates(x + i, y)))
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean shipPlacingValidation(ComputerBoard board, int polesNumber,
			Direction dir, Coordinates coords) {
		int x = coords.getX();
		int y = coords.getY();

		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_H)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_V)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(new Coordinates(x, y + i))
						|| board.isSunk(new Coordinates(x, y + i))) {
					return false;
				}
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (board.isMiss(new Coordinates(x + i, y))
						|| board.isSunk(new Coordinates(x + i, y))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean shipDisplacingValidation(Board plansza, int polesNumber,
			Direction dir, Coordinates coords) {
		int x = coords.getX();
		int y = coords.getY();

		if (dir == Direction.HORIZONTAL) {
			if (y + polesNumber > BOARDSIZE_H)
				return false;
		} else if (dir == Direction.VERTICAL) {
			if (x + polesNumber > BOARDSIZE_V)
				return false;
		}
		if (dir == Direction.HORIZONTAL) {
			for (int i = 0; i < polesNumber; i++) {
				if (!plansza.isShipOnPlace(new Coordinates(x, y + i)))
					return false;
			}
		} else {
			for (int i = 0; i < polesNumber; i++) {
				if (!plansza.isShipOnPlace(new Coordinates(x + i, y)))
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean placeShips(PlayerStatus player, int id, int polesNumber,
			Direction direction, Coordinates coords) {
		Board plansza = player.getPlansza();
		if (shipPlacingValidation(plansza, polesNumber, direction, coords)
				&& !player.isShipSet(id)) {

			int x = coords.getX();
			int y = coords.getY();

			int x_temp = coords.getX();
			int y_temp = coords.getY();

			player.getShip(id).setShipSet(true);
			player.getShip(id).setDirection(direction);
			player.increaseShipsNumber();

			for (int i = 0; i < polesNumber; i++) {
				if (direction == Direction.HORIZONTAL)
					y_temp = y + i;
				else if (direction == Direction.VERTICAL)
					x_temp = x + i;
				if (!putShipOnPlace(plansza, id,
						new Coordinates(x_temp, y_temp)))
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
			Direction direction, Coordinates coords) {
		Board plansza = player.getPlansza();
		if (shipDisplacingValidation(plansza, polesNumber, direction, coords)
				&& player.isShipSet(id)) {
			int x = coords.getX();
			int y = coords.getY();

			int x_temp = coords.getX();
			int y_temp = coords.getY();

			player.getShip(id).setShipSet(false);
			player.getShip(id).setDirection(Direction.HORIZONTAL);
			player.decreaseShipsNumber();
			for (int i = 0; i < polesNumber; i++) {
				if (direction == Direction.HORIZONTAL)
					y_temp = y + i;
				else if (direction == Direction.VERTICAL)
					x_temp = x + i;
				if (!takeShipOfPlace(plansza, id, new Coordinates(x_temp,
						y_temp)))
					return false;
				else
					player.removeCoordinate(id, new Coordinates(x_temp, y_temp));
			}
			return true;
		} else
			return false;
	}

	@Override
	public ShootResult makeMove(PlayerStatus enemy, Coordinates coords) {
		Board board = enemy.getPlansza();
		if (!board.isShipOnPlace(coords)) {
			enemy.getPlansza().takeOut(coords);
			return ShootResult.MISS;
		} else {
			if (board.isShipOnPlaceAndActive(coords)) {
				enemy.takeOutShip(coords);
				if (enemy.reducePolesNumber(coords) == 0) {
					enemy.reduceShipsNumber();
					return ShootResult.SINK;
				}
				return ShootResult.HIT;
			} else
				return ShootResult.MISS;
		}
	}
}
