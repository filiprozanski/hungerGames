package pl.praktykiatrem.game.battleship.graphic.shooting;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;

public interface IShootingPresenter {
	public void shot(int x, int y);

	public IShootingView getView();

	void changeIcon(int x, int y, int type);

	void changeStatus(boolean ableToMove);

	void setStats(int playerShips, int enemyShips, int accuracy);

	void setStats(int playerShips, int enemyShips);

	void drawShip(Coordinates[] tab);

	void changeBattlePlaceIcon(int x, int y, int type);

	void changeStateIcon(int x, int y, int type);

	public void GameOver(boolean win);

	void fchangeIcon(int x, int y, int type);

	public void giveUp();

	void changeGiveUpButtonLabel();

}
