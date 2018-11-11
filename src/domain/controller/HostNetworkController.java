package domain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import com.google.gson.Gson;

import domain.network.HostNetwork;

public class HostNetworkController implements Observer, NetworkEventPublisher{
	private HostNetwork network;
	private ArrayList<HostNetworkControllerListener> listeners;

	private int connectionCount;
	private Gson gson;

	public HostNetworkController(String port) {
		super();
		gson = new Gson();
		listeners = new ArrayList<HostNetworkControllerListener>();
		connectionCount = 0;
		network = new HostNetwork(port);
		network.addObserver(this);
		new Thread(network).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		connectionCount++;
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "newConnection");
		publishNetworkEvent(map);
	}
	
	public void addHostNetworkControllerListener(HostNetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void publishNetworkEvent(HashMap<String, String> map) {
		for (HostNetworkControllerListener listener : listeners) {
			listener.onNetworkEvent(this, map);
		}
	}

	public int getConnectionCount() {
		return connectionCount;
	}
	
	public void sendToClients(String message) {
		for  (Socket s : network.getConnections()) {
			try {
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println(message);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void gameStarted() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "gameStarted");
		String json = gson.toJson(map);
		sendToClients(json);
	}
}
