package domain.model;

import domain.model.specialSquares.payCorners.Go;

public class Piece {
	private Square currentSquare;
     
     public Piece(Square currentSquare) {
     this.currentSquare = currentSquare;
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
     
    /*
     Oriental = {
    		 "Price" : 120,
    		 "Rent" : 6,W
    		 "1st house" : 12
     }
     */
     
}
