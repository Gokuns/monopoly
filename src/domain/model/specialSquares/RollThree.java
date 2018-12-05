package domain.model.specialSquares;

import domain.model.Players.Player;
import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.squares.SpecialSquare;

public class RollThree extends SpecialSquare{

	public RollThree(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		Deck roll3Deck = new Deck("roll3");
		Card c =roll3Deck.draw();
		return c.getDesc();
	}

}
