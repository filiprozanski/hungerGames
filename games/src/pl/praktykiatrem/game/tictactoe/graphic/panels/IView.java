package pl.praktykiatrem.game.tictactoe.graphic.panels;

import pl.praktykiatrem.game.tictactoe.rules.Sign;

public interface IView {

	public abstract void changeIcon(int x, int y, Sign sign);

	public abstract void lock();

	public abstract void disableButton(int x, int y);

}