/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.praktykiatrem.game.menu;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForNoPlayer;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForOnePlayer;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicForTwoPlayers;
import pl.praktykiatrem.game.battleship.graphic.StartGraphicOnline;
import pl.praktykiatrem.game.battleship.rmi.ServerApp;
import pl.praktykiatrem.game.tictactoe.StartTicTacToeForOnePlayer;
import pl.praktykiatrem.game.tictactoe.StartTicTacToeForTwoPlayers;
import pl.praktykiatrem.game.uniElements.dialogs.PlayerNameDialog;
import pl.praktykiatrem.game.uniElements.enums.Difficulty;
import pl.praktykiatrem.game.uniElements.enums.RulesType;

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
		tttRulesChoice = new javax.swing.JMenu();
		shipRulesChoice = new javax.swing.JMenu();
		bsCustomRulesField = new javax.swing.JRadioButtonMenuItem();
		bsOriginalRulesField = new javax.swing.JRadioButtonMenuItem();
		tttCustomRulesField = new javax.swing.JRadioButtonMenuItem();
		tttOriginalRulesField = new javax.swing.JRadioButtonMenuItem();
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
		bsOnlineButton.setEnabled(true);
		bsOnlineButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					bsOnlineButtonActionPerformed(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		getContentPane().add(bsOnlineButton);
		bsOnlineButton.setBounds(118, 277, 136, 42);

		tttOnlineButton.setFont(new java.awt.Font("SimHei", 1, 18)); // NOI18N
		tttOnlineButton.setText("Online");
		tttOnlineButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					tttOnlineButtonActionPerformed(evt);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AlreadyBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				closeButtonActionPerformed(evt);
			}
		});
		fileMenu.add(closeButton);

		menuBar.add(fileMenu);

		settingsMenu.setText("Settings");

		shipRulesChoice.setText("Battleships game rules");

		bsCustomRulesField.setSelected(true);
		bsCustomRulesField.setText("CustomRules");
		bsCustomRulesField
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						customRulesFieldActionPerformed(evt);
					}
				});
		shipRulesChoice.add(bsCustomRulesField);

		bsOriginalRulesField.setText("OriginalRules");
		bsOriginalRulesField
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						originalRulesFieldActionPerformed(evt);
					}
				});
		shipRulesChoice.add(bsOriginalRulesField);

		settingsMenu.add(shipRulesChoice);

		tttRulesChoice.setText("TicTacToe game rules");

		tttCustomRulesField.setSelected(true);
		tttCustomRulesField.setText("CustomRules");
		tttCustomRulesField
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						tttCustomRulesFieldActionPerformed(evt);
					}
				});
		tttRulesChoice.add(tttCustomRulesField);

		tttOriginalRulesField.setText("OriginalRules");
		tttOriginalRulesField
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						tttOriginalRulesFieldActionPerformed(evt);
					}
				});
		tttRulesChoice.add(tttOriginalRulesField);

		settingsMenu.add(tttRulesChoice);

		difficultyMenu.setText("Difficulty");

		easyRadioButton.setText("Easy");
		easyRadioButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				easyRadioButtonActionPerformed(evt);
			}
		});
		difficultyMenu.add(easyRadioButton);

		mediumRadioButton.setSelected(true);
		mediumRadioButton.setText("Medium");
		mediumRadioButton
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						mediumRadioButtonActionPerformed(evt);
					}
				});
		difficultyMenu.add(mediumRadioButton);

		hardRadioButton.setSelected(false);
		hardRadioButton.setText("Hard");
		hardRadioButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				hardRadioButtonActionPerformed(evt);
			}
		});
		difficultyMenu.add(hardRadioButton);

		settingsMenu.add(difficultyMenu);

		pcOnlyButton.setText("PC vs PC");
		pcOnlyButton.addActionListener(new java.awt.event.ActionListener() {
			@Override
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
		JDialog dialog = new PlayerNameDialog(this, "Gracz 1", true);
		new StartGraphicForOnePlayer(name1, "Komputer", this,
				getShipsRulesChoice(), getDifficultyChoice());
		setVisible(false);
	}

	private void bsPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		JDialog dialog = new PlayerNameDialog(this, "Gracz 1", true);
		dialog = new PlayerNameDialog(this, "Gracz 2", true);
		new StartGraphicForTwoPlayers(name1, name2, this, getShipsRulesChoice());
		setVisible(false);
	}

	private void bsOnlineButtonActionPerformed(java.awt.event.ActionEvent evt)
			throws InterruptedException {
		JDialog dialog = new PlayerNameDialog(this, "Gracz 1", true);
		try {
			new StartGraphicOnline(name1, this);
		} catch (RemoteException e) {
			System.out.println("StartGraphic");
			e.printStackTrace();
		}
		dispose();
	}

	private void tttPCButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new StartTicTacToeForOnePlayer("Filip", this, getTTTRulesChoice(),
				getDifficultyChoice());
		setVisible(false);
	}

	private void tttPlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new StartTicTacToeForTwoPlayers("Filip", "Wiktor", this,
				getTTTRulesChoice());
		setVisible(false);
	}

	private void tttOnlineButtonActionPerformed(java.awt.event.ActionEvent evt)
			throws RemoteException, AlreadyBoundException {
		new ServerApp(getShipsRulesChoice());
	}

	private void customRulesFieldActionPerformed(java.awt.event.ActionEvent evt) {
		bsOriginalRulesField.setSelected(false);
		bsOriginalRulesField.setSelectedIcon(null);
	}

	private void tttCustomRulesFieldActionPerformed(
			java.awt.event.ActionEvent evt) {
		tttOriginalRulesField.setSelected(false);
		tttOriginalRulesField.setSelectedIcon(null);
	}

	private void originalRulesFieldActionPerformed(
			java.awt.event.ActionEvent evt) {
		bsCustomRulesField.setSelected(false);
		bsCustomRulesField.setSelectedIcon(null);
	}

	private void tttOriginalRulesFieldActionPerformed(
			java.awt.event.ActionEvent evt) {
		tttCustomRulesField.setSelected(false);
		tttCustomRulesField.setSelectedIcon(null);
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
		new StartGraphicForNoPlayer("COM1", "COM2", this,
				getShipsRulesChoice(), getDifficultyChoice());
		setVisible(false);
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
	private javax.swing.JRadioButtonMenuItem bsCustomRulesField;
	private javax.swing.JMenu difficultyMenu;
	private javax.swing.JRadioButtonMenuItem easyRadioButton;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JRadioButtonMenuItem hardRadioButton;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JRadioButtonMenuItem mediumRadioButton;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JRadioButtonMenuItem bsOriginalRulesField;
	private javax.swing.JMenuItem pcOnlyButton;
	private javax.swing.JMenu shipRulesChoice;
	private javax.swing.JMenu tttRulesChoice;
	private javax.swing.JMenu settingsMenu;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JRadioButtonMenuItem tttCustomRulesField;
	private javax.swing.JLabel tttLabel;
	private javax.swing.JButton tttOnlineButton;
	private javax.swing.JRadioButtonMenuItem tttOriginalRulesField;
	private javax.swing.JButton tttPCButton;
	private javax.swing.JButton tttPlayerButton;
	private String name1;
	private String name2;
	private Registry r;

	// End of variables declaration
	@Override
	public void callMenu() {
		setVisible(true);
	}

	private RulesType getShipsRulesChoice() {
		if (bsCustomRulesField.isSelected())
			return RulesType.CUSTOMRULES;
		else if (bsOriginalRulesField.isSelected())
			return RulesType.ORIGINALRULES;
		else
			return RulesType.CUSTOMRULES;
	}

	private RulesType getTTTRulesChoice() {
		if (bsCustomRulesField.isSelected())
			return RulesType.CUSTOMRULES;
		else if (bsOriginalRulesField.isSelected())
			return RulesType.ORIGINALRULES;
		else
			return RulesType.CUSTOMRULES;
	}

	private Difficulty getDifficultyChoice() {
		if (easyRadioButton.isSelected())
			return Difficulty.EASY;
		else if (mediumRadioButton.isSelected())
			return Difficulty.MEDIUM;
		else if (hardRadioButton.isSelected())
			return Difficulty.HARD;
		else
			return Difficulty.MEDIUM;
	}
}
