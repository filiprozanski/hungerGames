package pl.praktykiatrem.game.tictactoe;

import java.awt.Frame;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.menu.IMenuCallObserver;
import pl.praktykiatrem.game.tictactoe.ai.TTTDifficulty;
import pl.praktykiatrem.game.tictactoe.ai.TTTEasy;
import pl.praktykiatrem.game.tictactoe.ai.TTTMedium;
import pl.praktykiatrem.game.tictactoe.files.Icons;
import pl.praktykiatrem.game.tictactoe.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.tictactoe.graphic.GamePresenter;
import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IController;
import pl.praktykiatrem.game.tictactoe.graphic.interfaces.IDialogOwner;
import pl.praktykiatrem.game.tictactoe.rules.TTGame;
import pl.praktykiatrem.game.uniElements.dialogs.MessageDialog;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;
import pl.praktykiatrem.game.uniElements.enums.GameState;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

public class StartTicTacToeForOnePlayer implements IController, IDialogOwner {
	private PlayerStatus player1;
	private PlayerStatus player2;
	private PlayerStatus currentPlayer;
	private TTTDifficulty dummy;
	private TTGame g;
	private GamePresenter pres;
	private IMenuCallObserver menuObserver;

	public StartTicTacToeForOnePlayer(String name1,
			IMenuCallObserver menuObserver, RulesType gameRules, Difficulty dif) {
		g = new TTGame();
		Icons.createImages(g.getButtonSize());

		this.menuObserver = menuObserver;

		player1 = new PlayerStatus(g.allocateSign());
		player1.setName(name1);

		player2 = new PlayerStatus(g.allocateSign());
		player2.setName("PC");

		switch (dif) {
		case EASY:
			dummy = new TTTEasy(g.getRules(), player2);
			break;
		case MEDIUM:
			dummy = new TTTMedium(g.getRules(), player2);
			break;
		}

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
		if (currentPlayer == player1) {
			currentPlayer = player2;
			Coordinates c = dummy.getMove();
			makeMove(c.getX(), c.getY());
		} else
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
