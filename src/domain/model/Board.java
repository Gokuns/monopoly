package domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.faceValue;
import domain.model.specialSquares.payCorners.Go;

public class Board {
	private GameState game = GameState.getInstance();
	private static Board board;
	private List<List<Square>> Squares = new ArrayList<List<Square>>();
	private static Cup cup = Cup.getInstance();
	private SquareIterator iter;
	private int poolBalance;
	private LayerFactory layerFactory = LayerFactory.getInstance();
	
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
		List<Square> layerOne = layerFactory.createLayer("inner");
		List<Square> layerTwo = layerFactory.createLayer("middle");
		List<Square> layerThree = layerFactory.createLayer("outer");
		Squares.add(layerOne);
		Squares.add(layerTwo);
		Squares.add(layerThree);
		
	}
	
	public void rollCup() {
		List<faceValue> diceValues = cup.rollCup();
		faceValue firstDieVal = diceValues.get(0);
		faceValue secondDieVal = diceValues.get(1);
		faceValue speedDieVal = diceValues.get(2);
		if(firstDieVal == secondDieVal && secondDieVal == speedDieVal) {
			game.getCurrentPlayer().setRolledTriple(true);
		}else if(firstDieVal == secondDieVal || firstDieVal==speedDieVal || secondDieVal==speedDieVal) {
			game.getCurrentPlayer().setRolledDouble(true);
		}else if(speedDieVal.name().equals("MRMONOPOLY")) {
			game.getCurrentPlayer().setRolledMrMonopoly(true);
		}else if(secondDieVal.name().equals("BUS")) {
			game.getCurrentPlayer().setRolledBus(true);
		}
		game.getCurrentPlayer().setRolled(true);
			
	}
	
	public Square movePiece(Player currentPlayer) {
		Square currentSquare = getPlayersSquare(currentPlayer);
		iter = new SquareIterator(currentSquare, Squares);
		int movement = calculateMovement();
		boolean even = movement%2 == 0;
		for (int i=0;i<movement;i++) {
			if(currentSquare.isTransit() && currentPlayer.isChangingLayer()==false)  {
				if(even){
					currentPlayer.setChangingLayer(true);
					if(iter.hasInner()) currentSquare = iter.inner();
					else currentSquare = iter.outer();
				}else currentSquare = iter.next();
				
			}else {
				currentPlayer.setChangingLayer(false);
				currentSquare = iter.next();
			}
		}
		
		Square landedOn = currentSquare;
		
		setPlayersSquare(currentPlayer,landedOn);
		
		if(landedOn.isSpecialSquare()){
			((SpecialSquare) landedOn).action(currentPlayer);
		}
		
		System.out.println("Moved to " + getPlayersSquare(game.getCurrentPlayer()).getName());
		currentPlayer.setMoved(true);
		return landedOn;
		
	}
	
	
	public void movePiece(Square squareToMove) {
		setPlayersSquare(game.getCurrentPlayer(),squareToMove);
		System.out.println("Moved to " + getPlayersSquare(game.getCurrentPlayer()).getName());
	}
	
	public int getSquareLayerIndex(Square sq) {
		int result=-1;
	if(Squares.get(0).contains(sq)) result =0;
	else if(Squares.get(1).contains(sq)) result = 1;
	else if(Squares.get(2).contains(sq)) result = 2;
	return result;
	}
	
	public int getSquareIndex(Square sq) {
		List<Square> layer = Squares.get(getSquareLayerIndex(sq));
		int result=-1;
		for(int i=0;i<layer.size();i++) {
			if(layer.get(i).equals(sq)) {
				result = i;
			}
		}
		return result;
	}
	
	private int calculateMovement() {

		List<Integer> fValues = cup.convertFaceValueToInt();
		int first = fValues.get(0);
		int second = fValues.get(1);
		int speed = fValues.get(2);
		int result = first + second + speed;
		System.out.println(result);
		return result;
		
	}
	private Square getPlayersSquare(Player p) {
		Piece piece = p.getPiece();
		Square playerSquare = piece.getCurrentSquare();
		return playerSquare;
	}
	private void setPlayersSquare(Player p, Square s) {
		Piece piece = p.getPiece();
		piece.setCurrentSquare(s);
	}

	public int getPoolBalance() {
		return poolBalance;
	}

	public void setPoolBalance(int poolBalance) {
		this.poolBalance = poolBalance;
	}
	
	public List<List<Square>> getSquares() {
		return Squares;
	}

	public void setSquares(List<List<Square>> squares) {
		Squares = squares;
	}	

}
