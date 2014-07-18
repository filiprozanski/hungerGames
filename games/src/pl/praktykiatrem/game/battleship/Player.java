package pl.praktykiatrem.game.battleship;

public class Player {
	private String name;
	
	public Player()
	{
		name = "NoNameGiven";
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
