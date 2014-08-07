/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.praktykiatrem.game.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForTwoPlayers;

/**
 *
 * @author Filip R�a�ski
 */
public class MainMenu extends JFrame implements IMenuCallObserver {

	/**
	 * Creates new form MainMenu
	 */
	public MainMenu() {
		initComponents();
		setTitle("hungerGames");
		setResizable(false);
		setLocationByPlatform(true);
		setSize(640, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		jLabel4 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		bsPCButton = new javax.swing.JButton();
		bsPlayerButton = new javax.swing.JButton();
		tttPlayerButton = new javax.swing.JButton();
		tttPCButton = new javax.swing.JButton();
		bsOnlineButton = new javax.swing.JButton();
		tttOnlineButton = new javax.swing.JButton();
		titleLabel = new javax.swing.JLabel();
		bsLabel = new javax.swing.JLabel();
		tttLabel = new javax.swing.JLabel();
		backgroundLabel = new javax.swing.JLabel();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		closeButton = new javax.swing.JMenuItem();
		settingsMenu = new javax.swing.JMenu();
		rulesChoice = new javax.swing.JMenu();
		customRulesField = new javax.swing.JRadioButtonMenuItem();
		originalRulesField = new javax.swing.JRadioButtonMenuItem();
		difficultyMenu = new javax.swing.JMenu();
		easyRadioButton = new javax.swing.JRadioButtonMenuItem();
		mediumRadioButton = new javax.swing.JRadioButtonMenuItem();
		hardRadioButton = new javax.swing.JRadioButtonMenuItem();
		pcOnlyButton = new javax.swing.JMenuItem();

		jLabel4.setIcon(new ImageIcon(ShipIcons.class
				.getResource("\\backgroundMenu.jpg"))); // NOI18N

		jLabel6.setIcon(new ImageIcon(ShipIcons.class
				.getResource("\\backgroundMenu.jpg"))); // NOI18N

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		bsPCButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		bsPCButton.setText("vs PC");
		bsPCButton.setBorder(null);
		bsPCButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bsPCButtonActionPerformed(evt);
			}
		});
		getContentPane().add(bsPCButton);
		bsPCButton.setBounds(118, 157, 136, 42);

		bsPlayerButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		bsPlayerButton.setText("vs Player");
		bsPlayerButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bsPlayerButtonActionPerformed(evt);
			}
		});
		getContentPane().add(bsPlayerButton);
		bsPlayerButton.setBounds(118, 217, 136, 42);

		tttPlayerButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		tttPlayerButton.setText("vs Player");
		tttPlayerButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tttPlayerButtonActionPerformed(evt);
			}
		});
		getContentPane().add(tttPlayerButton);
		tttPlayerButton.setBounds(357, 217, 136, 42);

		tttPCButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		tttPCButton.setText("vs PC");
		tttPCButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tttPCButtonActionPerformed(evt);
			}
		});
		getContentPane().add(tttPCButton);
		tttPCButton.setBounds(357, 157, 136, 42);

		bsOnlineButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		bsOnlineButton.setText("Online");
		bsOnlineButton.setEnabled(false);
		bsOnlineButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bsOnlineButtonActionPerformed(evt);
			}
		});
		getContentPane().add(bsOnlineButton);
		bsOnlineButton.setBounds(118, 277, 136, 42);

		tttOnlineButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		tttOnlineButton.setText("Online");
		tttOnlineButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				tttOnlineButtonActionPerformed(evt);
			}
		});
		getContentPane().add(tttOnlineButton);
		tttOnlineButton.setBounds(357, 277, 136, 42);

		titleLabel.setFont(new java.awt.Font("SimHei", 1, 48)); // NOI18N
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titleLabel.setText("hungerGames");
		getContentPane().add(titleLabel);
		titleLabel.setBounds(120, 30, 375, 56);

		bsLabel.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		bsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		bsLabel.setText("BattleShips");
		getContentPane().add(bsLabel);
		bsLabel.setBounds(118, 113, 136, 26);

		tttLabel.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		tttLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		tttLabel.setText("TicTacToe");
		getContentPane().add(tttLabel);
		tttLabel.setBounds(360, 120, 136, 26);

		backgroundLabel.setIcon(new ImageIcon(ShipIcons.class
				.getResource("\\backgroundMenu.jpg"))); // NOI18N
		getContentPane().add(backgroundLabel);
		backgroundLabel.setBounds(0, 0, 640, 400);

		fileMenu.setText("File");

		closeButton.setText("Quit");
		closeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeButtonActionPerformed(evt);
			}
		});
		fileMenu.add(closeButton);

		menuBar.add(fileMenu);

		settingsMenu.setText("Settings");

		rulesChoice.setText("Zasady");

		customRulesField.setSelected(true);
		customRulesField.setText("CustomRules");
		customRulesField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				customRulesFieldActionPerformed(evt);
			}
		});
		rulesChoice.add(customRulesField);

		originalRulesField.setText("OriginalRules");
		originalRulesField
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						originalRulesFieldActionPerformed(evt);
					}
				});
		rulesChoice.add(originalRulesField);

		settingsMenu.add(rulesChoice);

		difficultyMenu.setText("Difficulty");

		easyRadioButton.setText("Easy");
		easyRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				easyRadioButtonActionPerformed(evt);
			}
		});
		difficultyMenu.add(easyRadioButton);

		mediumRadioButton.setText("Medium");
		mediumRadioButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						mediumRadioButtonActionPerformed(evt);
					}
				});
		difficultyMenu.add(mediumRadioButton);

		hardRadioButton.setSelected(true);
		hardRadioButton.setText("Hard");
		hardRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				hardRadioButtonActionPerformed(evt);
			}
		});
		difficultyMenu.add(hardRadioButton);

		settingsMenu.add(difficultyMenu);

		pcOnlyButton.setText("PC vs PC");
		pcOnlyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				pcOnlyButtonActionPerformed(evt);
			}
		});
		settingsMenu.add(pcOnlyButton);

		menuBar.add(settingsMenu);

		setJMenuBar(menuBar);

		pack();
	}// </editor-fold>

	private void bsPCButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// JDialog dialog = new NameDialog(this, "Gracz 1", true);
		new StartGraphicForOnePlayer("Wiktor", "Komputer", this,
				getRulesChoice(), getDifficultyChoice());
		setVisible(false);
	}

	private void bsPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// JDialog dialog = new NameDialog(this, "Gracz 1", true);
		// dialog = new NameDialog(this, "Gracz 2", true);
		new StartGraphicForTwoPlayers("Filip", "Wiktor", this, getRulesChoice());
		setVisible(false);
	}

	private void bsOnlineButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

	}

	private void tttPCButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void tttPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void tttOnlineButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void customRulesFieldActionPerformed(java.awt.event.ActionEvent evt) {
		originalRulesField.setSelected(false);
		originalRulesField.setSelectedIcon(null);
	}

	private void originalRulesFieldActionPerformed(
			java.awt.event.ActionEvent evt) {
		customRulesField.setSelected(false);
		customRulesField.setSelectedIcon(null);
	}

	private void easyRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		mediumRadioButton.setSelected(false);
		mediumRadioButton.setSelectedIcon(null);
		hardRadioButton.setSelected(false);
		hardRadioButton.setSelectedIcon(null);
	}

	private void mediumRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		easyRadioButton.setSelected(false);
		easyRadioButton.setSelectedIcon(null);
		hardRadioButton.setSelected(false);
		hardRadioButton.setSelectedIcon(null);
	}

	private void hardRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		mediumRadioButton.setSelected(false);
		mediumRadioButton.setSelectedIcon(null);
		easyRadioButton.setSelected(false);
		easyRadioButton.setSelectedIcon(null);
	}

	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void pcOnlyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	public void setPlayerName(String name) {
		if (name1 == null)
			name1 = name;
		else if (name2 == null)
			name2 = name;
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel backgroundLabel;
	private javax.swing.JLabel bsLabel;
	private javax.swing.JButton bsOnlineButton;
	private javax.swing.JButton bsPCButton;
	private javax.swing.JButton bsPlayerButton;
	private javax.swing.JMenuItem closeButton;
	private javax.swing.JRadioButtonMenuItem customRulesField;
	private javax.swing.JMenu difficultyMenu;
	private javax.swing.JRadioButtonMenuItem easyRadioButton;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JRadioButtonMenuItem hardRadioButton;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JRadioButtonMenuItem mediumRadioButton;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JRadioButtonMenuItem originalRulesField;
	private javax.swing.JMenuItem pcOnlyButton;
	private javax.swing.JMenu rulesChoice;
	private javax.swing.JMenu settingsMenu;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JLabel tttLabel;
	private javax.swing.JButton tttOnlineButton;
	private javax.swing.JButton tttPCButton;
	private javax.swing.JButton tttPlayerButton;
	private String name1;
	private String name2;

	// End of variables declaration
	@Override
	public void callMenu() {
		setVisible(true);
	}

	private int getRulesChoice() {
		if (customRulesField.isSelected())
			return 1;
		else if (originalRulesField.isSelected())
			return 2;
		else
			return 1;
	}

	private int getDifficultyChoice() {
		if (easyRadioButton.isSelected())
			return 1;
		else if (mediumRadioButton.isSelected())
			return 2;
		else if (hardRadioButton.isSelected())
			return 3;
		else
			return 2;
	}

	public void enableButton() {
		bsOnlineButton.setEnabled(true);
	}
}
