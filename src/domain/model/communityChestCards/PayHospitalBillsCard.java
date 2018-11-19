package domain.model.communityChestCards;

import java.util.HashMap;

import domain.model.Board;
import domain.model.CommunityChestCard;
import domain.model.GameState;
import domain.model.Player;

public class PayHospitalBillsCard extends CommunityChestCard{

	
	@Override
	protected void action() {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		GameState game = GameState.getInstance();
		Player currentPlayer = game.getCurrentPlayer();
		
		int newPoolBalance = board.getPoolBalance() + 100;
		int newPlayerBalance = currentPlayer.getBalance() - 100;
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "payHospitalBill");
		
		board.setPoolBalance(newPoolBalance);
		currentPlayer.setBalance(newPlayerBalance);
		
		String pName = currentPlayer.getName();
		
		mapForUITransfer.put(pName, ((Integer) newPlayerBalance).toString());
		mapForUITransfer.put("Pool", ((Integer) newPoolBalance).toString());
		
		game.publishToUIListeners(mapForUITransfer);////displays player&pool balances in UI.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
	}

	
	
}
