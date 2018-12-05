package domain.model.specialSquares;

import domain.model.Player;
import domain.model.SpecialSquare;

public class TransitStation extends SpecialSquare{

	public TransitStation(String name, String description) {
		super(name, description);
		this.setTransit(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String action(Player p) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
