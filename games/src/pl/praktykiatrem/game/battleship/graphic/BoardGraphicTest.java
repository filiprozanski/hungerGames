package pl.praktykiatrem.game.battleship.graphic;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import pl.praktykiatrem.game.battleship.Controller;

public class BoardGraphicTest {
	public static void main(String[] args)
	{
		//Controller cont = new Controller();
		game a = new game();
		a.dialogPopUp(1);
		a.showName();
	}
	
	
}

class game {
	private TestFrame f;
	private Icon optionIcon = UIManager.getIcon("FileView.computerIcon");
	private Controller cont;
	
	public game()
	{
		cont = new Controller();
		TestFrame f = new TestFrame(cont);
		//f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	public void dialogPopUp(int id){
		String name = (String)JOptionPane.showInputDialog(f
                , "Podaj imiê " + id + " gracza:"
                , "Text Field Dialog", JOptionPane.QUESTION_MESSAGE
                , optionIcon, null, null);
		cont.setName(name, id);
	}
	
	public void showName()
	{
		cont.showName();
	}
	
	Controller getCont()
	{
		return cont;
	}
}

class TestFrame extends JFrame
{
	private JPanel c;
	private JPanel d;
	private JPanel e;
	private JPanel f;
	
	public TestFrame(Controller cont)
	{
		setLayout(new GridLayout(2, 2));
		c = new BoardGraphic(cont);
		d = new BoardGraphic(cont);
		e = new BoardGraphic(cont);
		f = new BoardGraphic(cont);
		getContentPane().add(c);
		getContentPane().add(d);
		getContentPane().add(e);
		getContentPane().add(f);
		setSize(660, 660);
	}
}