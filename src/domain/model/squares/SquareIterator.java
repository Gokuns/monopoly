package domain.model.squares;

//import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

public class SquareIterator implements ISquareIteratator {
	
	/**
	 * @Overview This class implements the interface ISQuareIterator,
	 * it`s purpose is to make the List<List<Square>> Squares iterable and
	 * to iterate through it as required
	 */
	
	private List<List<Square>> Squares;
	// private GameState game = GameState.getInstance();
	// private Player currentPlayer = game.getCurrentPlayer();
	// private Piece piece = currentPlayer.getPiece();
	// private Square currentSquare = game.getPlayerCurrentSquare();
	// private int currentLayer = findLayerIndex(currentSquare);
	private Square currentSquare;
	private List<Square> layerOne;
	private List<Square> layerTwo;
	private List<Square> layerThree;
	private Square innerTransitShort;
	private Square innerTransitPenns;
	private Square middleTransitReading;
	private Square middleTransitPenns;
	private Square middleTransitBnO;
	private Square middleTransitShort;
	private Square outerTransitReading;
	private Square outerTransitBnO;

	public SquareIterator(Square currentSquare, List<List<Square>> Squares) {
		this.currentSquare = currentSquare;
		this.Squares = Squares;
		layerOne = Squares.get(0);
		layerTwo = Squares.get(1);
		layerThree = Squares.get(2);
		innerTransitShort = layerOne.get(21);
		innerTransitPenns = layerOne.get(9);
		middleTransitReading = layerTwo.get(5);
		middleTransitPenns = layerTwo.get(15);
		middleTransitBnO = layerTwo.get(25);
		middleTransitShort = layerTwo.get(35);
		outerTransitReading = layerThree.get(7);
		outerTransitBnO = layerThree.get(35);

	}

	@Override
	public void add(int layer, Square arg0) {
		// TODO Auto-generated method stub
		Squares.get(layer).add(arg0);

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean hasPrevious() {
		// TODO this doesnt check the circle of the arraylist
		return true;
	}

	@Override
	
	/**
	 * @modifies currentSquare
	 * @effects it sets the index of the currentSquare to nextIndex(),
	 * returns the currentSquare
	 */
	public Square next() {
		// TODO Auto-generated method stub
		List<Square> layer = findLayer(currentSquare);
		Square result = layer.get(nextIndex());
		currentSquare = result;
		return result;

	}

	@Override
	
	/**
	 * @effects finds out the index of currentSquare and returns +1
	 */
	
	public int nextIndex() {
		// TODO Auto-generated method stub
		List<Square> layer = findLayer(currentSquare);
		int index = layer.indexOf(currentSquare);
		if (index + 1 == layer.size())
			return 0;
		return index + 1;
	}

	@Override
	public Square previous() {
		// TODO Auto-generated method stub
		List<Square> layer = findLayer(currentSquare);
		Square result = layer.get(previousIndex());
		currentSquare = result;
		return result;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		List<Square> layer = findLayer(currentSquare);
		int index = layer.indexOf(currentSquare);
		if (index == 0)
			return layer.size() - 1;
		return index - 1;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Square arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasOuter() {
		// TODO Auto-generated method stub

		if (currentSquare == innerTransitPenns) {
			return true;
		} else if (currentSquare == innerTransitShort) {
			return true;
		} else if (currentSquare == middleTransitReading) {
			return true;
		} else if (currentSquare == middleTransitBnO) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean hasInner() {
		// TODO Auto-generated method stub
		if (currentSquare == middleTransitPenns) {
			return true;
		} else if (currentSquare == middleTransitShort) {
			return true;
		} else if (currentSquare == outerTransitReading) {
			return true;
		} else if (currentSquare == outerTransitBnO) {
			return true;
		} else
			return false;
	}

	@Override
	public Square outer() {
		// TODO Auto-generated method stub
		if (hasOuter()) {
			if (currentSquare == innerTransitPenns) {
				currentSquare = middleTransitPenns;
			} else if (currentSquare == innerTransitShort) {
				currentSquare = middleTransitShort;
			} else if (currentSquare == middleTransitReading) {
				currentSquare = outerTransitReading;
			} else if (currentSquare == middleTransitBnO) {
				currentSquare = outerTransitBnO;
			}
			return currentSquare;
		}
		return null; // if it doesnt have any outer.
	}

	public int outerIndex() {
		List<Square> layer = findLayer(currentSquare);
		int result = layer.indexOf(outer());
		return result;
	}

	@Override
	public Square inner() {
		// TODO Auto-generated method stub
		if (hasInner()) {
			if (currentSquare == middleTransitPenns) {
				currentSquare = innerTransitPenns;
			} else if (currentSquare == middleTransitShort) {
				currentSquare = innerTransitShort;
			} else if (currentSquare == outerTransitReading) {
				currentSquare = middleTransitReading;
			} else if (currentSquare == outerTransitBnO) {
				currentSquare = middleTransitBnO;
			}
			return currentSquare;
		}
		return null; // if it doesnt have any inner.
	}

	public int innerIndex() {
		List<Square> layer = findLayer(currentSquare);
		int result = layer.indexOf(inner());
		return result;
	}

	private List<Square> findLayer(Square sq) {
		/*
		 * returns the index of the layer of the given square
		 */
		if (Squares.get(0).contains(sq))
			return Squares.get(0);
		else if (Squares.get(1).contains(sq))
			return Squares.get(1);
		else if (Squares.get(2).contains(sq))
			return Squares.get(2);
		else {
			System.out.println("Square cannot be found");
			return null;
		}
	}

	@Override
	public void add(Square e) {
		// TODO Auto-generated method stub

	}
}
