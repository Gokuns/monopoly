package domain.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonObject;

import domain.model.gameHandler.GameState;
import domain.model.players.Player;
import domain.model.players.Bot.PlayerBot;
import domain.network.HostNetwork;

public class HostNetworkController extends NetworkController{
	private HostNetwork network;
	private List<NetworkControllerListener> listeners;
	private GameState gameState = GameState.getInstance();

	public HostNetworkController(String port, String username) {
		super();
		GameController.getInstance().initializeLocalPlayer(username, 0);
		GameState.getInstance().addPlayer(username, 0);
		listeners = Collections.synchronizedList(
				new ArrayList<NetworkControllerListener>());
		network = new HostNetwork(port, this);
		new Thread(network).start();
	}

	public void addNetworkControllerListener(NetworkControllerListener listener) {
		listeners.add(listener);
	}

	public void handleMessage(HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "newConnection":
			String username = map.get("username");
			int ID = network.getConnectionCount();
			GameState.getInstance().addPlayer(username, ID);
			if(network.getConnectionCount()==1) {
//				GameState.getInstance().getPlayerList().add(new PlayerBot("Bot",-1,1));
			}
			map.put("connectionCount", Integer.toString(network.getConnectionCount()));
			publishToListeners(map);
			sendMessageToLastSender(map);
			break;
		case "roll":
			List<String> faceValues = new ArrayList<String>();
			for(int i=0; i<3; i++) {
				faceValues.add(map.get("faceValue" + i));
			}
			GameController.getInstance().setDice(faceValues);
			GameState.getInstance().publishToUIListeners(map);
			relayMessageToPlayers(map);
			break;
		case "roll3":
			gameState.publishToUIListeners(map);
			relayMessageToPlayers(map);
			break;
		case "payHospitalBill":
			gameState.publishToUIListeners(map);
			relayMessageToPlayers(map);
			break;
		case "goToJail":
			gameState.publishToUIListeners(map);
			relayMessageToPlayers(map);
			break;
		case "endTurn":
			GameController.getInstance().endTurn(false);
			relayMessageToPlayers(map);
			break;
		case "load":
			gameState.publishToUIListeners(map);
			relayMessageToPlayers(map);
			break;
		case "move":
			GameController.getInstance().move(false);
			relayMessageToPlayers(map);
			break;
		case "buy":
			GameController.getInstance().buyProperty(false);
			relayMessageToPlayers(map);
		}
	}
	
	public void handleLoadData(JsonObject loadData) {
		try {
			network.relayLoadData(loadData);
			GameController.getInstance().loadReceivedGame(loadData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void distributeLoadData(JsonObject loadData) {
		network.sendLoadData(loadData);
	}

	public void sendMessageToPlayers(HashMap<String, String> map) {
		network.sendMessageToPlayers(map);
	}

	private void relayMessageToPlayers(HashMap<String, String> map) {
		network.relayMessageToPlayers(map);
	}
	
	private void sendMessageToLastSender(HashMap<String, String> map) {
		network.sendMessageToLastSender(map);
	}

	public void publishToListeners(HashMap<String, String> map) {
		for (NetworkControllerListener listener : listeners) {
			listener.onNetworkEvent(this, map);
		}
	}

	public int getConnectionCount() {
		return network.getConnectionCount();
	}

	/**
	 * Does the necessary tasks to move from awaiting new players to playing the game.
	 * Sets the HostNetworkController instance as the networkController of the GameController.
	 * Creates a message HashMap that contains information about the connected players.
	 * Tells the gameState to publish this map to client players and to the UI.
	 * @param playerName The String containing the username for the host player.
	 */
	public void gameStarted(String playerName, int botType) {
		ArrayList<Player> playerList = GameState.getInstance().getPlayerList();
		GameController.getInstance().setNetworkController(this);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "gameStarted");
		map.put("currentPlayer", playerName);
		map.put("playerCount", Integer.toString(network.getConnectionCount() + 1));
		map.put("currentPlayerID", "0");
		for(int i = 0; i <= network.getConnectionCount(); i++) {
			Player p = playerList.get(i);
			map.put("player"+(i)+"Name", p.getName());
			map.put("player"+(i)+"ID", Integer.toString(p.getID()));
		}
		map.put("botType", "0");
		System.out.println("size: " +playerList.size());
		String botName = "Bot";
		int botId = playerList.size();
		PlayerBot pb = new PlayerBot(botName,botId, botType);
		GameController.getInstance().setBot(pb);
		playerList.add(pb);
		HashMap<String, String> botMap = new HashMap<String, String>();
		botMap.put("type", "bot");
		botMap.put("name", botName);
		botMap.put("id", botId+"");
		botMap.put("botType", botType+"");
		network.sendMessageToPlayers(botMap);
		System.out.println("size: " +playerList.size());
		GameState.getInstance().setCurrentPlayer(playerList.get(0));
		GameState.getInstance().setOrderedPlayerList(playerList);
		GameState.getInstance().publishToUIListeners(map);
		network.sendMessageToPlayers(map);
	}

}
