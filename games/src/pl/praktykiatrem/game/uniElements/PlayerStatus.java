package pl.praktykiatrem.game.uniElements;

import java.io.Serializable;

public abstract class PlayerStatus implements Serializable {

	protected Player gamer;

	public PlayerStatus() {
		gamer = new Player();
	}

	/**
	 * Porównuje graczy wykorzystuj¹c do tego nadane im ID
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof PlayerStatus))
			return false;

		PlayerStatus other2 = (PlayerStatus) other;
		if (this.gamer.equals(other2.gamer))
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

	public Player getPlayer() {
		return gamer;
	}

	public abstract void resetStatus();

}