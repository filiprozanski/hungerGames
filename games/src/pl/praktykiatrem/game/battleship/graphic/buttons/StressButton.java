/*
 * Plik stworzony dnia 30 lip 2014 przez filipr
 * 
 * Copyright ATREM S.A. ATREM 2014(C)
 */

package pl.praktykiatrem.game.battleship.graphic.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StressButton extends JButton {
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
