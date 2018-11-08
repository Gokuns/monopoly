package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import network.HostNetwork;
import network.HostNetworkControllerListener;

public class HostNetworkController implements Observer{
	private HostNetwork network;
	private ArrayList<HostNetworkControllerListener> listeners;
	
	private int connectionCount;

	public HostNetworkController(String port) {
		super();
		listeners = new ArrayList<HostNetworkControllerListener>();
		connectionCount = 0;
		network = new HostNetwork(port);
		network.addObserver(this);
		new Thread(network).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		connectionCount++;
		publishNetworkEvent("newConnection");
	}
	
	public void addHostNetworkControllerListener(HostNetworkControllerListener listener) {
		listeners.add(listener);
	}
	
	public void publishNetworkEvent(String eventName) {
		for (HostNetworkControllerListener listener : listeners) {
			listener.onNetworkEvent(this, eventName);
		}
	}

	public int getConnectionCount() {
		return connectionCount;
	}
}
