package pl.praktykiatrem.game.tictactoe.graphic;

import java.awt.Frame;

import pl.praktykiatrem.game.battleship.files.TTIcons;
import pl.praktykiatrem.game.menu.IMenuCallObserver;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.rules.TTGame;

public class StartGraphicForTwoPlayers implements IController, IDialogOwner {
	private TTPlayerStatus player1;
	private TTPlayerStatus player2;
	private TTPlayerStatus currentPlayer;
	private TTGame g;
	private GamePresenter pres;
	private IMenuCallObserver menuObserver;

	public StartGraphicForTwoPlayers(String name1, String name2,
			IMenuCallObserver menuObserver, int gameRules) {
		g = new TTGame();
		TTIcons.createImages(g.getButtonSize());

		this.menuObserver = menuObserver;

		player1 = new TTPlayerStatus(g.allocateSign());
		player1.setName(name1);

		player2 = new TTPlayerStatus(g.allocateSign());
		player2.setName(name2);

		pres = new GamePresenter(g.getHorizontalSize(), g.getVerticalSize(),
				g.getButtonSize(), this);

		currentPlayer = player1;
		pres.setSignIcon(currentPlayer.getSign());

		pres.showGame();
	}

	@Override
	public void makeMove(int x, int y) {
		if (g.makeMove(currentPlayer, x, y)) {
			pres.changeIcon(currentPlayer.getSign(), x, y);
			pres.lockGameBoard();
			gameOver(currentPlayer);
		} else {
			pres.changeIcon(currentPlayer.getSign(), x, y);
			changePlayer();
		}
	}

	public void gameOver(TTPlayerStatus player) {
		WinDialog dialog = new WinDialog(new Frame(), player.getName(), this,
				true);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	public void changePlayer() {
		if (currentPlayer == player1)
			currentPlayer = player2;
		else
			currentPlayer = player1;

		pres.setSignIcon(currentPlayer.getSign());
	}

	@Override
	public void clicked() {
		pres.closeGame();
		menuObserver.callMenu();
	}

	@Override
	public void abortGame() {
		changePlayer();
		gameOver(currentPlayer);
	}

}
