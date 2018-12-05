package domain.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.model.Board;
import domain.model.GameState;
import domain.model.Piece;
import domain.model.Player;
import domain.model.dice.Cup;
import domain.model.dice.FaceValue;

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
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "roll");
		board.rollCup();
		Cup cup = Cup.getInstance();
		List<FaceValue> faceValList = cup.getFaceValues();
		for(int i=0; i<3;i++) {
			map.put("faceValue" + i, faceValList.get(i).toString());
		}
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}

	public void initializePlayers(HashMap<String, String> map) {
		ArrayList<Player> playerList = gameState.getPlayerList();
		int playerCount = Integer.parseInt(map.get("playerCount"));
		for(int i=0; i<playerCount; i++) {
			String username = map.get("player" + i + "Name");
			int ID = Integer.parseInt(map.get("player" + i + "ID"));
			Player p = new Player(username, ID, new Piece());
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

	public void move(boolean isLocalCommand) {
		board.movePiece(GameState.getInstance().getCurrentPlayer());
		System.out.println("Piece move Completed");
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

	public Player getLocalPlayer() {
		return localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}

	public void initializeLocalPlayer(String username, int ID) {
		Player player = new Player(username, ID, new Piece());
		localPlayer = player;
	}

	public void setDice(List<String> faceValueStrings) {
		List<FaceValue> faceValues = new ArrayList<FaceValue>();
		for(String str : faceValueStrings) {
			faceValues.add(FaceValue.valueOf(str));
		}
		Cup.getInstance().setFaceValues(faceValues);
	}
}
