package pl.praktykiatrem.game.uniElements;

import pl.praktykiatrem.game.battleship.gameComponents.BSBoard;
import pl.praktykiatrem.game.battleship.gameComponents.BSPlayerStatus;

public abstract class PlayerStatus {

	protected BSBoard plansza;
	protected Player gamer;
	int playerID;
	private static int counter = 100001;

	public PlayerStatus() {
		playerID = counter++;
	}

	/**
	 * Por�wnuje graczy wykorzystuj�c do tego nadane im ID
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BSPlayerStatus))
			return false;

		BSPlayerStatus other2 = (BSPlayerStatus) other;
		if (this.playerID == other2.playerID)
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return imi� gracza
	 */
	public String getName() {
		return gamer.getName();
	}

	/**
	 * Ustawia imi� gracza
	 * 
	 * @param name
	 */
	public void setName(String name) {
		gamer.setName(name);
	}

	/**
	 * 
	 * @return zwraca plansze gracza
	 */
	public BSBoard getPlansza() {
		return plansza;
	}

	public Place getPlace(int x, int y) {
		return plansza.getPlace(x, y);
	}

	public int getID() {
		return playerID;
	}

	public abstract void resetStatus();

}