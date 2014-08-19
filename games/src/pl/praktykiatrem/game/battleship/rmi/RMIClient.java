package pl.praktykiatrem.game.battleship.rmi;

import java.io.Serializable;

public class RMIClient implements Serializable {
	private static final long serialVersionUID = 2L;
	private String IP;
	private int portNumber;
	private int clientID;
	private static int nextID = 1;

	public RMIClient() {
		clientID = nextID;
		nextID++;
	}

	public boolean equals(Object other) {
		if (other instanceof RMIClient)
			if (((RMIClient) other).clientID == this.clientID)
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
