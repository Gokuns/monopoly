package domain.model.specialSquares;

import domain.model.Card;
import domain.model.Deck;
import domain.model.Player;
import domain.model.SpecialSquare;

public class RollThree extends SpecialSquare{

	public RollThree(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String action(Player p) {
		// TODO Auto-generated method stub
		Deck roll3Deck = new Deck("roll3");
		Card c =roll3Deck.draw();
		return c.getDesc();
	}

}
