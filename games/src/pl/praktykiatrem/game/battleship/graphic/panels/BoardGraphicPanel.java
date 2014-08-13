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
import pl.praktykiatrem.game.battleship.graphic.listeners.PlaceChoiceDropListener;
import pl.praktykiatrem.game.battleship.graphic.listeners.PlaceChoiceListener;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;

/**
 * Panel zawieraj¹cy planszê u¿ywan¹ do ustawiania statków, oraz do strzelania
 * 
 * @author Filip Ró¿añski
 *
 */
/**
 * @author Filip Ró¿añski
 *
 */
public class BoardGraphicPanel extends JPanel {
	private static final long serialVersionUID = 3299237964024134126L;
	private int SIZEH;
	private int SIZEV;
	protected ShipButton[][] place;
	private IBoardPlaceObserver observer;

	public BoardGraphicPanel(int sizeV, int sizeH, boolean dragable) {
		super(new GridLayout(sizeV + 1, sizeH + 1));
		initialize(sizeV, sizeH);
		initializeBoard(dragable);
	}

	public BoardGraphicPanel(IBoardPlaceObserver observer, int sizeV,
			int sizeH, boolean dragable) {
		super(new GridLayout(sizeV + 1, sizeH + 1));
		this.observer = observer;
		initialize(sizeV, sizeH);
		initializeBoard(dragable);
	}

	private void initialize(int sizeV, int sizeH) {

		SIZEH = sizeH;
		SIZEV = sizeV;
		place = new ShipButton[SIZEH][SIZEV];
		setSize(30 * SIZEH, 30 * SIZEV);
	}

	public void setBoardSettingsObserver(IBoardPlaceObserver observer) {
		this.observer = observer;
	}

	public void changePlaceIcon(int x, int y, int type) {
		place[y][x].setPlaceIcon(type);
	}

	public void changeStateAllButtons(boolean enable) {
		for (int i = 0; i < SIZEV; i++) {
			for (int j = 0; j < SIZEH; j++)
				place[j][i].setEnabled(enable);
		}
	}

	public void disableButton(int x, int y) {
		place[y][x].setEnabled(false);
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

	private void initializeBoard(boolean dragable) {
		setBackground(new Color(135, 206, 235));
		fillGameBoard(dragable);
		drawNumbers();
	}

	private void fillGameBoard(boolean dragable) {
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int j = 0; j < place.length; j++) {
			for (int i = 0; i < place[j].length; i++) {
				ShipButton b = new ShipButton(i, j, dragable, observer);

				if (observer != null) {
					b.addMouseListener(new PlaceChoiceListener(i, j, observer));
					b.addActionListener(new PlaceChoiceListener(i, j, observer));
				}
				b.setMargin(buttonMargin);
				ImageIcon icon = new ImageIcon(new BufferedImage(30, 30,
						BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				b.setBackground(Color.WHITE);
				new PlaceChoiceDropListener(b, i, j, observer);
				place[j][i] = b;
			}
		}
	}

	private void drawNumbers() {
		add(new JLabel(""));
		// pierwszy wiersz cyfr
		for (int i = 0; i < SIZEH; i++)
			add(new JLabel("" + i, SwingConstants.CENTER));

		// pierwsza kolumna to SwingConstant cyfr
		for (int i = 0; i < SIZEV; i++)
			for (int j = 0; j < SIZEH + 1; j++)
				if (j == 0)
					add(new JLabel("" + i, SwingConstants.CENTER));
				else
					add(place[j - 1][i]);
	}

	public void changePlaceStateIcon(int x, int y, int type) {
		place[y][x].setPlaceStateIcon(type);
	}
}
