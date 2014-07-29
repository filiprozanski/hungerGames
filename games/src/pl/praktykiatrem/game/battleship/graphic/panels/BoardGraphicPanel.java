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

public class BoardGraphicPanel extends JPanel {
    private final int SIZEH;
    private final int SIZEV;
    protected ShipButton[][] place;
    private IBoardPlaceObserver observer;

    public BoardGraphicPanel(int sizeH, int sizeV) {
	super(new GridLayout(sizeH + 1, sizeV + 1));
	SIZEH = sizeH;
	SIZEV = sizeV;
	place = new ShipButton[SIZEH][SIZEV];
	setSize(30 * SIZEH, 30 * SIZEV);
	initializeBoard();
    }

    public BoardGraphicPanel(IBoardPlaceObserver observer, int sizeH, int sizeV) {
	this(sizeH, sizeV);
	this.observer = observer;
    }

    public void setBoardSettingsObserver(IBoardPlaceObserver observer) {
	this.observer = observer;
    }

    public void changePlaceIcon(int x, int y, int type) {
	place[y][x].setPlaceIcon(type);
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
	setBackground(Color.LIGHT_GRAY);
	fillGameBoard();
	drawNumbers();
    }

    public void enableAllButtons() {
	for (int i = 0; i < SIZEH; i++) {
	    for (int j = 0; j < SIZEV; j++)
		place[j][i].setEnabled(true);
	}
    }

    public void enableButton(int x, int y) {
	place[y][x].setEnabled(true);
    }

    public void disableAllButtons() {
	for (int i = 0; i < SIZEH; i++) {
	    for (int j = 0; j < SIZEV; j++)
		place[j][i].setEnabled(false);
	}
    }

    public void disableButton(int x, int y) {
	place[y][x].setEnabled(false);
    }

    public void changeButtonCallNumber(int x, int y, int number) {
	place[y][x].changeCallNumber(number);
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
