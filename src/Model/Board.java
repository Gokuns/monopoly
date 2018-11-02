package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private static Board board;
	List<List<Square>> Squares = new ArrayList<List<Square>>();
	
	private Board() {
		initiateSquares();
	}
	
	public static Board getInstance() {
		if(board==null) {
			
			return new Board();
			
		}else return board;
		
	}
	
	private void initiateSquares() {
		
	}

}
