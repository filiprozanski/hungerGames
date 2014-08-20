package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IShootingPresenterControll {
	public void changeStatus(boolean ableToMove);

	public void setStats(int playerShips, int enemyShips, int accuracy);

	public void setStats(int playerShips, int enemyShips);

	public void changeBattlePlaceIcon(int x, int y, int type);

	public void changeStateIcon(int x, int y, int type);

	public void gameOver(boolean win);

	public void fchangeIcon(int x, int y, int type);

	public void changeGiveUpButtonLabel();

	public void showFrame();

	public void closeFrame();

	public void drawShip(Coordinates[] tab);

	public void changeShipState(int id);
}
