package pl.praktykiatrem.game.battleship.graphic;

public interface IBasicPresenter {
	public void shipChoiceDone(int polesNumber, int id);

	public void placeShip(int x, int y, int freq);
}
