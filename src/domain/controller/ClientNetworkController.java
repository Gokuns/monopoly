package domain.controller;

import java.util.ArrayList;
import java.util.HashMap;

import domain.network.ClientNetwork;
import domain.network.SocketReader;

public class ClientNetworkController implements NetworkEventPublisher{
	private ClientNetwork network;
	private ArrayList<ClientNetworkControllerListener> listeners;
	
	private SocketReader socketReader;
	
	
	public ClientNetworkController() {
		listeners = new ArrayList<ClientNetworkControllerListener>();
	}
	
	public void initializeClientNetwork(String IP, String port) {
		network = new ClientNetwork(IP, port);
		socketReader = new SocketReader(network.getSocket(), this);
		new Thread(socketReader).start();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "connectedToHost");
		publishNetworkEvent(map);
	}
	
	public void addClientNetworkControllerListener(ClientNetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void publishNetworkEvent(HashMap<String, String> map) {
		for (ClientNetworkControllerListener listener : listeners) {
			listener.onNetworkEvent(this, map);
		}
	}
}
