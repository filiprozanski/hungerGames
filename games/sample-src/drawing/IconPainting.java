package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class IconPainting extends JApplet {
	static String iconFile = "largeJava2sLogo.gif";

	public void init() {
		Image starry = getImage(getURL(iconFile));
		IconPanel starPanel = new IconPanel(starry);
		getContentPane().add(starPanel, BorderLayout.CENTER);
	}

	protected URL getURL(String filename) {
		URL codeBase = this.getCodeBase();
		URL url = null;

		try {
			url = new URL(codeBase, filename);
		} catch (java.net.MalformedURLException e) {
			System.out.println("Couldn't create image: "
					+ "badly specified URL");
			return null;
		}

		return url;
	}

	public static void main(String[] args) {
		Image starImage = Toolkit.getDefaultToolkit().getImage(
				IconPainting.iconFile);

		IconPanel starPanel = new IconPanel(starImage);

		JFrame f = new JFrame("Icon");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.getContentPane().add(starPanel, BorderLayout.CENTER);
		f.setSize(new Dimension(550, 200));
		f.setVisible(true);
	}
}

class IconPanel extends JPanel {
	Image img;

	public IconPanel(Image img) {
		this.img = img;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.white);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.drawImage(img, 10, 10, 100, 100, this);
	}
}
