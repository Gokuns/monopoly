package domain.model.squares;

import domain.model.players.Player;

public abstract class SpecialSquare extends Square {

	public SpecialSquare(String name, String description) {
		super(name, description);
		setSpecialSquare(true);
		// TODO Auto-generated constructor stub
	}

	public abstract String action(Player p);
}
