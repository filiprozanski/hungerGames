package pl.praktykiatrem.game.tictactoe.graphic.panels;

import pl.praktykiatrem.game.tictactoe.rules.Sign;

public interface IView {

	public abstract void changeIcon(int x, int y, Sign sign);

	public abstract void lock();

	public abstract void disableButton(int x, int y);

	public abstract void showGame();

	public abstract void setSignIcon(Sign sign);

	public abstract void closeFrame();

}