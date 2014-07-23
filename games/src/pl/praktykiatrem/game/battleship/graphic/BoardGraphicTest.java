package pl.praktykiatrem.game.battleship.graphic;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import pl.praktykiatrem.game.battleship.graphic.panels.ShipSettingPanel;

public class BoardGraphicTest {
	public static void main(String[] args) {
		game a = new game();
		a.dialogPopUp(1);
	}
}

class game {
	private TestFrame f;
	private Icon optionIcon = UIManager.getIcon("FileView.computerIcon");

	public game() {
		TestFrame f = new TestFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	public void dialogPopUp(int id) {
		String name = (String) JOptionPane.showInputDialog(f, "Podaj imiê "
				+ id + " gracza:", "Text Field Dialog",
				JOptionPane.QUESTION_MESSAGE, optionIcon, null, null);
		// cont.setName(name, id); //very wa¿ne!
	}
}

class TestFrame extends JFrame {
	private ShipSettingPanel settingPanel;

	public TestFrame() {
		settingPanel = new ShipSettingPanel();
		getContentPane().add(settingPanel);
		setSize(660, 660);
	}
}