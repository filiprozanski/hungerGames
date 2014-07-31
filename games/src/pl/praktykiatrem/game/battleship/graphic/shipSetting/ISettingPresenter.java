package pl.praktykiatrem.game.battleship.graphic.shipSetting;

public interface ISettingPresenter {
	public void shipChoiceDone(int polesNumber, int id);

	public void placeShip(int x, int y, int freq);

	public void placeShipAtRandom();

	public ISettingView getView();

	public void resetBoard();

	public void playerIsReady();

}
