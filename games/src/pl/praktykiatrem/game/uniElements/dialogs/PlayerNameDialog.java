package pl.praktykiatrem.game.uniElements.dialogs;

import javax.swing.JPanel;

import pl.praktykiatrem.game.menu.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Filip R�a�ski
 */
public class PlayerNameDialog extends javax.swing.JDialog {

	/**
	 * Creates new form PlayerNameDialog
	 */
	public PlayerNameDialog(MainMenu parent, String name, boolean modal) {
		super(parent, name, modal);
		initComponents();
		setSize(330, 200);
		setAlwaysOnTop(true);
		setVisible(true);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		infoLabel = new javax.swing.JLabel();
		nameField = new javax.swing.JTextField();
		okButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		infoLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		infoLabel.setText("Podaj nazw� gracza:");
		getContentPane().add(infoLabel);
		infoLabel.setBounds(23, 11, 269, 28);

		nameField.setText("");
		nameField.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nameFieldActionPerformed(evt);
			}
		});
		getContentPane().add(nameField);
		nameField.setBounds(23, 57, 269, 30);

		okButton.setText("OK");
		okButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okButtonActionPerformed(evt);
			}
		});
		getContentPane().add(okButton);
		okButton.setBounds(122, 113, 63, 33);

		pack();
	}// </editor-fold>

	private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {
		okButtonActionPerformed(evt);
	}

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String name = nameField.getText();
		((MainMenu) super.getParent()).setPlayerName(name);
		dispose();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */

	// Variables declaration - do not modify
	private javax.swing.JLabel infoLabel;
	private javax.swing.JTextField nameField;
	private javax.swing.JButton okButton;
	private JPanel panel;
	// End of variables declaration
}
