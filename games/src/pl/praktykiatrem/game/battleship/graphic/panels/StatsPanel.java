/*
 * Plik stworzony dnia 30 lip 2014 przez filipr
 *
 * Copyright ATREM S.A.
 * ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * Klasa <code>StatsPanel</code>
 *
 * Przechowuje statystyke strza³ów gracza
 *
 * @author filipr
 * @version 30 lip 2014 15:02:15
 *
 */
public class StatsPanel extends JPanel {
	/**
	 * Pole <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -7850686332716065416L;
	/**
	 * etykieta reprezentuj±ca liczbê statków gracza
	 */
	private JLabel lblPlayerShipsNumber;
	/**
	 * etykieta reprezentuj±ca liczbê statków przeciwnika
	 */
	private JLabel lblEnemyShipsNumber;
	/**
	 * etykieta reprezentuj±ca skuteczno¶æ strza³ów gracza
	 */
	private JLabel lblAccuracyNumber;

	/**
	 * 
	 * Tworzy nowy obiekt klasy <code>StatsPanel</code>
	 *
	 */
	public StatsPanel() {
		setBackground(new Color(135, 206, 235));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 250, 80 };
		gridBagLayout.rowHeights = new int[] { 35, 35, 35 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);

		JLabel lblPlayerShips = new JLabel("Liczba twoich stak\u00F3w:");
		lblPlayerShips.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPlayerShips = new GridBagConstraints();
		gbc_lblPlayerShips.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerShips.gridx = 0;
		gbc_lblPlayerShips.gridy = 0;
		add(lblPlayerShips, gbc_lblPlayerShips);

		lblPlayerShipsNumber = new JLabel("-");
		lblPlayerShipsNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPlayerShipsNumber = new GridBagConstraints();
		gbc_lblPlayerShipsNumber.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerShipsNumber.gridx = 1;
		gbc_lblPlayerShipsNumber.gridy = 0;
		add(lblPlayerShipsNumber, gbc_lblPlayerShipsNumber);

		JLabel lblEnemyShips = new JLabel("Liczba statk\u00F3w przeciwnika:");
		lblEnemyShips.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblEnemyShips = new GridBagConstraints();
		gbc_lblEnemyShips.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnemyShips.gridx = 0;
		gbc_lblEnemyShips.gridy = 1;
		add(lblEnemyShips, gbc_lblEnemyShips);

		lblEnemyShipsNumber = new JLabel("-");
		lblEnemyShipsNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblEnemyShipsNumber = new GridBagConstraints();
		gbc_lblEnemyShipsNumber.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnemyShipsNumber.gridx = 1;
		gbc_lblEnemyShipsNumber.gridy = 1;
		add(lblEnemyShipsNumber, gbc_lblEnemyShipsNumber);

		JLabel lblAccuracy = new JLabel(
				"Skuteczno\u015B\u0107 strza\u0142\u00F3w:");
		lblAccuracy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblAccuracy = new GridBagConstraints();
		gbc_lblAccuracy.insets = new Insets(0, 0, 0, 5);
		gbc_lblAccuracy.gridx = 0;
		gbc_lblAccuracy.gridy = 2;
		add(lblAccuracy, gbc_lblAccuracy);

		lblAccuracyNumber = new JLabel("-");
		lblAccuracyNumber.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblAccuracyNumber = new GridBagConstraints();
		gbc_lblAccuracyNumber.gridx = 1;
		gbc_lblAccuracyNumber.gridy = 2;
		add(lblAccuracyNumber, gbc_lblAccuracyNumber);
	}

	/**
	 * 
	 * Metoda <code>setLblPlayerShipsNumber</code>
	 * 
	 * ustawia tekst w lblPlayerShipsNumber na podan± warto¶æ
	 *
	 * @param value
	 */
	public void setLblPlayerShipsNumber(String value) {
		lblPlayerShipsNumber.setText(value);
	}

	/**
	 * 
	 * Metoda <code>setLblEnemyShipsNumber</code>
	 * 
	 * ustawia tekst w lblEnemyShipsNumber na podan± warto¶æ
	 *
	 * @param value
	 */
	public void setLblEnemyShipsNumber(String value) {
		lblEnemyShipsNumber.setText(value);
	}

	/**
	 * 
	 * Metoda <code>setLblAccuracyNumber</code>
	 *
	 * ustawia tekst w lblAccuracyNumber na podan± warto¶æ
	 *
	 * @param value
	 */
	public void setLblAccuracyNumber(String value) {
		lblAccuracyNumber.setText(value);
	}

}
