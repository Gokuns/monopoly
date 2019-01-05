package domain.model.cards.communityChestCards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import domain.model.cards.CommunityChestCard;
import domain.model.gameHandler.Board;
import domain.model.gameHandler.GameState;
import domain.model.players.Player;

public class OpeningNightTickets extends CommunityChestCard{

	public OpeningNightTickets(String name, String desc){
		super(name, desc);
	}

	@Override
	protected void action(Player currentPlayer) {
		
		GameState game = GameState.getInstance();
		ArrayList<Player> playerList = game.getPlayerList();
		int playerCount = playerList.size();
		
		int newPlayerBalance = currentPlayer.getBalance() + (playerCount -1)* 50;
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "openingNightTickets");
		
		currentPlayer.setBalance(newPlayerBalance);
		String pName = currentPlayer.getName();
		mapForUITransfer.put(pName, ((Integer) newPlayerBalance).toString());
		
		for(Player p:playerList){
			if(p.getID() != currentPlayer.getID()){
				int currentPlayerBalance = p.getBalance();
				p.setBalance(currentPlayerBalance-50);
				String playerName = p.getName();
				int pBalance = p.getBalance();
				mapForUITransfer.put(playerName, ((Integer) pBalance).toString());
			}
		}
		
		game.publishToUIListeners(mapForUITransfer);////displays player&pool balances in UI.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
	}
	
	
	
}
