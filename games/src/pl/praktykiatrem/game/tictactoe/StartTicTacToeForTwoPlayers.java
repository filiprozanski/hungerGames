package pl.praktykiatrem.game.tictactoe;

import java.awt.Frame;

import pl.praktykiatrem.game.battleship.files.TTIcons;
import pl.praktykiatrem.game.menu.IMenuCallObserver;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.graphic.GamePresenter;
import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IController;
import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IDialogOwner;
import pl.praktykiatrem.game.tictactoe.rules.TTGame;
import pl.praktykiatrem.game.uniElements.dialogs.MessageDialog;
import pl.praktykiatrem.game.uniElements.enums.GameState;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class StartTicTacToeForTwoPlayers implements IController, IDialogOwner {
	private TTPlayerStatus player1;
	private TTPlayerStatus player2;
	private TTPlayerStatus currentPlayer;
	private TTGame g;
	private GamePresenter pres;
	private IMenuCallObserver menuObserver;

	public StartTicTacToeForTwoPlayers(String name1, String name2,
			IMenuCallObserver menuObserver, RulesType gameRules) {
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
		GameState result = g.makeMove(currentPlayer, x, y);
		if (result == GameState.WINNER) {
			pres.changeIcon(currentPlayer.getSign(), x, y);
			pres.lockGameBoard();
			gameOver(currentPlayer);
		} else if (result == GameState.GAME) {
			pres.changeIcon(currentPlayer.getSign(), x, y);
			changePlayer();
		} else if (result == GameState.DRAW) {
			gameOver(null);
		}
	}

	public void gameOver(TTPlayerStatus player) {
		MessageDialog dialog;
		if (player != null) {
			dialog = new MessageDialog(new Frame(), "Winner: "
					+ player.getName(), this, true);
		} else {
			dialog = new MessageDialog(new Frame(), "Draw", this, true);
		}
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
