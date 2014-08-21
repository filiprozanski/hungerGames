package pl.praktykiatrem.game.tictactoe;

import java.awt.Frame;

import pl.praktykiatrem.game.menu.IMenuCallObserver;
import pl.praktykiatrem.game.tictactoe.files.Icons;
import pl.praktykiatrem.game.tictactoe.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.tictactoe.graphic.GamePresenter;
import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IController;
import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IDialogOwner;
import pl.praktykiatrem.game.tictactoe.rules.TTGame;
import pl.praktykiatrem.game.uniElements.dialogs.MessageDialog;
import pl.praktykiatrem.game.uniElements.enums.GameState;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class StartTicTacToeForTwoPlayers implements IController, IDialogOwner {
	private PlayerStatus player1;
	private PlayerStatus player2;
	private PlayerStatus currentPlayer;
	private TTGame g;
	private GamePresenter pres;
	private IMenuCallObserver menuObserver;

	public StartTicTacToeForTwoPlayers(String name1, String name2,
			IMenuCallObserver menuObserver, RulesType gameRules) {
		g = new TTGame();
		Icons.createImages(g.getButtonSize());

		this.menuObserver = menuObserver;

		player1 = new PlayerStatus(g.allocateSign());
		player1.setName(name1);

		player2 = new PlayerStatus(g.allocateSign());
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

	public void gameOver(PlayerStatus player) {
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
