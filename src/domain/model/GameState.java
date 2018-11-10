package domain.model;

import java.util.ArrayList;

import domain.model.dice.Cup;
import domain.model.specialSquares.payCorners.Go;

public class GameState {
	private static GameState game;
	private Player currentPlayer = new Player("Goko", 0, new Piece());
	private int nPlayers;
	private ArrayList<Player> playerList;
	private ArrayList<Player> orderedPlayerList;
	private static Board board = Board.getInstance();
	private static Cup cup = Cup.getInstance();
	
	private GameState() {}
	
	public static synchronized GameState getInstance() {
		if(game == null) {
			game = new GameState();
		}
		return game;
	}
	
	
	
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
	
	public Square getPlayerCurrentSquare() {
		return currentPlayer.getPiece().getCurrentSquare();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public int getnPlayers() {
		return nPlayers;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public ArrayList<Player> getOrderedPlayerList() {
		return orderedPlayerList;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setnPlayers(int nPlayers) {
		this.nPlayers = nPlayers;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public void setOrderedPlayerList(ArrayList<Player> orderedPlayerList) {
		this.orderedPlayerList = orderedPlayerList;
	}
	
}
