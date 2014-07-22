package pl.praktykiatrem.game.battleship.eventsSample;

import javax.swing.JOptionPane;

public class TestClass {

	public static void main(String[] args) {
		GeneratorZdarzen zd = new GeneratorZdarzen();
		zd.addWybuchListener(new WybuchListener() {

			@Override
			public void wybuchlo(WybuchEvent event) {
				System.out.println("Wybuchlo " + event.getPoziomZniszczen());

			}
		});
		WybuchListener listener2 = new WybuchListener() {

			@Override
			public void wybuchlo(WybuchEvent event) {
				JOptionPane.showMessageDialog(null,
						"Wybuchlo " + event.getPoziomZniszczen());

			}
		};
		zd.addWybuchListener(listener2);
		zd.wykonajEksperyment();
		zd.removeWybuchListener(listener2);
		zd.wykonajEksperyment();

	}

}
