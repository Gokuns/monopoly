package domain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import domain.model.GameState;
import domain.model.GameStateListener;
import domain.model.Piece;
import domain.model.Player;
import domain.network.ClientNetwork;
import domain.network.SocketReader;

public class ClientNetworkController implements NetworkController, GameStateListener{
	private ClientNetwork network;
	private List<NetworkControllerListener> listeners;
	
	private Gson gson = new Gson();
	private GameController gameController = GameController.getInstance();
	private GameState gameState = GameState.getInstance();
	private SocketReader socketReader;
	
	
	public ClientNetworkController() {
		listeners = Collections.synchronizedList(
				new ArrayList<NetworkControllerListener>());
	}
	
	public void initializeClientNetwork(String IP, String port, String username) {
		network = new ClientNetwork(IP, port);
		socketReader = new SocketReader(network.getSocket(), this);
		new Thread(socketReader).start();
		gameState.addNetworkListener(this);
		
		Player localPlayer = new Player(username, 1, new Piece());
		gameController.setLocalPlayer(localPlayer);
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
		try {
			String json = gson.toJson(map);
			Socket socket = network.getSocket();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameState source, HashMap<String, String> map) {
		sendMessageToPlayers(map);
	}
}
