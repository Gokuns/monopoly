package domain.model.squares.specialSquares.payCorners;

import domain.model.players.Player;
import domain.model.squares.PayCornerStrategy;

public class Go implements PayCornerStrategy{
	
	private static Go go;
	
	private Go() {}
	
	public static Go getInstance() {
		if(go==null) {
			go = new Go();
		}
		return go;
	}	

	@Override
	public String getPaid(Player p) {
		// TODO Auto-generated method stub
		return null;
	}
}
