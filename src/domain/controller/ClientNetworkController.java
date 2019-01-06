package domain.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.model.gameHandler.GameState;
import domain.model.gameHandler.GameStateListener;
import domain.model.players.Bot.PlayerBot;
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
			GameController.getInstance().setNetworkController(this);
			gameController.initializePlayers(map);
			break;
		case "roll":
			List<String> faceValues = new ArrayList<String>();
			for(int i=0; i<3; i++) {
				faceValues.add(map.get("faceValue" + i));
			}
			GameController.getInstance().setDice(faceValues);
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
			gameController.endTurn(false);
			break;
		case "move":
			gameController.move(false);
			break;
		case "load":
			gameState.publishToUIListeners(map);
			break;
		case "bot":
			PlayerBot pb = new PlayerBot(map.get("name"),Integer.parseInt(map.get("id")),Integer.parseInt(map.get("botType")));
			gameController.setBot(pb);
			break;
		case "newConnection":
			GameController.getInstance().setLocalPlayerID(
					Integer.parseInt(map.get("connectionCount")));
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
