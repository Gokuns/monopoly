package domain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.google.gson.Gson;

import domain.network.HostNetwork;
import domain.network.SocketReader;

public class HostNetworkController implements Observer, NetworkController{
	private HostNetwork network;
	private List<NetworkControllerListener> listeners;
	private List<SocketReader> socketReaders;

	private int connectionCount;
	private Gson gson;

	public HostNetworkController(String port) {
		super();
		gson = new Gson();
		listeners = Collections.synchronizedList(
				new ArrayList<NetworkControllerListener>());
		socketReaders = Collections.synchronizedList(
				new ArrayList<SocketReader>());
		connectionCount = 0;
		network = new HostNetwork(port);
		network.addObserver(this);
		new Thread(network).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		connectionCount++;
		HashMap<String, String> map = new HashMap<String, String>();
		Socket socket = (Socket)arg;
		SocketReader socketReader = new SocketReader(socket, this);
		socketReaders.add(socketReader);
		new Thread(socketReader).start();
		map.put("type", "newConnection");
		map.put("connectionCount", connectionCount+"");
		publishNetworkEvent(map);
	}
	
	public void addNetworkControllerListener(NetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void publishNetworkEvent(HashMap<String, String> map) {
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
	
	public void gameStarted() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "gameStarted");
		sendMessageToPlayers(map);
	}
}
