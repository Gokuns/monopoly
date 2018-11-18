package domain.model.chanceCards;

import java.util.HashMap;
import java.util.List;

import domain.model.Board;
import domain.model.ChanceCard;
import domain.model.GameState;
import domain.model.Player;
import domain.model.Square;

public class GoToJailCard extends ChanceCard{

	@Override
	protected void action() {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		GameState game = GameState.getInstance();
		Player currentPlayer = game.getCurrentPlayer();
		
		List<List<Square>> squares = board.getSquares();
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "goToJail");
		
		List<Square> secondLayerSquares = squares.get(1); 
		
		Square squareToMove = secondLayerSquares.get(10); //jail Square
		
		board.movePiece(squareToMove);
		currentPlayer.setInJail(true);
		
		String pName = currentPlayer.getName();
		
		mapForUITransfer.put(pName, "has been put behind bars for some reason :)");
		
		game.publishToUIListeners(mapForUITransfer);////displays an info indicating that current player has been put in prison.
	}

}
