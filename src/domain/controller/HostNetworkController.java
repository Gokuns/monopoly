package domain.controller;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.model.GameState;
import domain.model.GameStateListener;
import domain.model.Piece;
import domain.model.Player;
import domain.network.HostNetwork;
import domain.network.SocketReader;

public class HostNetworkController extends NetworkController implements GameStateListener{
	private HostNetwork network;
	private List<NetworkControllerListener> listeners;
	public final GameController gameController = GameController.getInstance();
	private GameState gameState = GameState.getInstance();

	public HostNetworkController(String port) {
		super();
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
			GameController.getInstance().initializeLocalPlayer(username, ID);
			map.put("connectionCount", Integer.toString(network.getConnectionCount()));
			publishToListeners(map);
			break;
		case "roll":
			gameState.publishToUIListeners(map);
			sendMessageToPlayers(map);
			break;
		case "roll3":
			gameState.publishToUIListeners(map);
			sendMessageToPlayers(map);
			break;
		case "payHospitalBill":
			gameState.publishToUIListeners(map);
			sendMessageToPlayers(map);
			break;
		case "goToJail":
			gameState.publishToUIListeners(map);
			sendMessageToPlayers(map);
			break;
		case "endTurn":
			GameController.getInstance().endTurn(true);
			sendMessageToPlayers(map);
			break;
		}
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
		try {
			map.put("source", InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		network.sendMessageToPlayers(map);
	}
	
	public void gameStarted(String playerName) {
		Player localPlayer = new Player(playerName, 0, new Piece());
		GameController.getInstance().setLocalPlayer(localPlayer);
		GameState.getInstance().setCurrentPlayer(localPlayer);
		ArrayList<Player> playerList = gameState.getPlayerList();
		playerList.add(localPlayer);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "gameStarted");
		map.put("currentPlayer", localPlayer.getName());
		map.put("playerCount", Integer.toString(network.getConnectionCount() + 1));
		map.put("player"+0+"Name", localPlayer.getName());
		map.put("player"+0+"ID", Integer.toString(localPlayer.getID()));
		map.put("currentPlayerID", "0");
		for(int i = 0; i < network.getConnectionCount(); i++) {
			Player p = playerList.get(i);
			map.put("player"+(i+1)+"Name", p.getName());
			map.put("player"+(i+1)+"ID", Integer.toString(p.getID()));
		}
		gameState.setOrderedPlayerList(playerList);
		gameState.publishToUIListeners(map);
		sendMessageToPlayers(map);
	}

	@Override
	public void update(GameState source, HashMap<String, String> map) {
		sendMessageToPlayers(map);
	}
}
