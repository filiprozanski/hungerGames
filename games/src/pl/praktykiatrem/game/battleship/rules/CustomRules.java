package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.console.BoardDrawing;
import pl.praktykiatrem.game.battleship.gameComponents.Board;
import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public class CustomRules implements IRules {

    private static final int BOARDSIZE_H = 10;
    private static final int BOARDSIZE_V = 10;
    private static final int SHIPTYPES[] = { 6, 4, 4, 3, 3, 2, 2 };

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

    public boolean shipPlacingValidation(Board plansza, int polesNumber,
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

    public boolean shipDisplacingValidation(Board plansza, int polesNumber,
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
	    System.out.println(player.isShipSet(id));

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
	    BoardDrawing.drawGameBoardForPlayer(plansza);
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
	    BoardDrawing.drawGameBoardForPlayer(plansza);
	    return true;
	} else
	    return false;
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
		if (enemy.reducePolesNumber(x, y))
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

    public int getShipsNumber() {
	return SHIPTYPES.length;
    }

    @Override
    public int getActiveShipsNumber(PlayerStatus player) {
	return player.getShipsNumber();
    }
}
