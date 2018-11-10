package domain.model;

import domain.model.specialSquares.payCorners.Go;

public class Piece {
	private Square currentSquare;
     
     public Piece() {
      currentSquare = Go.getInstance();
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
