package domain.model.specialSquares;

import domain.model.Card;
import domain.model.Deck;
import domain.model.Player;
import domain.model.SpecialSquare;

public class ChanceAction extends SpecialSquare{

	public ChanceAction(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		Deck chanceDeck = new Deck("chance");
		Card c = chanceDeck.draw();
		return c.getDesc();
	}

}
