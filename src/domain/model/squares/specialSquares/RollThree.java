package domain.model.squares.specialSquares;

import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.players.Player;
import domain.model.squares.SquareStrategy;

public class RollThree  implements SquareStrategy{


	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		Deck roll3Deck = new Deck("roll3");
		Card c =roll3Deck.draw(p);
		return c.getDesc();
	}

}
