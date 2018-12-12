package domain.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.model.GameState;
import domain.model.GameStateListener;
import domain.model.players.Player;
import domain.network.HostNetwork;

public class HostNetworkController extends NetworkController implements GameStateListener{
	private HostNetwork network;
	private List<NetworkControllerListener> listeners;
	private GameState gameState = GameState.getInstance();

	public HostNetworkController(String port, String username) {
		super();
		GameController.getInstance().initializeLocalPlayer(username, 0);
		GameState.getInstance().addPlayer(username, 0);
		gameState.addNetworkListener(this);
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
			map.put("connectionCount", Integer.toString(network.getConnectionCount()));
			publishToListeners(map);
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
		case "move":
			GameController.getInstance().move(false);
			relayMessageToPlayers(map);
		}
	}

	private void relayMessageToPlayers(HashMap<String, String> map) {
		network.relayMessageToPlayers(map);
	}

	public void publishToListeners(HashMap<String, String> map) {
		for (NetworkControllerListener listener : listeners) {
			listener.onNetworkEvent(this, map);
		}
	}

	public int getConnectionCount() {
		return network.getConnectionCount();
	}

	public void sendMessageToPlayers(HashMap<String, String> map) {
		network.sendMessageToPlayers(map);
	}

	public void gameStarted(String playerName) {
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
		GameState.getInstance().publishToUIListeners(map);
		GameState.getInstance().setCurrentPlayer(playerList.get(0));
		GameState.getInstance().setOrderedPlayerList(playerList);
		sendMessageToPlayers(map);
	}

	@Override
	public void update(GameState source, HashMap<String, String> map) {
		sendMessageToPlayers(map);
	}
}
