package pl.praktykiatrem.game.battleship.density;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DensityView extends JFrame {

	private static final long serialVersionUID = 4884302083293184798L;
	private JPanel contentPane;
	private DensityPanel densityPanel;
	private int sizeH;
	private int sizeV;
	private double temp;
	private int temp2;
	private int temp3;
	private double red;
	private double green;
	private double blue;

	public DensityView(int sizeV, int sizeH) {
		this.sizeH = sizeH;
		this.sizeV = sizeV;
		densityPanel = new DensityPanel(sizeV, sizeH);
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void updateView(DensityBoard board) {
		for (int i = 0; i < sizeV; i++)
			for (int j = 0; j < sizeH; j++) {
				temp = ((double) board.getDensity(i, j)
						/ (double) board.getMaxDensity() * 255);
				temp2 = (int) temp;
				temp3 = Math.abs(temp2 - 255);
				if (temp2 == 255) {
					red = 255;
					green = 0;
					blue = 0;
				} else {
					red = temp3;// * 0.2126;
					green = temp3;// * 0.7152;
					blue = temp3;// * 0.0722;
				}
				densityPanel.changeButtonColor(i, j, new Color((int) red,
						(int) green, (int) blue));
			}
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(densityPanel);
		pack();
	}

	public void showHint() {
		setVisible(true);
	}
}
