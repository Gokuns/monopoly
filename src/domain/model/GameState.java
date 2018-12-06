package domain.model;

import java.util.ArrayList;
import java.util.HashMap;

import domain.model.dice.Cup;
import domain.model.players.Piece;
import domain.model.players.Player;
import domain.model.squares.Square;

public class GameState {
	private static GameState game;
	private Player currentPlayer;
	private int nPlayers;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Player> orderedPlayerList = new ArrayList<Player>();
	
	@SuppressWarnings("unused")
	private static Board board = Board.getInstance();
	@SuppressWarnings("unused")
	private static Cup cup = Cup.getInstance();
	private ArrayList<GameStateListener> networkListeners = new ArrayList<GameStateListener>();
	private ArrayList<GameStateListener> UIListeners = new ArrayList<GameStateListener>();
	
	private GameState() {}
	
	public static synchronized GameState getInstance() {
		if(game == null) {
			game = new GameState();
		}
		return game;
	}
	
	public void addNetworkListener(GameStateListener listener) {
		networkListeners.add(listener);
	}
	
	public void addUIListener(GameStateListener listener) {
		UIListeners.add(listener);
	}
	
	public void publishToNetworkListeners(HashMap<String, String> map) {
		for(GameStateListener listener : networkListeners) {
			listener.update(this, map);
		}
	}
	
	public void publishToUIListeners(HashMap<String, String> map) {
		for(GameStateListener listener : UIListeners) {
			listener.update(this, map);
		}
	}
	
	public Player getNextPlayer() {
		
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

	public void addPlayer(String username, int ID) {
		Player p = new Player(username, ID, new Piece());
		playerList.add(p);
	}
	
}
