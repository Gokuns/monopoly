package domain.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.gameHandler.Board;
import domain.model.gameHandler.GameLoader;
import domain.model.gameHandler.GameSaver;
import domain.model.gameHandler.GameState;
import domain.model.gameHandler.SaveData;
import domain.model.players.Player;
import domain.model.squares.Square;

/**
 * @Overview This class is the first class after UI Layer,
 * it receives messages from UI and does the necessary things or
 * notifies the responsible classes
 */

public class GameController {
	private static GameController controller;
	private Board board = Board.getInstance();
	private GameState gameState = GameState.getInstance();
	@SuppressWarnings("unused")
	private NetworkController networkController;
	private Player localPlayer;
	private GameController() {}

	public static synchronized GameController getInstance() {
		if(controller == null) {
			controller = new GameController();
		}
		return controller;
	}

	public void roll() {
		gameState.getCurrentPlayer().setRolled(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "roll");
		board.rollCup(gameState.getCurrentPlayer());
		board.setDieToPlayerState(gameState.getCurrentPlayer());
		Cup cup = Cup.getInstance();
		map.putAll(createFaceValMap(cup));
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	
	public HashMap<String, String> createFaceValMap(Cup cup){
		HashMap<String, String> map = new HashMap<String, String>();
		List<FaceValue> faceValList = cup.getFaceValues();
		for(int i=0; i<3;i++) {
			map.put("faceValue" + i, faceValList.get(i).toString());
		}
		
		return map;
	}
	
	/**
	 * @param map The String to String HashMap containing the message
	 * @modifies orderedPlayerList and currentPlayer
	 * @effects  creates and initializes as many players using playerCount from input map, 
	 * adds them to a newly created playerList that was equal to gameState.getPlayerList(),
	 * sets orderedPlayerList and currentPlayer using newly created arrayList,
	 * puts the message "gameStarted" and currentPlayer information in a HashMap,
	 * publishes it to UIListeners
	 */
	public void initializePlayers(HashMap<String, String> map) {
		ArrayList<Player> playerList = gameState.getPlayerList();
		int playerCount = Integer.parseInt(map.get("playerCount"));
		for(int i=0; i<playerCount; i++) {
			String username = map.get("player" + i + "Name");
			int ID = Integer.parseInt(map.get("player" + i + "ID"));
			Player p = new Player(username, ID);
			playerList.add(p);
		}
		gameState.setOrderedPlayerList(playerList);
		gameState.setCurrentPlayer(playerList.get(0));
		HashMap<String, String> gameStartedMap = new HashMap<String, String>();
		gameStartedMap.put("type", "gameStarted");
		gameStartedMap.put("currentPlayer", gameState.getCurrentPlayer().getName());
		gameStartedMap.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		gameState.publishToUIListeners(gameStartedMap);
	}
	/**
	 * @param isLocalCommand: 
	 * @modifies specialMap
	 * @effects method calls the moveCommand with the boolean parameter isLocalComand. 
	 * Then, move method gets which square the player must land on,puts these information
	 * into a map and publishes this information to UI listeners and network listeners.
	 */
	public void move(boolean isLocalCommand) {
		ArrayList<Square> moveList = Board.getInstance().movePiece(
				GameState.getInstance().getCurrentPlayer());
		Square landedSquare = moveList.get(moveList.size()-1);
		System.out.println("Piece move Completed");
		sendMoveCommand(isLocalCommand);

		if(landedSquare.getSqStrat()!=null) {
			HashMap<String, String> specialMap = new HashMap<String, String>();
			specialMap.put("type", "special");
			String desc = landedSquare.tryToAct(GameState.getInstance().getCurrentPlayer());
			if(desc==null) desc = landedSquare.getDescription();
			specialMap.put("description", desc);
			GameState.getInstance().publishToUIListeners(specialMap);
			if(isLocalCommand) {
				gameState.publishToNetworkListeners(specialMap);
			}
		}
		gameState.getCurrentPlayer().setMoved(true);
	}
	
	/**
	 *@param isLocalCommand
	 *@modifies map
	 *@effects This method takes a boolean as a parameter, generates a hashMap. Then puts the
	 *information about movement such as player ID, layer and square index info in this map.
	 *It publishes this map to the UI listeners and if the input boolean is true, publishes map 
	 *also to the network listeners.
	 */
	private void sendMoveCommand(boolean isLocalCommand) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "move");
		map.put("ID", GameState.getInstance().getCurrentPlayer().getID()+"");
		map.put("layer", Board.getInstance().getSquareLayerIndex(
				GameState.getInstance().getPlayerCurrentSquare())+"");
		map.put("number", Board.getInstance().getSquareIndex(
				GameState.getInstance().getPlayerCurrentSquare())+"");
		GameState.getInstance().publishToUIListeners(map);
		if(isLocalCommand) {
			gameState.publishToNetworkListeners(map);
		}
	}


	public void setNetworkController(NetworkController networkController) {
		this.networkController = networkController;
	}

	/**
	 * @param isLocalCommand: 
	 * @modifies isTurn and map
	 * @effects end turn method sets the turn of the current player as false, which terminates
	 * the players turn. Then it takes the next player to have turn from gameState and sets this players
	 * turn true, which initiates the players' turn. Method also puts the currentPlayer and currentPlayerID
	 * in map and publishes this map to UI listeners. If the input boolean of this method is true, it also
	 * publishes this map to network listeners too.
	 */
	public void endTurn(boolean isLocalCommand) {
		gameState = GameState.getInstance();
		gameState.getCurrentPlayer().setTurn(false);
		gameState.setCurrentPlayer(gameState.getNextPlayer());
		gameState.getCurrentPlayer().setTurn(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "endTurn");
		map.put("currentPlayer", gameState.getCurrentPlayer().getName());
		map.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		gameState.publishToUIListeners(map);
		if(isLocalCommand) {
			gameState.publishToNetworkListeners(map);
		}
	}
	
	public void pauseGame() {
		gameState.setGamePaused(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "pause");
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	public void resumeGame() {
		gameState.setGamePaused(false);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type",  "resume");
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	
	public void saveGame(File file) throws Exception {
		String filename = "game.json";
		GameSaver.writeJsonOnject(file, gameState, Cup.getInstance());
	}
	
	public void loadGame(File file) throws Exception {
		String filename = "game.json";
		SaveData data = SaveData.getInstance();
		data.converDataToGame(GameLoader.readJsonSimpleDemo(file), gameState, Cup.getInstance());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "load");
		ArrayList<Player> lst = gameState.getOrderedPlayerList();
		for (int i = 0; i < lst.size(); i++) {
			Player p = lst.get(i);
			Square playerSq = p.getPiece().getCurrentSquare();
			map.put("name"+i,p.getName());
			map.put("ID"+i,p.getID()+"");
			map.put("layer"+i, board.getSquareLayerIndex(playerSq)+"");
			map.put("number"+i, board.getSquareIndex(playerSq)+"");
			map.put("balance"+i, p.getBalance()+"");
			
		}
		map.put("currentPlayer",  gameState.getCurrentPlayer().getName());
		map.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		map.putAll(createFaceValMap(Cup.getInstance()));

		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
		
	}

	public Player getLocalPlayer() {
		return localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}

	/**
	 * @param username: The username of the player
	 * @param ID: the ID number of the player
	 * @modifies localPlayer
	 * @effects This method creates a new instance of Player with the input string and integer,
	 * then sets the local player to this new created player.
	 */
	public void initializeLocalPlayer(String username, int ID) {
		Player player = new Player(username, ID);
		localPlayer = player;
	}

	public void setDice(List<String> faceValueStrings) {
		List<FaceValue> faceValues = new ArrayList<FaceValue>();
		for(String str : faceValueStrings) {
			faceValues.add(FaceValue.valueOf(str));
		}
		Cup.getInstance().setFaceValues(faceValues);
	}

	public void setLocalPlayerID(int ID) {
		localPlayer.setID(ID);
	}
	
	public ArrayList<Boolean> getPlayerState(Player p) {
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		//Player p = gameState.getCurrentPlayer();
		result.add(p.isRolled());
		result.add(p.isMoved());
		result.add(p.isTurn());
		result.add(p.hasPaused());
		
		return result;
		
	}
}
