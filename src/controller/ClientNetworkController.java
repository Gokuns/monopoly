package controller;

import java.util.ArrayList;

import network.ClientNetwork;
import network.ClientNetworkControllerListener;

public class ClientNetworkController {
	private ClientNetwork network;
	private ArrayList<ClientNetworkControllerListener> listeners;
	
	
	public ClientNetworkController() {
		listeners = new ArrayList<ClientNetworkControllerListener>();
	}
	
	public void initializeClientNetwork(String IP, String port) {
		network = new ClientNetwork(IP, port);
		publishNetworkEvent("connectedToHost");
	}
	
	public void addClientNetworkControllerListener(ClientNetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void publishNetworkEvent(String eventName) {
		for (ClientNetworkControllerListener listener : listeners) {
			listener.onNetworkEvent(this, eventName);
		}
	}
}
