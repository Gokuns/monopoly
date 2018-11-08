package domain.model;

import java.util.ArrayList;
import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.faceValue;

public class Board {
	private GameState game = GameState.getInstance();
	private static Board board;
	List<List<Square>> Squares = new ArrayList<List<Square>>();
	private Player currentPlayer = game.getCurrentPlayer();
	private static Cup cup = Cup.getInstance();
	
	private Board() {
		initiateSquares();
	}
	
	public static synchronized Board getInstance() {
		if(board==null) {
			
			board = new Board();
			
		}
		return board;
		
	}
	
	private void initiateSquares() {
		
	}
	
	public void rollCup() {
		List<faceValue> diceValues = cup.rollCup();
		faceValue firstDieVal = diceValues.get(0);
		faceValue secondDieVal = diceValues.get(1);
		faceValue speedDieVal = diceValues.get(2);
		if(firstDieVal == secondDieVal && secondDieVal == speedDieVal) {
			currentPlayer.setRolledTriple(true);
		}else if(firstDieVal == secondDieVal || firstDieVal==speedDieVal || secondDieVal==speedDieVal) {
			currentPlayer.setRolledDouble(true);
		}else if(speedDieVal.name().equals("MRMONOPOLY")) {
			currentPlayer.setRolledMrMonopoly(true);
		}else if(secondDieVal.name().equals("BUS")) {
			currentPlayer.setRolledBus(true);
		}
			
	}
	
	public void movePiece() {
		
	}

}
