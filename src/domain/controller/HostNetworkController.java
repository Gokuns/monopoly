package domain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//import java.util.Observable;
//import java.util.Observer;

import com.google.gson.Gson;

import domain.model.GameState;
import domain.model.GameStateListener;
import domain.model.Piece;
import domain.model.Player;
import domain.network.HostNetwork;
import domain.network.SocketReader;

public class HostNetworkController implements NetworkController, GameStateListener{
	private HostNetwork network;
	private List<NetworkControllerListener> listeners;
	private List<SocketReader> socketReaders;
	private GameController gameController = GameController.getInstance();
	private GameState gameState = GameState.getInstance();

	private int connectionCount;
	private Gson gson;

	public HostNetworkController(String port) {
		super();
		gameState.addNetworkListener(this);
		gson = new Gson();
		listeners = Collections.synchronizedList(
				new ArrayList<NetworkControllerListener>());
		socketReaders = Collections.synchronizedList(
				new ArrayList<SocketReader>());
		connectionCount = 0;
		network = new HostNetwork(port, this);
		new Thread(network).start();
	}

	public void newConnection(Socket socket) {
		connectionCount++;
		SocketReader socketReader = new SocketReader(socket, this);
		socketReaders.add(socketReader);
		new Thread(socketReader).start();
	}
	
	public void addNetworkControllerListener(NetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void handleMessage(HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "newConnection":
			System.out.println("Connection received.");
			String username = map.get("username");
			int ID = connectionCount;
			Player client = new Player(username, ID, new Piece());
			gameState.getPlayerList().add(client);
			map.put("connectionCount", Integer.toString(connectionCount));
			publishToListeners(map);
			break;
		case "roll":
			gameState.publishToUIListeners(map);
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
		return connectionCount;
	}
	
	public void sendMessageToPlayers(HashMap<String, String> map) {
		for  (Socket s : network.getSocketList()) {
			try {
				String json = gson.toJson(map);
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println(json);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void gameStarted(String playerName) {
		Player localPlayer = new Player(playerName, 0, new Piece());
		gameController.setLocalPlayer(localPlayer);
		gameState.setCurrentPlayer(localPlayer);
		ArrayList<Player> playerList = gameState.getPlayerList();
		playerList.add(localPlayer);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "gameStarted");
		map.put("currentPlayer", localPlayer.getName());
		map.put("playerCount", Integer.toString(connectionCount+1));
		map.put("player"+0+"Name", localPlayer.getName());
		map.put("player"+0+"ID", Integer.toString(localPlayer.getID()));
		for(int i = 1; i < connectionCount + 1; i++) {
			Player p = playerList.get(i);
			map.put("player"+i+"Name", p.getName());
			map.put("player"+i+"ID", Integer.toString(p.getID()));
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
