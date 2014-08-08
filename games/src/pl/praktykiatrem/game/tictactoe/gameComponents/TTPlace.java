package pl.praktykiatrem.game.tictactoe.gameComponents;

import pl.praktykiatrem.game.tictactoe.rules.Sign;
import pl.praktykiatrem.game.uniElements.Place;

public class TTPlace extends Place {
	private Sign xo;

	@Override
	public void takeOut() {
		isInGame = false;
	}

	@Override
	public boolean isPlaceInGame() {
		return isInGame;
	}

	public void setOnPlace(Sign xo) {
		this.xo = xo;
		isInGame = false;
	}

	@Override
	public void resetPlace() {
		isInGame = true;
		xo = null;
	}

	public Sign getSign() {
		return xo;
	}
}
