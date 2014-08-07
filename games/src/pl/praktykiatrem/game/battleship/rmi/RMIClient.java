package pl.praktykiatrem.game.battleship.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			try {
				Registry r = LocateRegistry.getRegistry("localhost", 9875);
				IRMIServer s = (IRMIServer) r.lookup("RMIServer");
				System.out.println("Jestem klientem");
				System.out.println("wywo³uje metode");
				s.zshowConnection();
				break;
			} catch (Exception e) {
				// System.out.println("Nie mog³em po³¹czyæ");
				Thread.sleep(1000);
			}
		}

	}
}
