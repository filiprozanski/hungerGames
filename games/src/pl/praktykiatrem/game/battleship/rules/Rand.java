package pl.praktykiatrem.game.battleship.rules;

import java.awt.Color;
import java.util.Random;

import pl.praktykiatrem.game.battleship.gameComponents.Direction;

public class Rand {

	static Random generator = new Random();

	public static Direction getRandDirection() {
		if (generator.nextBoolean() == true)
			return Direction.HORIZONTAL;
		else
			return Direction.VERTICAL;
	}

	public static int getRand(int b) {
		return generator.nextInt(b);
	}

	public static int getRandX(Game gameRules) {
		return generator.nextInt(gameRules.getBoardSizeV());
	}

	public static int getRandY(Game gameRules) {
		return generator.nextInt(gameRules.getBoardSizeH());
	}

	public static Color getRandColor() {
		return new Color(generator.nextInt(255), generator.nextInt(255),
				generator.nextInt(255));
	}
}
