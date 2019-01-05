package domain.model.cards.communityChestCards;

import java.util.HashMap;

import domain.model.cards.CommunityChestCard;
import domain.model.gameHandler.Board;
import domain.model.gameHandler.GameState;
import domain.model.players.Player;

public class InheritHundredDollars extends CommunityChestCard{
	
	public InheritHundredDollars(String name, String desc){
		super(name,desc);
	}

	@Override
	protected void action(Player currentPlayer) {
		// TODO Auto-generated method stub
		GameState game = GameState.getInstance();
		
		int newPlayerBalance = currentPlayer.getBalance() - 100;
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "inheritHundredDollars");
	
		currentPlayer.setBalance(newPlayerBalance);
		
		String pName = currentPlayer.getName();
		
		mapForUITransfer.put(pName, ((Integer) newPlayerBalance).toString());
		
		game.publishToUIListeners(mapForUITransfer);////displays player balance in UI.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
	}
}
