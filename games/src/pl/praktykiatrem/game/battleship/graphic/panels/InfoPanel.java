package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.ShipIcons;

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

		JButton change1 = new JButton("GREEN");
		JButton change2 = new JButton("YELLOW");
		JButton change3 = new JButton("RED");
		JPanel button_panel = new JPanel(new GridLayout(0, 3));
		button_panel.add(change1);
		button_panel.add(change2);
		button_panel.add(change3);
		add(stats);
		add(status_label);
		add(button_panel);

		change1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeStatus(true);
			}
		});
		change2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changeStatus(false);
			}
		});

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