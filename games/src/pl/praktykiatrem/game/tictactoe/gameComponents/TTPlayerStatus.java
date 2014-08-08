package pl.praktykiatrem.game.tictactoe.gameComponents;

import pl.praktykiatrem.game.tictactoe.rules.Sign;
import pl.praktykiatrem.game.uniElements.PlayerStatus;

public class TTPlayerStatus extends PlayerStatus {
	private Sign sign;

	public TTPlayerStatus(Sign sign) {
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
