package domain.model.specialSquares.payCorners;

import domain.model.Player;
import domain.model.specialSquares.PayCorner;

public class Go extends PayCorner{
	
	private static Go go;
	
	public static synchronized Go getInstance() {
		if(go == null) {
			go = new Go("Go", "Take $200 when you pass");
		}return go;
	}

	private Go(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void action(Player p) {
		// TODO Auto-generated method stub
		
	}
}
