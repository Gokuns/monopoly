package domain.model.squares.specialSquares;

import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.players.Player;
import domain.model.squares.SpecialSquare;

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
