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

	private ImageIcon ready_icon = new ImageIcon(
			ShipIcons.class.getResource("shoot.png"));
	private ImageIcon wait_icon = new ImageIcon(
			ShipIcons.class.getResource("wait.png"));

	public InfoPanel() {
		super(new GridLayout(3, 0));
		initialize();
	}

	private void initialize() {
		// JLabel status_label = new JLabel(ready_icon);
		status_label.setIcon(ready_icon);
		JButton change1 = new JButton("GREEN");
		JButton change2 = new JButton("RED");
		add(status_label);
		add(change1);
		add(change2);
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