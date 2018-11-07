package controller;

import java.util.List;

import Model.Board;
import Model.GameState;
import Model.Player;
import Model.Dice.Cup;
import Model.Dice.faceValue;

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
	}
	
	
	
}
