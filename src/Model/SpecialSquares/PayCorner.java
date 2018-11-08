package Model.SpecialSquares;

import Model.Player;
import Model.SpecialSquare;

public abstract class PayCorner extends SpecialSquare{



	public PayCorner(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected abstract void action(Player p);

}
