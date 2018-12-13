package domain.model.squares.specialSquares;

import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.players.Player;
import domain.model.squares.SquareStrategy;

public class CommunityChest implements SquareStrategy{


	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		Deck communtityDeck = new Deck("community");
		Card c = communtityDeck.draw(p);
		return c.getDesc();
	}

}
