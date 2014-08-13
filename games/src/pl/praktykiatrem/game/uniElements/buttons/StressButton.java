/*
 * Plik stworzony dnia 30 lip 2014 przez filipr
 * 
 * Copyright ATREM S.A. ATREM 2014(C)
 */

package pl.praktykiatrem.game.uniElements.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Klasa opisuj¹ca tzw. StressButton, czyli guzik, na którym mo¿na siê wy¿yæ, w
 * razie ulegania presji, podczas gry w statki
 * 
 * @author Filip Ró¿añski
 *
 */
public class StressButton extends JButton {
	private static final long serialVersionUID = -1526329558381201300L;
	private int hitNumber = 0;

	public StressButton() {
		setBackground(new Color(135, 206, 235));
		setFont(new Font("Tahoma", Font.PLAIN, 17));
		setText("StressButton");
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				hitNumber++;
				String text = Integer.toString(hitNumber);
				changeDescription(text);
			}
		});
	}

	private void changeDescription(String text) {
		this.setText(text);
	}
}
