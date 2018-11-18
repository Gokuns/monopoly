package domain.model.specialSquares;

import domain.model.Player;
import domain.model.SpecialSquare;

public class TransitStation extends SpecialSquare{

<<<<<<< src/domain/model/specialSquares/TransitStation.java
	public TransitStation(String name, String description, int layer, int number) {
		super(name, description, layer, number);
=======
	public TransitStation(String name, String description) {
		super(name, description);
		this.setTransit(true);
>>>>>>> src/domain/model/specialSquares/TransitStation.java
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void action(Player p) {
		// TODO Auto-generated method stub
		
	}

}
