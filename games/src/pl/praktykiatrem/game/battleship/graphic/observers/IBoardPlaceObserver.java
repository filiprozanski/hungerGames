package pl.praktykiatrem.game.battleship.graphic.observers;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;

public interface IBoardPlaceObserver {

	public void dropShip(int id, int x, int y, Direction dir);

	public void clickedLeft(int x, int y);

	public int getPolesNumber(int id);

	public int getID(int x, int y);

	public Direction getDirection(int id);

	public void clickedRight(int x, int y);
}
