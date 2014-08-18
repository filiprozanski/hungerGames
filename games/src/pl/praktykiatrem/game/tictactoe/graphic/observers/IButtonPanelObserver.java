package pl.praktykiatrem.game.tictactoe.graphic.observers;

public interface IButtonPanelObserver {
	public void abortGame();

	public void clicked(int x, int y);
}
