package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.graphic.listeners.UserStageListener;
import pl.praktykiatrem.game.battleship.graphic.observers.IStageObserver;

public class ButtonsPanel extends JPanel {
	private JButton ready;
	private JButton reset;
	private JButton autoSet;

	public ButtonsPanel(IStageObserver observer) {
		super(new GridLayout(3, 0));
		initialize(observer);
	}

	private void initialize(IStageObserver observer) {
		ready = new JButton("Gotowy!");
		reset = new JButton("Resetuj planszê!");
		autoSet = new JButton("Ustaw statki automatycznie!");
		ready.addActionListener(new UserStageListener(observer));
		add(ready, BorderLayout.CENTER);
		add(autoSet, BorderLayout.CENTER);
		add(reset, BorderLayout.CENTER);
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
