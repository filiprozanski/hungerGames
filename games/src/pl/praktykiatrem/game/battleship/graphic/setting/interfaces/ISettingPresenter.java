package pl.praktykiatrem.game.battleship.graphic.setting.interfaces;

import pl.praktykiatrem.game.uniElements.enums.Direction;

public interface ISettingPresenter {
	// public void shipChoiceDone(int polesNumber, int id);

	// public void placeShip(int x, int y, int freq);

	public void placeShipAtRandom();

	public void resetBoard();

	public void playerIsReady();

	public void dropShip(int id, int x, int y, Direction dir);

	public void clickedLeft(int x, int y);

	public int getPolesNumber(int id);

	public int getID(int x, int y);

	public Direction getDirection(int id);

	public void clickedRight(int x, int y);
}
