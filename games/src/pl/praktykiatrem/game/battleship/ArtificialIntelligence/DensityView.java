package pl.praktykiatrem.game.battleship.ArtificialIntelligence;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DensityView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4884302083293184798L;
	private JPanel contentPane;
	private DensityPanel densityPanel;
	private int sizeH;
	private int sizeV;
	private double temp;
	private int temp2;
	private int temp3;
	private int red;
	private int green;
	private int blue;

	public DensityView(int sizeH, int sizeV) {
		this.sizeH = sizeH;
		this.sizeV = sizeV;
		densityPanel = new DensityPanel(sizeH, sizeV);
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void updateView(DensityBoard board) {
		for (int i = 0; i < sizeH; i++)
			for (int j = 0; j < sizeV; j++) {
				temp = ((double) board.getDensity(i, j)
						/ (double) board.getMaxDensity() * 255);
				temp2 = (int) temp;
				temp3 = Math.abs(temp2 - 255);
				if (temp2 == 255) {
					red = 255;
					green = 0;
					blue = 0;
				} else {
					red = temp3;
					green = temp3;
					blue = temp3;
				}
				densityPanel.changeButtonColor(i, j,
						new Color(red, green, blue));
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
		setVisible(true);
	}
}
