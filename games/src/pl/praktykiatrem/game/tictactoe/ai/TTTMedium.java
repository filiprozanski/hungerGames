package pl.praktykiatrem.game.tictactoe.ai;

//https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.components.Coordinates;
import pl.praktykiatrem.game.tictactoe.gameComponents.Board;
import pl.praktykiatrem.game.tictactoe.gameComponents.Place;
import pl.praktykiatrem.game.tictactoe.gameComponents.PlayerStatus;
import pl.praktykiatrem.game.tictactoe.rules.Rules;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class TTTMedium implements TTTDifficulty {
	private PlayerStatus computer;
	private PlayerStatus player;
	private PlayerStatus currentPlayer;
	private ArrayList<Integer> scores;
	private Rules rules;

	public TTTMedium(Rules rules, PlayerStatus me) {
		computer = me;
		this.rules = rules;
		player = new PlayerStatus(Sign.X);
		scores = new ArrayList<Integer>();

		currentPlayer = computer;
	}

	public Coordinates getMove() {
		int[] result = computeMove(rules.getActualBoard(), 5, computer);
		return new Coordinates(result[1], result[2]);

	}

	private int[] computeMove(Board board, int depth,
			PlayerStatus currentPlayer) {

		ArrayList<Integer> internalScores = new ArrayList<Integer>();
		ArrayList<Coordinates> nextMoves = new ArrayList<Coordinates>();

		Board myBoard = new Board(board);
		Place[][] gameBoard = myBoard.getGameBoard();
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
					int[] result = computeMove(myBoard, depth - 1, player);
					currentScore = result[0];
					if (currentScore > bestScore) {
						bestScore = currentScore;
						best = move;
					}
				} else {
					int[] result = computeMove(myBoard, depth - 1, computer);
					currentScore = result[0];
					if (currentScore < bestScore) {
						bestScore = currentScore;
						best = move;
					}
				}
			}
		}
		return new int[] { currentScore, best.getX(), best.getY() };
	}

	private void changePlayer() {
		if (currentPlayer == computer)
			currentPlayer = player;
		else
			currentPlayer = computer;
	}

	private void getPossibleMoves(Board board, ArrayList<Coordinates> moves) {
		Place[][] gameBoard = board.getGameBoard();
		for (int i = 0; i < gameBoard.length; i++)
			for (int j = 0; j < gameBoard[i].length; j++)
				if (gameBoard[i][j].isPlaceInGame()) {
					Coordinates tab = new Coordinates(i, j);
					moves.add(tab);
				}
	}
}
