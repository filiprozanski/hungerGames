package pl.praktykiatrem.game.tictactoe.graphic.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import pl.praktykiatrem.game.tictactoe.files.Icons;
import pl.praktykiatrem.game.tictactoe.graphic.listeners.IBoardObserver;
import pl.praktykiatrem.game.tictactoe.graphic.listeners.PlaceChoiceListener;
import pl.praktykiatrem.game.tictactoe.rules.Sign;
import pl.praktykiatrem.game.uniElements.buttons.CoordsButton;

public class BoardPanel extends JPanel implements IBoardObserver {
	private int SIZEH;
	private int SIZEV;
	private GamePanel observer;
	protected JButton[][] place;
	private int prefferedX;
	private int prefferedY;

	public BoardPanel(int sizeH, int sizeV, int buttonSize, GamePanel observer) {
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
		place = new CoordsButton[SIZEH][SIZEV];
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
				CoordsButton b = new CoordsButton();
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
		place[x][y].setIcon(Icons.getIcon(sign));
		place[x][y].setDisabledIcon(Icons.getIcon(sign));
	}

	public void lock() {
		for (int i = 0; i < SIZEH; i++)
			for (int j = 0; j < SIZEV; j++)
				place[i][j].setEnabled(false);
	}

	public void disableButton(int x, int y) {
		place[x][y].setEnabled(false);
	}

	@Override
	public void clicked(int x, int y) {
		observer.clicked(x, y);
	}
}
