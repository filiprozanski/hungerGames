package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;
import pl.praktykiatrem.game.battleship.graphic.buttons.StressButton;

public class InfoPanel extends JPanel {
	private JLabel status_label = new JLabel();
	private StatsPanel stats = new StatsPanel();
	private ImageIcon ready_icon = new ImageIcon(
			ShipIcons.class.getResource("shoot.png"));
	private ImageIcon wait_icon = new ImageIcon(
			ShipIcons.class.getResource("wait.png"));

	public InfoPanel() {
		super(new GridLayout(3, 0));
		initialize();
	}

	private void initialize() {

		status_label.setIcon(ready_icon);

		JButton giveUp = new JButton("Poddaj siê");
		JPanel button_panel = new JPanel(new GridLayout(0, 2));
		button_panel.add(new StressButton());
		button_panel.add(giveUp);

		add(stats);
		add(status_label);
		add(button_panel);

	}

	public Dimension gerPrefferedSize() {
		return new Dimension(150, 150);
	}

	public Dimension gerMinumumSize() {
		return new Dimension(150, 150);
	}

	public void changeStatus(boolean ready) {
		if (ready == true) {

			System.out.print("strzelaj");
			status_label.setIcon(ready_icon);
		} else {
			status_label.setIcon(wait_icon);
			System.out.print("czekaj");
		}
	}
}