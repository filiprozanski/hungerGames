package pl.praktykiatrem.game.battleship;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoardGraphicTest {
	public static void main(String[] args)
	{
		TestFrame f = new TestFrame();
		//f.setResizable(false);
		f.setVisible(true);
	}
	
}

class TestFrame extends JFrame
{
	private JPanel c;
	private JPanel d;
	private JPanel e;
	private JPanel f;
	
	public TestFrame()
	{
		setLayout(new GridLayout(2, 2));
		c = new BoardGraphic();
		d = new BoardGraphic();
		e = new BoardGraphic();
		f = new BoardGraphic();
		getContentPane().add(c);
		getContentPane().add(d);
		getContentPane().add(e);
		getContentPane().add(f);
		setSize(660, 660);
	}
}