package pl.praktykiatrem.game.battleship.graphic.shooting.interfaces;

public interface IShootingPresenter {
	public void shot(int x, int y);

	void changeIcon(int x, int y, int type);

	void changeShipState(int shipID);

	public void giveUp();

	public void showHint();

}
