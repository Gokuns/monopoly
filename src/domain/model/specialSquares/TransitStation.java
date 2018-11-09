package domain.model.specialSquares;

import domain.model.Player;
import domain.model.SpecialSquare;

public class TransitStation extends SpecialSquare{

	public TransitStation(String name, String description) {
		super(name, description);
		isTransit = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void action(Player p) {
		// TODO Auto-generated method stub
		
	}

}
