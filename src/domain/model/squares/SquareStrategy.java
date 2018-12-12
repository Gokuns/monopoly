package domain.model.squares;

import domain.model.players.Player;

public interface SquareStrategy {
	public String action(Player p);
}
