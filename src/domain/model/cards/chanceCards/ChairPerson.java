package domain.model.cards.chanceCards;

import java.util.ArrayList;
import java.util.HashMap;

import domain.model.cards.ChanceCard;
import domain.model.gameHandler.GameState;
import domain.model.players.Player;

public class ChairPerson extends ChanceCard{

	public ChairPerson(String name, String d) {
		super(name, d);
	}

	@Override
	protected void action(Player currentPlayer) {
		// TODO Auto-generated method stub
		GameState game = GameState.getInstance();
		ArrayList<Player> playerList = game.getPlayerList();
		int playerCount = playerList.size();
		
		int newPlayerBalance = currentPlayer.getBalance() - (playerCount -1)* 50;
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "chairPerson");
		
		currentPlayer.setBalance(newPlayerBalance);
		String pName = currentPlayer.getName();
		mapForUITransfer.put(pName, ((Integer) newPlayerBalance).toString());
		
		for(Player p:playerList){
			if(p.getID() != currentPlayer.getID()){
				int currentPlayerBalance = p.getBalance();
				p.setBalance(currentPlayerBalance+50);
				String playerName = p.getName();
				int pBalance = p.getBalance();
				mapForUITransfer.put(playerName, ((Integer) pBalance).toString());
			}
		}
		
		game.publishToUIListeners(mapForUITransfer);////displays player&pool balances in UI.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
	}
	
}
