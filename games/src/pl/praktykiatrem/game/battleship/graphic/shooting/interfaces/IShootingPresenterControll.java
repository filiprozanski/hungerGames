package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;

public interface IShootingPresenterControll {
	public void changeStatus(boolean ableToMove);

	public void setStats(int playerShips, int enemyShips, int accuracy);

	public void setStats(int playerShips, int enemyShips);

	public void changeBattlePlaceIcon(Coordinates coords, int type);

	public void changeStateIcon(Coordinates coords, int type);

	public void gameOver(boolean win);

	public void fchangeIcon(Coordinates coords, int type);

	public void changeGiveUpButtonLabel();

	public void showFrame(String name);

	public void closeFrame();

	public void drawShip(Coordinates[] tab);

	public void changeShipState(int id);

	public void drawLeftShips(ArrayList<Coordinates> leftShips);
}
