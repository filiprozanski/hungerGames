package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

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

public class DensityPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8905654954077788715L;
	private int SIZEH;
	private int SIZEV;
	protected ShipButton[][] place;

	public DensityPanel(int sizeH, int sizeV) {
		super(new GridLayout(sizeH + 1, sizeV + 1));
		this.SIZEH = sizeH;
		this.SIZEV = sizeV;
		place = new ShipButton[SIZEH][SIZEV];
		setSize(30 * SIZEH, 30 * SIZEV);
		initializeBoard();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(30 * SIZEH, 30 * SIZEV);
	}

	public Dimension getMinimumDimension() {
		return new Dimension(30 * SIZEH, 30 * SIZEV);
	}

	public Dimension getMaximDimension() {
		return new Dimension(30 * SIZEH, 30 * SIZEV);
	}

	private void initializeBoard() {
		setBackground(new Color(135, 206, 235));
		fillGameBoard();
		drawNumbers();
	}

	private void fillGameBoard() {
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length; j++) {
				ShipButton b = new ShipButton();

				b.setMargin(buttonMargin);
				ImageIcon icon = new ImageIcon(new BufferedImage(30, 30,
						BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				b.setBackground(Color.WHITE);
				place[j][i] = b;
			}
		}
	}

	public void changeButtonColor(int x, int y, Color color) {
		place[y][x].setBackground(color);
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
