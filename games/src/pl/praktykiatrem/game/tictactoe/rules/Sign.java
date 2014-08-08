package pl.praktykiatrem.game.tictactoe.rules;

public enum Sign {
	X, O;

	public static Sign getSign(char c) {
		if (c == 'x' || c == 'X')
			return X;
		else
			return O;
	}
}
