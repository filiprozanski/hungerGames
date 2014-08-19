package pl.praktykiatrem.game.tictactoe.graphic.observers;

public interface IGameObserver {
	public void clicked(int x, int y);

	public void buttonClicked(int x, int y);

	public void abortGame();
}
