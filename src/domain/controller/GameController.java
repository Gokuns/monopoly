package domain.controller;

import java.util.List;

import domain.model.Board;
import domain.model.GameState;
import domain.model.Player;
import domain.model.dice.Cup;
import domain.model.dice.faceValue;

public class GameController {
	private static GameController controller;
	private Board board = Board.getInstance();
	
	
	private GameController() {}
	
	public static synchronized GameController getInstance() {
		if(controller == null) {
			controller = new GameController();
		}
		return controller;
	}
	
	public void roll() {
		board.rollCup();
		System.out.println("Piece move Completed");
	}
	
	
	
}
