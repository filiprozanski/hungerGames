package pl.praktykiatrem.game.tictactoe.ai;

import java.util.ArrayList;

import pl.praktykiatrem.game.battleship.gameComponents.Coordinates;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTBoard;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlace;
import pl.praktykiatrem.game.tictactoe.gameComponents.TTPlayerStatus;
import pl.praktykiatrem.game.tictactoe.rules.CustomRules;
import pl.praktykiatrem.game.tictactoe.rules.Rules;
import pl.praktykiatrem.game.tictactoe.rules.Sign;
import pl.praktykiatrem.game.uniElements.enums.GameState;

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
		currentPlayer = computer;
		int index;
		ArrayList<Coordinates> moves = new ArrayList<Coordinates>();
		getPossibleMoves(rules.getActualBoard(), moves);

		scores = computeMove(rules, 0);

		for (int i = 0; i < scores.size(); i++) {
			if (scores.contains(10 + i)) {
				index = scores.indexOf(new Integer(10 + i));
				return moves.get(index);
			}
		}

		if (scores.contains(0)) {
			index = scores.indexOf(new Integer(0));
			return moves.get(index);
		} else
			return moves.get(0);

	}

	private ArrayList<Integer> computeMove(Rules rules, int depth) {
		ArrayList<Integer> internalScores = new ArrayList<Integer>();
		ArrayList<Coordinates> internalMoves = new ArrayList<Coordinates>();
		int myDepth = depth++;
		CustomRules temp = (CustomRules) rules;
		Rules myRules = new CustomRules(temp);

		getPossibleMoves(myRules.getActualBoard(), internalMoves);

		for (int i = 0; i < internalMoves.size(); i++) {
			Coordinates coord = internalMoves.get(i);
			GameState result = myRules.makeMove(currentPlayer, coord.getX(),
					coord.getY());
			if (result == GameState.WINNER && currentPlayer == computer) {
				internalScores.add(10);
			} else if (result == GameState.WINNER && currentPlayer != computer) {
				internalScores.add(-10);
			} else if (result == GameState.DRAW) {
				internalScores.add(0);
			} else if (result == GameState.GAME) {
				changePlayer();
				ArrayList<Integer> list = computeMove(myRules, myDepth);
				if (list.contains(-10))
					internalScores.add(-10);
				else if (list.contains(0))
					internalScores.add(0);
				else
					internalScores.add(10 + myDepth);
			}
		}

		return internalScores;
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
