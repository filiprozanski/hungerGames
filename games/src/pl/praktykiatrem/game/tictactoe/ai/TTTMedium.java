package pl.praktykiatrem.game.tictactoe.ai;

//https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlace;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.rules.Rules;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class TTTMedium implements TTTDifficulty {
	private TTPlayerStatus computer;
	private TTPlayerStatus player;
	private TTPlayerStatus currentPlayer;
	private ArrayList<Integer> scores;
	private Rules rules;

	public TTTMedium(Rules rules, TTPlayerStatus me) {
		computer = me;
		this.rules = rules;
		player = new TTPlayerStatus(Sign.X);
		scores = new ArrayList<Integer>();

		currentPlayer = computer;
	}

	public Coordinates getMove() {
		Coordinates result = computeMove(rules.getActualBoard(), 5, computer);
		return result;

	}

	private Coordinates computeMove(TTBoard board, int depth,
			TTPlayerStatus currentPlayer) {

		ArrayList<Integer> internalScores = new ArrayList<Integer>();
		ArrayList<Coordinates> nextMoves = new ArrayList<Coordinates>();

		TTBoard myBoard = new TTBoard(board);
		TTPlace[][] gameBoard = myBoard.getGameBoard();
		LineChecker check = new LineChecker(computer.getSign(),
				player.getSign(), rules.getSignsToWin());

		getPossibleMoves(myBoard, nextMoves);

		int bestScore = 0;
		int currentScore = 0;
		Coordinates best = new Coordinates(-1, -1);

		if (currentPlayer == computer)
			bestScore = Integer.MIN_VALUE;
		else if (currentPlayer == player)
			bestScore = Integer.MAX_VALUE;

		if (nextMoves.isEmpty() || depth == 0)
			bestScore = check.evaluateCoords(gameBoard);
		else {
			for (Coordinates move : nextMoves) {
				int x = move.getX();
				int y = move.getY();

				myBoard.setSign(currentPlayer.getSign(), x, y);

				if (currentPlayer == computer) {
					currentScore = computeMove(myBoard, depth - 1, player)
							.getX();
					if (currentScore > bestScore) {
						bestScore = currentScore;
						best = move;
					}
				} else {
					currentScore = computeMove(myBoard, depth - 1, computer)
							.getX();
					if (currentScore < bestScore) {
						bestScore = currentScore;
						best = move;
					}
				}
			}
		}
		return best;
	}

	private void changePlayer() {
		if (currentPlayer == computer)
			currentPlayer = player;
		else
			currentPlayer = computer;
	}

	private void getPossibleMoves(TTBoard board, ArrayList<Coordinates> moves) {
		TTPlace[][] gameBoard = board.getGameBoard();
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				if (gameBoard[i][j].isPlaceInGame()) {
					Coordinates tab = new Coordinates(i, j);
					moves.add(tab);
				}
	}
}
