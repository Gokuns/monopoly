package domain.model.cards.chanceCards;

import java.util.HashMap;

import domain.model.cards.ChanceCard;
import domain.model.gameHandler.Board;
import domain.model.gameHandler.GameState;
import domain.model.players.Player;

public class SchoolFees extends ChanceCard{

	public SchoolFees(String name, String desc){
		super(name,desc);
	}

	@Override
	protected void action(Player currentPlayer) {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		GameState game = GameState.getInstance();
		
		int newPoolBalance = board.getPoolBalance() + 150;
		int newPlayerBalance = currentPlayer.getBalance() - 150;
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "schoolFees");
		board.setPoolBalance(newPoolBalance);
		currentPlayer.setBalance(newPlayerBalance);
		
		String playerName = currentPlayer.getName();
		
		mapForUITransfer.put(playerName, ((Integer) newPlayerBalance).toString());
		mapForUITransfer.put("Pool", ((Integer) newPoolBalance).toString());
		
		game.publishToUIListeners(mapForUITransfer);////displays player&pool balances in UI.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
	}
}
