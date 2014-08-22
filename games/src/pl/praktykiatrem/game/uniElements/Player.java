package pl.praktykiatrem.game.uniElements;

import java.io.Serializable;

public class Player implements Serializable {
	private String name;

	public Player() {
		name = "NoNameGiven";
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
}
