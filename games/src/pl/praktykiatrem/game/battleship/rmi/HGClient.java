package pl.praktykiatrem.game.battleship.rmi;

import java.io.Serializable;

public class HGClient implements Serializable {
	private static final long serialVersionUID = 2L;
	private String IP;
	private int portNumber;
	private int clientID;
	private static int nextID = 1;

	public HGClient() {
		clientID = nextID;
		nextID++;
	}

	public boolean equals(Object other) {
		if (other instanceof HGClient)
			if (((HGClient) other).clientID == this.clientID)
				return true;
			else
				return false;
		else
			return false;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public int getPortNumber() {
		return portNumber;
	}
}
