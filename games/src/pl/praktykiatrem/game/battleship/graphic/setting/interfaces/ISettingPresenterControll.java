package pl.praktykiatrem.game.battleship.graphic.setting.interfaces;

import pl.praktykiatrem.game.uniElements.enums.Direction;

public interface ISettingPresenterControll {
	public void resetBoard();

	public void playerIsReady();

	public void showFrame();

	public void closeFrame();

	public void placeShipsOnView(int randX, int randY, Direction rand_dir,
			int i, int polesNumber);
}
