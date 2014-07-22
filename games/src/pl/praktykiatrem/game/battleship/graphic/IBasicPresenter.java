package pl.praktykiatrem.game.battleship.graphic;

public interface IBasicPresenter {
	public void shipChoiceDone(int polesNumber, int id);

	public void putInVerticalDirection(int x, int y);

	public void putInHorizontalDirection(int x, int y);

	public void clearLastPlacing(int x, int y);
}
