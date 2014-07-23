package pl.praktykiatrem.game.battleship.menu;

public interface IMainView {
	final static String GAME = "Plansza";
	final static String MENU = "Menu";
	final static String GoToGame = "Przejd� do gry.";
	final static String GoToMenu = "Przejd� do menu.";
	final static String CREDITS = "O programie";
	final static String GAMEOVER = "Koniec Gry";

	public void startGame();

	public void showCredits();

	public void showMenu();

	public void showGameOver();
}
