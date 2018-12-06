package domain.model.squares.specialSquares.payCorners;

import domain.model.players.Player;
import domain.model.squares.specialSquares.PayCorner;

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
	public String action(Player p) {
		return null;
		// TODO Auto-generated method stub
		
	}
}