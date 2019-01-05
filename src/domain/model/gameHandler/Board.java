package domain.model.gameHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.players.Piece;
import domain.model.players.Player;
import domain.model.squares.LayerFactory;
import domain.model.squares.Square;
import domain.model.squares.SquareIterator;

/**
 * @overview The Board Object is where the squares and cup that holds the dice are stand on.
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
	private HashMap<String, Integer> coloredDistricts= new HashMap<String, Integer>();
	
	/**
	 * Constructor
	 */
	private Board() {
		initiateSquares(this.Squares);
		intializeColoredDistricts();
	}
	
	public HashMap<String, Integer> getColoredDistricts() {
		return coloredDistricts;
	}

	public void setColoredDistricts(HashMap<String, Integer> coloredDistricts) {
		this.coloredDistricts = coloredDistricts;
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
	 * @param Squares list of list of squares
	 * @modifies Squares list of lists in Board's fields
	 * @requires a new Squares list of lists
	 * @effects The SquareIterator
	 */
	public void initiateSquares(List<List<Square>> Squares) {
		List<Square> layerOne = layerFactory.createLayer("inner");
		List<Square> layerTwo = layerFactory.createLayer("middle");
		List<Square> layerThree = layerFactory.createLayer("outer");
		Squares.add(layerOne);
		Squares.add(layerTwo);
		Squares.add(layerThree);
		
	}
	
	private void intializeColoredDistricts(){
		coloredDistricts.put("Purple", 2);
		coloredDistricts.put("Light Blue", 3);
		coloredDistricts.put("Magenta", 3);
		coloredDistricts.put("Orange", 3);
		coloredDistricts.put("Red", 3);
		coloredDistricts.put("Yellow", 3);
		coloredDistricts.put("Dark Blue", 2);
		coloredDistricts.put("Dark Green", 3);
		coloredDistricts.put("Dark Orange", 3);
		coloredDistricts.put("White", 3);
		coloredDistricts.put("Black", 3);
		coloredDistricts.put("Grey", 3);
		coloredDistricts.put("Pink", 3);
		coloredDistricts.put("Light Green ", 4);
		coloredDistricts.put("Light Yellow ", 4);
		coloredDistricts.put("Turquiose", 4);
		coloredDistricts.put("Wine Red", 4);
		coloredDistricts.put("Dark Yellow", 4);
		coloredDistricts.put("Tan", 4);
		coloredDistricts.put("Dark Red", 3);
	}
	
	/**
	 * Rolls the dice in the cup one by one.
	 * @param player: The current player.
	 * @return list of FaceValues
	 * @requires a cup instance that holds three dice
	 * @modifies the FaceValues of the dice., players moved state
	 * @effects the movement.
	 */
	public List<FaceValue> rollCup(Player player) {
		List<FaceValue> diceValues = cup.rollCup();	
		if(player!=null) {
		player.setRolled(true);
		}
		return diceValues;
	}
	
	/**
	 * Sets the Players state according to the dice values
	 * @param p: The current player
	 * @requires a cup object
	 * @modifies players state according to the faceValues.
	 * @effects the players state
	 */
	public void setDieToPlayerState(Player p) {
		List<FaceValue> diceValues = cup.getFaceValues();
		FaceValue firstDieVal = diceValues.get(0);
		FaceValue secondDieVal = diceValues.get(1);
		FaceValue speedDieVal = diceValues.get(2);
		if(firstDieVal == secondDieVal && secondDieVal == speedDieVal) {
			p.setRolledTriple(true);
		}else if(firstDieVal == secondDieVal || firstDieVal==speedDieVal || secondDieVal==speedDieVal) {
			p.setRolledDouble(true);
			p.setInJail(false); // If one rolls double then he gets out of the jail for sure.
		}else if(speedDieVal.name().equals("MRMONOPOLY")) {
			p.setRolledMrMonopoly(true);
		}else if(secondDieVal.name().equals("BUS")) {
			p.setRolledBus(true);
		}

	}
	
	/**
	 * Moves the piece of the current player according to the current faceValues of the dice.
	 * @param currentPlayer: the Player who has the turn.
	 * @return A List of Square's that the player's piece has passed.
	 * @requires A player's piece on a square on the board.
	 * @modifies the given players currentSquare
	 * @effects The game state.
	 */
	public ArrayList<Square> movePiece(Player currentPlayer) {
		
		ArrayList<Square> movedSquares = new ArrayList<Square>();
		currentPlayer.setMoved(true);
		
		if(currentPlayer.isInJail()){// If player is in jail then make the player stay where he is currently at for that movement.
			Piece currentPlayerPiece = currentPlayer.getPiece();
			Square currentPlayerSquare = currentPlayerPiece.getCurrentSquare();
			movedSquares.add(currentPlayerSquare);
			System.out.println("Moved to " + getPlayersSquare(game.getCurrentPlayer()).getName());
			return movedSquares;
		}
		
		Square currentSquare = getPlayersSquare(currentPlayer);
		iter = new SquareIterator(currentSquare, Squares);
		int movement = calculateMovement();
		
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
		if(landedOn.isProperty()) {
			currentPlayer.setEnableBuy(true);
		}
		return movedSquares;
	}
	
	
	/**
	 * Moves the current player's piece to the target square immediately. For action methods
	 * @param currentPlayer The player who has the turn
	 * @param squareToMove The target square that the piece will go.
	 * @requires a player from the available players.
	 * @modifies the currentSquare of the given player.
	 * @effects Card actions.
	 */
	public void movePiece(Player currentPlayer, Square squareToMove) {
		setPlayersSquare(currentPlayer,squareToMove);
		System.out.println("Transported to " + getPlayersSquare(game.getCurrentPlayer()).getName());
	}
	
	
	/**
	 * When searching for a certain square on the board, this gets us the layer.
	 * @param sq The square that we are trying to find
	 * @return the int value of the layer that contains target square.
	 * @requires Squares list to be initialized.
	 * @modifies nothing
	 * @effects  the movement, getSquareIndex, SquareIterator
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
	 * @requires Squares list to be initialized
	 * @modifies nothing.
	 * @effects SquareIterator, the movement
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
	 * @requires a defined FaceValues from the cup.
	 * @modifies nothing.
	 * @effects the movement
	 */
	private int calculateMovement() {

		List<Integer> fValues = cup.convertFaceValueToInt();
		int first = fValues.get(0);
		int second = fValues.get(1);
		int speed = fValues.get(2);
		int result = first + second + speed;
		System.out.println("The dice result is = " + result);
		return result;
		
	}
	
	/**
	 * Used for getting the given players current square.
	 * @param p the Player who we want to get the square
	 * @return The current square of the Player.
	 * @requires a Player's piece on a square
	 * @modifies nothing.
	 * @effects the movement
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
	 * @requires an available player and a created Square.
	 * @modifies the currentSquare of the PLayer
	 * @effects movePiece
	 */
	private void setPlayersSquare(Player p, Square s) {
		Piece piece = p.getPiece();
		piece.setCurrentSquare(s);
	}
	
	/**
	 * The money accumulated in the pool on the board
	 * @return int value of the pool money.
	 * @requires a instantiated poolBalance field.
	 * @modifies nothing.
	 * @effects card effects.
	 */
	public int getPoolBalance() {
		return poolBalance;
	}
	
	/**
	 * Sets the pool balance
	 * @param poolBalance the new pool balance
	 * @requires a Board.
	 * @modifies the pool balance on the board.
	 * @effects card effects.
	 */
	public void setPoolBalance(int poolBalance) {
		this.poolBalance = poolBalance;
	}
	
	/**
	 * The list of lists that contains all the squares on the board
	 * @return List of lists
	 * @requires an instantiated List of Squares.
	 * @modifies nothing
	 * @effects the movement
	 */
	public List<List<Square>> getSquares() {
		return Squares;
	}
	
	/**
	 * Sets the list of list of squares to a given list of lists
	 * @param squares new list
	 * @requires a Board object
	 * @modifies the Squares list.
	 * @effects Square iterator.
	 */
	public void setSquares(List<List<Square>> squares) {
		Squares = squares;
	}	
	
	/**
	 * Finds the square with the given name
	 * @param name the name of the target square
	 * @param squares the list of all the squares
	 * @return the square with the given name
	 * @requires a created board, and a correct name
	 * @effects the save load
	 */
	public Square findSquare(String name, List<List<Square>> squares) {
		Square result=null;
		for(List<Square> layer: squares) {
			for(Square sq:layer) {
				if(sq.getName().equals(name)) {
					result = sq;
				}
			}
		}
		return result;
	}

}
