package pl.praktykiatrem.game.battleship.files;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import pl.praktykiatrem.game.tictactoe.rules.Sign;

public class TTIcons {
	private static ImageIcon[] signIcons = new ImageIcon[2];
	private static int size;

	public static final void createImages(int size) {
		TTIcons.size = size;

		try {
			signIcons[0] = new ImageIcon(
					ShipIcons.class.getResource("\\tttIcons\\O.png"));
			signIcons[1] = new ImageIcon(
					ShipIcons.class.getResource("\\tttIcons\\X.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static ImageIcon getIcon(Sign sign) {
		return drawIcon(sign);
	}

	public static ImageIcon getBigIcon(Sign sign) {
		return drawBigIcon(sign);
	}

	private static ImageIcon drawIcon(Sign sign) {
		BufferedImage img = new BufferedImage(size, size,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D temp = img.createGraphics();
		temp.setStroke(new BasicStroke(5));

		if (sign == Sign.O) {
			temp.setColor(Color.RED);
			temp.drawOval(2, 2, size - 5, size - 5);
		} else if (sign == Sign.X) {
			temp.setColor(Color.BLUE);
			temp.drawLine(0, 0, size - 1, size - 1);
			temp.drawLine(size - 1, 0, 0, size - 1);
		}

		temp.dispose();

		return new ImageIcon(img);
	}

	private static ImageIcon drawBigIcon(Sign sign) {
		BufferedImage img = new BufferedImage(100, 100,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D temp = img.createGraphics();
		temp.setStroke(new BasicStroke(5));

		if (sign == Sign.O) {
			temp.setColor(Color.RED);
			temp.drawOval(2, 2, 95, 95);
		} else if (sign == Sign.X) {
			temp.setColor(Color.BLUE);
			temp.drawLine(0, 0, 99, 99);
			temp.drawLine(99, 0, 0, 99);
		}

		temp.dispose();

		return new ImageIcon(img);
	}
}
