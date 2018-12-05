package domain.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.model.GameState;
import domain.model.GameStateListener;
import domain.network.ClientNetwork;

public class ClientNetworkController extends NetworkController implements GameStateListener{
	private ClientNetwork network;
	private List<NetworkControllerListener> listeners;
	
	private GameController gameController = GameController.getInstance();
	private GameState gameState = GameState.getInstance();
	
	
	public ClientNetworkController() {
		listeners = Collections.synchronizedList(
				new ArrayList<NetworkControllerListener>());
	}
	
	public void initializeClientNetwork(String IP, String port, String username) {
		network = new ClientNetwork(this, IP, port);
		GameState.getInstance().addNetworkListener(this);
		GameController.getInstance().initializeLocalPlayer(username, 1);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "newConnection");
		map.put("username", username);
		sendMessageToPlayers(map);
		publishToListeners(map);
	}
	
	public void addNetworkControllerListener(NetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void handleMessage(HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "gameStarted":
			publishToListeners(map);
			gameController.initializePlayers(map);
			break;
		case "roll":
			gameState.publishToUIListeners(map);
			break;
		case "roll3":
			gameState.publishToUIListeners(map);
			break;
		case "payHospitalBill":
			gameState.publishToUIListeners(map);
			break;
		case "goToJail":
			gameState.publishToUIListeners(map);
			break;
		case "endTurn":
			gameState.publishToUIListeners(map);
			gameController.endTurn(false);
			break;
		}
	}
		
	public void publishToListeners(HashMap<String, String> map) {
		for (int i = 0; i < listeners.size(); i++) {
			NetworkControllerListener listener = listeners.get(i);
			listener.onNetworkEvent(this, map);
		}
	}

	@Override
	public void sendMessageToPlayers(HashMap<String, String> map) {
		network.sendMessageToPlayers(map);
	}

	@Override
	public void update(GameState source, HashMap<String, String> map) {
		sendMessageToPlayers(map);
	}
}
