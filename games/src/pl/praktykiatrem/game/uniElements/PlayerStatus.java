package pl.praktykiatrem.game.uniElements;

import java.io.Serializable;

public abstract class PlayerStatus implements Serializable {

	protected Player gamer;
	protected int playerID;
	private static int counter = 100001;

	public PlayerStatus() {
		gamer = new Player();
		playerID = counter++;
	}

	/**
	 * Porównuje graczy wykorzystuj¹c do tego nadane im ID
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof PlayerStatus))
			return false;

		PlayerStatus other2 = (PlayerStatus) other;
		if (this.getName().equals(other2.getName()))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * @return imiê gracza
	 */
	public String getName() {
		return gamer.getName();
	}

	/**
	 * Ustawia imiê gracza
	 * 
	 * @param name
	 */
	public void setName(String name) {
		gamer.setName(name);
	}

	public int getID() {
		return playerID;
	}

	public abstract void resetStatus();

}