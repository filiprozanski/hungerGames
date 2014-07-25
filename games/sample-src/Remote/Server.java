package Remote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) {
		try {
			ShipService obj1 = new ShipService();
			Registry r = LocateRegistry.createRegistry(9875);
			r.bind("ShipService", obj1);
			System.out.println("Jestem serwerem");
			System.out.println("Wait...");
			Thread.sleep(60000);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}