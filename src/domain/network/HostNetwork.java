package domain.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

public class HostNetwork extends Observable implements Runnable{
	private ServerSocket serverSocket;
	private ArrayList<Socket> connections;
	
	protected int port;
	
	public HostNetwork(String port) {
		try {
			connections = new ArrayList<Socket>();
			this.port = Integer.parseInt(port);
			serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				Socket connection = serverSocket.accept();
				connections.add(connection);
				setChanged();
				notifyObservers(connection);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Socket> getConnections() {
		return connections;
	}
}