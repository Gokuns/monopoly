package domain.model.squares.specialSquares.payCorners;

import domain.model.players.Player;
import domain.model.squares.PayCornerStrategy;
import domain.model.squares.SquareStrategy;

public class Go implements PayCornerStrategy, SquareStrategy{
	
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
		p.setBalance(p.getBalance()+200);
		return null;
	}

	@Override
	public String action(Player p) {
		// TODO Auto-generated method stub
		p.setBalance(p.getBalance()+200);
		return null;
	}
}
