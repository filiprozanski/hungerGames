package pl.praktykiatrem.game.tictactoe.gameComponents;

import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class Place extends pl.praktykiatrem.game.uniElements.Place implements
		Cloneable {
	private Sign xo;

	public Place() {
		super();
	}

	public Place(Place p) {
		super(p.isPlaceInGame());
		this.xo = p.getSign();
	}

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

	@Override
	public Object clone() {
		return super.clone();
	}
}
