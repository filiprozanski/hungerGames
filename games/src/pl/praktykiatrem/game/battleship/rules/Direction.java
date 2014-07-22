package pl.praktykiatrem.game.battleship.rules;

public enum Direction {
	HORIZONTAL('H'), VERTICAL('V');
    
    private char abbreviation;
    
    private Direction(char abb)
    {
        abbreviation = abb;
    }
    
    public char getAbbreviation()
    {
        return abbreviation;
    }
}
