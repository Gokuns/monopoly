package Model;

import java.util.ArrayList;

public class GameState {
	private Player currentPlayer;
	private int nPlayers;
	private ArrayList<Player> playerList;
	private ArrayList<Player> orderedPlayerList;
	
	private Player getNextPlayer() {
		
		int nPlayers = orderedPlayerList.size();
		int currentPlayerID = currentPlayer.getID();
		int currentPlayerIndex=0;
		Player nextPlayer = null;
		
		for(Player p:orderedPlayerList) {
			if(p.getID() == currentPlayerID) {
				currentPlayerIndex = orderedPlayerList.indexOf(p);
				break;
			}
		}
		
		if(currentPlayerIndex == nPlayers - 1) {
			nextPlayer = orderedPlayerList.get(0);
		}else {
			nextPlayer = orderedPlayerList.get(currentPlayerIndex+1);
		}
		
		return nextPlayer;
	}
	
}
