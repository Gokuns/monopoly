package domain.model.squares.specialSquares;

import java.util.HashMap;

import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.cards.chanceCards.Hurricane;
import domain.model.gameHandler.GameState;
import domain.model.players.Player;
import domain.model.squares.SquareStrategy;

public class ChanceAction implements SquareStrategy{

	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		Deck chanceDeck = new Deck("chance");
		Card c = chanceDeck.draw(p);
		return c.getDesc();
	}

}
