package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.gameHandler.Board;
import domain.model.players.Player;
import domain.model.squares.Square;;

class BoardTest {
	Board board = Board.getInstance();
	@Test
	void testInitiateSquares() {
		List<List<Square>> Squares = new ArrayList<List<Square>>();
		board.initiateSquares(Squares);
		assertTrue(Squares.size()>0);
	}
	
	@Test
	void testRollCupValues() {
		Player p = new Player("Ali", 1);
		Cup cup = Cup.getInstance();
		List<FaceValue> initial = cup.getFaceValues();
		FaceValue one = initial.get(0);
		FaceValue two = initial.get(1);
		FaceValue three = initial.get(2);
		List<FaceValue> result = board.rollCup(p);
		assertTrue(one != result.get(0) || two != result.get(1) || three != result.get(2));	
	}
	@Test 
	void testRollCupPlayerState(){
		Player p = new Player("Ali", 1);
		Cup cup = Cup.getInstance();
		board.rollCup(p);
		assertTrue(p.isRolled());
		
	}

}
