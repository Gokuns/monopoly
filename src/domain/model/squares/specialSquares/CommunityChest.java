package domain.model.squares.specialSquares;

import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.players.Player;
import domain.model.squares.SpecialSquare;

public class CommunityChest extends SpecialSquare{

	public CommunityChest(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		Deck communtityDeck = new Deck("community");
		Card c = communtityDeck.draw();
		return c.getDesc();
	}

}
