package domain.model;

import java.util.ArrayList;
import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.faceValue;
import domain.model.specialSquares.payCorners.Go;

public class Board {
	private GameState game = GameState.getInstance();
	private static Board board;
	List<List<Square>> Squares = new ArrayList<List<Square>>();
	private static Cup cup = Cup.getInstance();
	private SquareIterator iter;
	private int poolBalance;
	
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
		List<Square> layerOne = new ArrayList<Square>();
		List<Square> layerTwo = new ArrayList<Square>();
		List<Square> layerThree = new ArrayList<Square>();
		layerTwo.add(Go.getInstance());
		for (int i=0; i<25;i++) {
			String name = i +", 1st layer";
			layerOne.add(new Street(name,100+i,"","",null));
		}
		for (int i=1; i<40;i++) {
			String name = i +", 2nd layer";
			layerTwo.add(new Street(name,100+i,"","",null));
		}
		for (int i=0; i<55;i++) {
			String name = i +", 3rd layer";
			layerThree.add(new Street(name,100+i,"","",null));
		}
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
			
	}
	
	public void movePiece() {
		Square currentSquare = getPlayersSquare(game.getCurrentPlayer());
		iter = new SquareIterator(currentSquare, Squares);
		int movement = calculateMovement();
		for (int i=0;i<movement;i++) {
			currentSquare = iter.next();
		}
		setPlayersSquare(game.getCurrentPlayer(),currentSquare);
		System.out.println("Moved to " + getPlayersSquare(game.getCurrentPlayer()).getName());
		
		
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

}
