package pl.praktykiatrem.game.menu;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import pl.praktykiatrem.game.battleship.rmi.HGClient;
import pl.praktykiatrem.game.battleship.rmi.IRMIServer;

public class menuStart {
	private static MainMenu f;

	public static void main(String args[]) throws InterruptedException {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		HGClient client = new HGClient();
		Registry r;

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} // </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				f = new MainMenu();
			}
		});

		// while (true) {
		try {
			r = LocateRegistry.getRegistry("localhost", 9875);
			IRMIServer s = (IRMIServer) r.lookup("RMIServer");
			s.showConnection();
			s.logInClient(client);
			f.enableButton();
			// break;
		} catch (Exception e) {
			System.out.println("Nie mog³em po³¹czyæ");
			Thread.sleep(10000);
		}
		// }
	}
}
