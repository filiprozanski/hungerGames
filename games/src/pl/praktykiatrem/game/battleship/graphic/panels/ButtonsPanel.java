package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.IStageObserver;
import pl.praktykiatrem.game.battleship.graphic.listeners.UserStageListener;

public class ButtonsPanel extends JPanel {
    private JButton ready;

    public ButtonsPanel(IStageObserver observer) {
	super(new GridLayout(1, 0));
	initialize(observer);
    }

    private void initialize(IStageObserver observer) {
	ready = new JButton("Gotowy!");
	ready.addActionListener(new UserStageListener(observer));
	add(ready, BorderLayout.CENTER);
    }

    public Dimension gerPrefferedSize() {
	return new Dimension(150, 150);
    }

    public Dimension gerMinumumSize() {
	return new Dimension(150, 150);
    }

    public void diableButton() {
	ready.setEnabled(false);
    }
}
