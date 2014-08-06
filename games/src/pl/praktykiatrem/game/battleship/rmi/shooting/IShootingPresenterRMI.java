package pl.praktykiatrem.game.battleship.rmi.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.battleship.graphic.shooting.interfaces.IShootingView;

public interface IShootingPresenterRMI {
	public void shot(int x, int y);

	public IShootingView getView();

	void changeIcon(int x, int y, int type);

	void changeStatus(boolean ableToMove);

	void setStats(int playerShips, int enemyShips, int accuracy);

	void setStats(int playerShips, int enemyShips);

	void drawShip(Coordinates[] tab);

	void changeBattlePlaceIcon(int x, int y, int type);

	void changeStateIcon(int x, int y, int type);

	public void gameOver(boolean win);

	void fchangeIcon(int x, int y, int type);

	public void giveUp();

	void changeGiveUpButtonLabel();

	void showFrame();

	void closeFrame();

}
