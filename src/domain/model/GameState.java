package domain.model;

import java.util.ArrayList;
import java.util.HashMap;

import domain.model.dice.Cup;
//import domain.model.specialSquares.payCorners.Go;

public class GameState {
	private static GameState game;
//	private Player player1 = new Player("Player 1", 0, new Piece());
//	private Player player2 = new Player("Player 2", 1, new Piece());
//	private Player player3 = new Player("Player 3", 2, new Piece());
	private Player currentPlayer;
	private int nPlayers;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Player> orderedPlayerList = new ArrayList<Player>();
//	@SuppressWarnings("serial")
//	private ArrayList<Player> orderedPlayerList = new ArrayList<Player>() {{
//	    add(player1);
//	    add(player2);
//	    add(player3);
//	}};
	
	private static Board board = Board.getInstance();
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
	
	//2189139812390
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
	
}
