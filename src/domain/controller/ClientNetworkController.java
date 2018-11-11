package domain.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.network.ClientNetwork;
import domain.network.SocketReader;

public class ClientNetworkController implements NetworkEventPublisher{
	private ClientNetwork network;
	private List<NetworkControllerListener> listeners;
	
	private SocketReader socketReader;
	
	
	public ClientNetworkController() {
		listeners = Collections.synchronizedList(
				new ArrayList<NetworkControllerListener>());
	}
	
	public void initializeClientNetwork(String IP, String port) {
		network = new ClientNetwork(IP, port);
		socketReader = new SocketReader(network.getSocket(), this);
		new Thread(socketReader).start();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "connectedToHost");
		publishNetworkEvent(map);
	}
	
	public void addNetworkControllerListener(NetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void publishNetworkEvent(HashMap<String, String> map) {
		for (int i = 0; i < listeners.size(); i++) {
			NetworkControllerListener listener = listeners.get(i);
			listener.onNetworkEvent(this, map);
		}
	}
}
