package pl.praktykiatrem.game.tictactoe.gameComponents;

import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class PlayerStatus extends
		pl.praktykiatrem.game.uniElements.PlayerStatus {
	private Sign sign;

	public PlayerStatus(Sign sign) {
		super();
		this.sign = sign;
	}

	public Sign getSign() {
		return sign;
	}

	@Override
	public void resetStatus() {

	}
}
