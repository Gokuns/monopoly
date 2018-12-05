package domain.model.specialSquares;

import domain.model.Player;
import domain.model.SpecialSquare;

public abstract class PayCorner extends SpecialSquare{



	public PayCorner(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract String action(Player p);

}
