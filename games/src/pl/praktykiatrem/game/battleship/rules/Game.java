package pl.praktykiatrem.game.battleship.rules;

import pl.praktykiatrem.game.battleship.gameComponents.PlayerStatus;

public class Game {
	RulesInterface rules;
	
	public Game()
	{
		rules = new CustomRules();
	}
	
	public boolean makeMove(PlayerStatus p, int x, int y)
	{
		return rules.makeMove(p, x, y);
	}
	
	public boolean placeShips(PlayerStatus p, int id, int polesNumber, char direction, int x, int y)
	{
		return rules.placeShips(p, id, polesNumber, direction, x, y);
	}
}
