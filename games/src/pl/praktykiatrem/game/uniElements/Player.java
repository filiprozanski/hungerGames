package pl.praktykiatrem.game.uniElements;

import java.io.Serializable;

public class Player implements Serializable {
	private String name;
	private static int nextID = 100001;
	private int playerID;

	public Player() {
		name = "NoNameGiven";
		playerID = nextID++;
	}

	public Player(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return playerID;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Player))
			return false;
		Player otherPlayer = (Player) other;
		if (otherPlayer.name.equals(this.name)
				&& (otherPlayer.playerID == this.playerID))
			return true;
		else
			return false;
	}
}
