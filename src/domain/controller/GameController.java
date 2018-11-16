package domain.controller;

import java.util.HashMap;
import java.util.List;

import domain.model.Board;
import domain.model.GameState;
import domain.model.Player;
import domain.model.dice.Cup;
import domain.model.dice.faceValue;

public class GameController {
	private static GameController controller;
	private Board board = Board.getInstance();
	
	private NetworkController networkController;
	
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
	
	
	public void move() {
		board.movePiece();
		System.out.println("Piece move Completed");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "endTurn");
		map.put("currentPlayer", GameState.getInstance().getCurrentPlayer().getName());
		networkController.sendMessageToPlayers(map);
	}

	public void setNetworkController(NetworkController networkController) {
		this.networkController = networkController;
	}
}
