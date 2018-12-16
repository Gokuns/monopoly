package domain.model;

import java.util.ArrayList;
import java.util.HashMap;

import domain.model.dice.Cup;
import domain.model.players.Player;
import domain.model.squares.Square;

/**
 * The Class that holds the information of the players and being the subject.
 * This is the object that will be serialized.
 * @author Goko
 *
 */
public class GameState {
	private static GameState game;
	private Player currentPlayer;
//	private int nPlayers;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private ArrayList<Player> orderedPlayerList = new ArrayList<Player>();
	
	@SuppressWarnings("unused")
	private static Board board = Board.getInstance();
	@SuppressWarnings("unused")
	private static Cup cup = Cup.getInstance();
	private ArrayList<GameStateListener> networkListeners = new ArrayList<GameStateListener>();
	private ArrayList<GameStateListener> UIListeners = new ArrayList<GameStateListener>();
	
	/**
	 * Constructor
	 */
	private GameState() {}
	
	/**
	 * Singleton getInstance method
	 * @return gameState object, creates new if not created before.
	 */
	public static synchronized GameState getInstance() {
		if(game == null) {
			game = new GameState();
		}
		return game;
	}
	
	/** adds the listener to network listeners list.
	 * @param listener: observer that observers this class.
	 */
	public void addNetworkListener(GameStateListener listener) {
		networkListeners.add(listener);
	}
	
	/**
	 * adds the listener to UI listeners list.
	 * @param listener: observer that observers this class.
	 */
	public void addUIListener(GameStateListener listener) {
		UIListeners.add(listener);
	}
	
	/**
	 * send the  listeners from network classes the command to update their states.
	 * @param map: hashmap, containing the info
	 */
	public void publishToNetworkListeners(HashMap<String, String> map) {
		for(GameStateListener listener : networkListeners) {
			listener.update(this, map);
		}
	}
	
	/**
	 * send the listeners from UI classes the command to update their states.
	 * @param map: hashmap, containing the info
	 */
	public void publishToUIListeners(HashMap<String, String> map) {
		for(GameStateListener listener : UIListeners) {
			listener.update(this, map);
		}
	}
	
	/**
	 * Changes the currently selected player to the next one in the list
	 * @return Player that is next in the line.
	 */
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
	
	/**
	 * get currently selected players squares
	 * @return Square
	 */
	public Square getPlayerCurrentSquare() {
		return currentPlayer.getPiece().getCurrentSquare();
	}
	
	/**
	 * getter of the current player.
	 * @return Player.
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

// TODO Remove unused code
// 	public int getnPlayers() {
// 		return nPlayers;
// 	}
	
	/**
	 * The list of players in the game
	 * @return List of Players
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * The ordered version of the Player list.
	 * @return List of PLayers
	 */
	public ArrayList<Player> getOrderedPlayerList() {
		return orderedPlayerList;
	}

	/**
	 * setter of the current player field.
	 * @param currentPlayer : different player.
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

// TODO Remove unused code
// 	public void setnPlayers(int nPlayers) {
// 		this.nPlayers = nPlayers;
// 	}

	/**
	 * Setter of the player list
	 * @param playerList : List of Players
	 */
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	/**
	 * setter of the ordered player list
	 * @param orderedPlayerList : List of Players
	 */
	public void setOrderedPlayerList(ArrayList<Player> orderedPlayerList) {
		this.orderedPlayerList = orderedPlayerList;
	}
	
	/**
	 * Adds the player the Player list
	 * @param username : username of the new player
	 * @param ID : Id of the new player
	 */
	public void addPlayer(String username, int ID) {
		Player p = new Player(username, ID);
		playerList.add(p);
	}
	
}
