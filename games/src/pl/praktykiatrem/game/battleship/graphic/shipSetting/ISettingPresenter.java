package pl.praktykiatrem.game.battleship.graphic.shipSetting;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;

public interface ISettingPresenter {
	public void shipChoiceDone(int polesNumber, int id);

	public void placeShip(int x, int y, int freq);

	public void placeShipAtRandom();

	public ISettingView getView();

	public void resetBoard();

	public void playerIsReady();

	public void showFrame();

	public void closeFrame();

	public void placeShipsOnView(int randX, int randY, Direction rand_dir,
			int i, int polesNumber);

}
