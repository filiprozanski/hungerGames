package pl.praktykiatrem.game.tictactoe.graphic;

import pl.praktykiatrem.game.battleship.files.TTIcons;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.rules.TTGame;

public class StartGraphicForTwoPlayers implements IController {
	private TTPlayerStatus player1;
	private TTPlayerStatus player2;
	private TTPlayerStatus currentPlayer;
	private TTGame g;
	private GamePresenter pres;

	public StartGraphicForTwoPlayers(String name1, String name2, int gameRules) {
		g = new TTGame();
		TTIcons.createImages();

		player1 = new TTPlayerStatus(g.allocateSign());
		player1.setName(name1);

		player2 = new TTPlayerStatus(g.allocateSign());
		player2.setName(name2);

		pres = new GamePresenter(g.getHorizontalSize(), g.getVerticalSize(),
				this);

		currentPlayer = player1;

		pres.showGame();
	}

	@Override
	public void makeMove(int x, int y) {
		if (g.makeMove(currentPlayer, x, y)) {
			gameOver(currentPlayer);
			pres.changeIcon(currentPlayer.getSign(), x, y);
			pres.lockGameBoard();
		} else {
			pres.changeIcon(currentPlayer.getSign(), x, y);
			changePlayer();
		}
	}

	public void gameOver(TTPlayerStatus player) {
		System.out.println("Koniec gry, wygral " + player.getName());
	}

	public void changePlayer() {
		if (currentPlayer == player1)
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}

}
