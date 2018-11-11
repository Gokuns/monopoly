package domain.controller;

import java.util.ArrayList;

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
