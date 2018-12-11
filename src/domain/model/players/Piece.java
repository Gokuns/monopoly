package domain.model.players;

import domain.model.Board;
import domain.model.squares.Square;

public class Piece {
	private Square currentSquare;
     
     public Piece() {
      currentSquare = Board.getInstance().getSquares().get(1).get(0);
     }

	/**
	 * @return the currentSquare
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * @param currentSquare the currentSquare to set
	 */
	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}  
}
