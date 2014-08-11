package pl.praktykiatrem.game.tictactoe.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.battleship.files.TTIcons;
import pl.praktykiatrem.game.battleship.graphic.buttons.ShipButton;
import pl.praktykiatrem.game.battleship.graphic.listeners.PlaceChoiceListener;
import pl.praktykiatrem.game.battleship.graphic.observers.IBoardPlaceObserver;
import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class BoardPanel extends JPanel {
	private int SIZEH;
	private int SIZEV;
	private IBoardPlaceObserver observer;
	protected JButton[][] place;
	private int prefferedX;
	private int prefferedY;

	public BoardPanel(int sizeH, int sizeV, int buttonSize,
			IBoardPlaceObserver observer) {
		super(new GridLayout(sizeH, sizeV));
		this.observer = observer;
		initialize(sizeH, sizeV);
		initializeBoard();
		prefferedX = buttonSize * sizeH;
		prefferedY = buttonSize * sizeV;

	}

	private void initialize(int sizeH, int sizeV) {
		SIZEH = sizeH;
		SIZEV = sizeV;
		place = new ShipButton[SIZEH][SIZEV];
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(prefferedX, prefferedY);
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(300, 300);
	}

	@Override
	public Dimension getMaximumSize() {
		return new Dimension(400, 400);
	}

	private void initializeBoard() {
		setBackground(new Color(135, 206, 235));
		fillGameBoard();
	}

	private void fillGameBoard() {
		Insets buttonMargin = new Insets(0, 0, 0, 0);
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length; j++) {
				ShipButton b = new ShipButton();
				b.setMargin(buttonMargin);
				b.addActionListener(new PlaceChoiceListener(i, j, observer));
				ImageIcon icon = new ImageIcon(new BufferedImage(30, 30,
						BufferedImage.TYPE_INT_ARGB));
				b.setIcon(icon);
				b.setBackground(Color.WHITE);
				place[i][j] = b;
				add(place[i][j]);
			}
		}
	}

	public void changeIcon(int x, int y, Sign sign) {
		place[x][y].setIcon(TTIcons.getIcon(sign));
		place[x][y].setDisabledIcon(TTIcons.getIcon(sign));
	}

	public void lock() {
		for (int i = 0; i < SIZEH; i++)
			for (int j = 0; j < SIZEV; j++)
				place[i][j].setEnabled(false);
	}

	public void disableButton(int x, int y) {
		place[x][y].setEnabled(false);
	}
}
