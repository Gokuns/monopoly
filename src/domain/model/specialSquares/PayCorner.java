package domain.model.specialSquares;

import domain.model.Player;
import domain.model.SpecialSquare;

public abstract class PayCorner extends SpecialSquare{



	public PayCorner(String name, String description, int layer, int number) {
		super(name, description, layer, number);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected abstract void action(Player p);

}
