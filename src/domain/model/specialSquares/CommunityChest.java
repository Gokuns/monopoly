package domain.model.specialSquares;

import domain.model.Card;
import domain.model.Deck;
import domain.model.Player;
import domain.model.SpecialSquare;

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
