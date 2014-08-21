package pl.praktykiatrem.game.uniElements;

public abstract class PlayerStatus {

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
		if (this.playerID == other2.playerID)
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