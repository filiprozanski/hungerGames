package pl.praktykiatrem.game.battleship.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;
import pl.praktykiatrem.game.battleship.graphic.listeners.PlaceChoiceListener;

public class BoardGraphicSettingPanel extends JPanel {
	private static final int SIZEH = 10;
	private static final int SIZEV = 10;
	protected ShipButton[][] place = new ShipButton[SIZEH][SIZEV];
	private IBoardSettingsObserver observer;

	public BoardGraphicSettingPanel(IBoardSettingsObserver observer) {
		super(new GridLayout(SIZEH + 1, SIZEV + 1));
		place = new ShipButton[SIZEH][SIZEV];
		setSize(330, 330);
		this.observer = observer;
		initializeBoard();
	}

	public void setBoardSettingsObserver(IBoardSettingsObserver observer) {
		this.observer = observer;
	}

	public void changePlaceIcon(int x, int y, int type) {
		place[y][x].setPlaceIcon(type);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(330, 330);
	}

	public Dimension getMinimumDimension() {
		return new Dimension(330, 330);
	}

	public Dimension getMaximDimension() {
		return new Dimension(330, 330);
	}

	private void initializeBoard() {
		setBackground(Color.LIGHT_GRAY);
		fillGameBoard();
		drawNumbers();
	}

	public void enableButton() {

	}

	public void disableButton() {

	}

	private void fillGameBoard() {
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length; j++) {
				ShipButton b = new ShipButton();

				b.addActionListener(new PlaceChoiceListener(i, j, observer));
				b.setMargin(buttonMargin);
				ImageIcon icon = new ImageIcon(new BufferedImage(30, 30,
						BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				b.setBackground(Color.WHITE);
				// b.setEnabled(false);
				place[j][i] = b;
			}
		}
	}

	private void drawNumbers() {
		add(new JLabel(""));
		// pierwszy rz±d cyfr
		for (int i = 0; i < SIZEH; i++)
			add(new JLabel("" + i, SwingConstants.CENTER));

		// pierwsza kolumna to SwingConstant cyfry
		for (int i = 0; i < SIZEH; i++) {
			for (int j = 0; j < SIZEV; j++) {
				switch (j) {
				case 0:
					add(new JLabel("" + i, SwingConstants.CENTER));
				default:
					add(place[j][i]);
				}
			}
		}
	}
}
