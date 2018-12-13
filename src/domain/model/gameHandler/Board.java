package domain.model.gameHandler;

import java.util.ArrayList;
import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.players.Piece;
import domain.model.players.Player;
import domain.model.squares.LayerFactory;
import domain.model.squares.Square;
import domain.model.squares.SquareIterator;

/**
 * The Board Object is where the squares and cup that holds the dice are stand on.
 * it is also the class where the movement is calculated.
 * @author Goko
 *
 */
public class Board {
	private GameState game = GameState.getInstance();
	private static Board board;
	private List<List<Square>> Squares = new ArrayList<List<Square>>();
	private static Cup cup = Cup.getInstance();
	private SquareIterator iter;
	private int poolBalance;
	private LayerFactory layerFactory = LayerFactory.getInstance();
	
	/**
	 * Constructor
	 */
	private Board() {
		initiateSquares();
	}
	
	/**
	 * Singleton board, getInstance method.
	 * @return if its not created before, creates a new one
	 * if not, returns the same board object.
	 */
	public static synchronized Board getInstance() {
		if(board==null) {
			
			board = new Board();
			
		}
		return board;
		
	}
	
	/**
	 * Via the factory classes, it creates the squares and the layers
	 * this method adds the layers to Squares list.
	 */
	private void initiateSquares() {
		List<Square> layerOne = layerFactory.createLayer("inner");
		List<Square> layerTwo = layerFactory.createLayer("middle");
		List<Square> layerThree = layerFactory.createLayer("outer");
		Squares.add(layerOne);
		Squares.add(layerTwo);
		Squares.add(layerThree);
		
	}
	
	/**
	 * Rolls the dice in the cup one by one.
	 * Checks the conditions of double, Mr Monopoly etc
	 */
	public void rollCup() {
		List<FaceValue> diceValues = cup.rollCup();
		FaceValue firstDieVal = diceValues.get(0);
		FaceValue secondDieVal = diceValues.get(1);
		FaceValue speedDieVal = diceValues.get(2);
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
	
	/**
	 * Moves the piece of the current player according to the current faceValues of the dice.
	 * @param currentPlayer the Player who has the turn.
	 * @return A List of Square's that the player's piece has passed.
	 */
	public ArrayList<Square> movePiece(Player currentPlayer) {
		Square currentSquare = getPlayersSquare(currentPlayer);
		iter = new SquareIterator(currentSquare, Squares);
		int movement = calculateMovement();
		ArrayList<Square> movedSquares = new ArrayList<Square>();
		boolean even = movement%2 == 0;
		for (int i=0;i<movement;i++) {
			if(currentSquare.isTransit() && currentPlayer.isChangingLayer()==false)  {
				if(even){
					currentPlayer.setChangingLayer(true);
					if(iter.hasInner()) {
						currentSquare = iter.inner();
						movedSquares.add(currentSquare);
					}
					else { currentSquare = iter.outer();
					movedSquares.add(currentSquare);
					}
					currentSquare = iter.next();
					movedSquares.add(currentSquare);
				}else {
					currentSquare = iter.next();
					movedSquares.add(currentSquare);
				}		
			}else {
				if(i<movement-1 && currentSquare.getPayStrat()!=null) {
					currentSquare.tryToGetPaid(currentPlayer);
				}
				currentPlayer.setChangingLayer(false);
				currentSquare = iter.next();
				movedSquares.add(currentSquare);
				
			}
		}
		Square landedOn = currentSquare;
		setPlayersSquare(currentPlayer,landedOn);		
		System.out.println("Moved to " + getPlayersSquare(game.getCurrentPlayer()).getName());
		currentPlayer.setMoved(true);
		
		return movedSquares;
	}
	
	
	/**
	 * Moves the current player's piece to the target square immediately. For action methods
	 * @param currentPlayer The player who has the turn
	 * @param squareToMove The target square that the piece will go.
	 */
	public void movePiece(Player currentPlayer, Square squareToMove) {
		setPlayersSquare(currentPlayer,squareToMove);
		System.out.println("Transported to " + getPlayersSquare(game.getCurrentPlayer()).getName());
	}
	
	
	/**
	 * When searching for a certain square on the board, this gets us the layer.
	 * @param sq The square that we are trying to find
	 * @return the int value of the layer that contains target square.
	 */
	public int getSquareLayerIndex(Square sq) {
		int result=-1;
	if(Squares.get(0).contains(sq)) result =0;
	else if(Squares.get(1).contains(sq)) result = 1;
	else if(Squares.get(2).contains(sq)) result = 2;
	return result;
	}
	
	/**
	 * With the method above after we find the layer that contains the target square
	 * this method iterates over the squares till it finds the square.
	 * @param sq the target square
	 * @return The squares index number in the list.
	 */
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
	
	/**
	 * Takes the cups current face values and calculates it to an int.
	 * This calculation only consist of the pure movement.
	 * Exluding the features such as Mr. Monopoly or tiple roll.
	 * @return the result of the calculation
	 */
	private int calculateMovement() {

		List<Integer> fValues = cup.convertFaceValueToInt();
		int first = fValues.get(0);
		int second = fValues.get(1);
		int speed = fValues.get(2);
		int result = first + second + speed;
		System.out.println(result);
		return result;
		
	}
	
	/**
	 * Used for getting the given players current square.
	 * @param p the Player who we want to get the square
	 * @return The current square of the Player.
	 */
	private Square getPlayersSquare(Player p) {
		Piece piece = p.getPiece();
		Square playerSquare = piece.getCurrentSquare();
		return playerSquare;
	}
	
	/**
	 * Changes the player's piece's current square to a given square.
	 * @param p The player
	 * @param s The target square.
	 */
	private void setPlayersSquare(Player p, Square s) {
		Piece piece = p.getPiece();
		piece.setCurrentSquare(s);
	}
	
	/**
	 * The money accumulated in the pool on the board
	 * @return int value of the pool money.
	 */
	public int getPoolBalance() {
		return poolBalance;
	}
	
	/**
	 * Sets the pool balance
	 * @param poolBalance the new pool balance
	 */
	public void setPoolBalance(int poolBalance) {
		this.poolBalance = poolBalance;
	}
	
	/**
	 * The list of lists that contains all the squares on the board
	 * @return List of lists
	 */
	public List<List<Square>> getSquares() {
		return Squares;
	}
	
	/**
	 * Sets the list of list of squares to a given list of lists
	 * @param squares new list
	 */
	public void setSquares(List<List<Square>> squares) {
		Squares = squares;
	}	

}
