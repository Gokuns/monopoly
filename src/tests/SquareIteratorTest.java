package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import domain.model.Board;
import domain.model.squares.*;;

public class SquareIteratorTest {
	
	private SquareIterator iterator;
	private List<List<Square>> squares;
	private Square currentSquare;
	
	
	@Before
    public void setUp() throws Exception {
		currentSquare = Board.getInstance().getSquares().get(1).get(0);
		iterator = new SquareIterator(currentSquare ,Board.getInstance().getSquares());
		System.out.println(Board.getInstance().getSquares().get(0).size());
		System.out.println(Board.getInstance().getSquares().get(1).size());
		System.out.println(Board.getInstance().getSquares().get(2).size());
	}

	@Test
	public void testNextIndex() {
		assertEquals(iterator.nextIndex(),1);
		iterator = new SquareIterator(Board.getInstance().getSquares().get(0).get(23) ,Board.getInstance().getSquares());
		assertEquals(iterator.nextIndex(),0);
	}
	
	@Test
	public void testNext() {
		iterator.next();
		assertEquals(iterator.nextIndex(),2);
		iterator.next();
		assertEquals(iterator.nextIndex(),3);
		iterator = new SquareIterator(Board.getInstance().getSquares().get(0).get(22) ,Board.getInstance().getSquares());
		assertEquals(iterator.nextIndex(),23);
		iterator.next();
		assertEquals(iterator.nextIndex(),0);
		
		
	}
	
	@Test 
	public void testFindLayer() {
		assertEquals(iterator.findLayer(currentSquare),Board.getInstance().getSquares().get(1));
		currentSquare = Board.getInstance().getSquares().get(1).get(10);
		assertEquals(iterator.findLayer(currentSquare),Board.getInstance().getSquares().get(1));
		currentSquare = Board.getInstance().getSquares().get(0).get(0);
		assertEquals(iterator.findLayer(currentSquare),Board.getInstance().getSquares().get(0));
		currentSquare = Board.getInstance().getSquares().get(2).get(0);
		assertEquals(iterator.findLayer(currentSquare),Board.getInstance().getSquares().get(2));
		
		
		
	}
	
}
