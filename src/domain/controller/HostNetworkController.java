package domain.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.network.HostNetwork;

public class HostNetworkController implements Observer, NetworkEventPublisher{
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
	
	public void sendToClients(String command) {
		for  (Socket s : network.getConnections()) {
			try {
				System.out.println("Sending #" + command + "#");
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.write(command + "\n");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
