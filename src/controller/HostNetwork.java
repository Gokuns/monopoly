package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class HostNetwork{
	ArrayList<ServerSocket> serverSockets;
	protected final int port = 9999;
	private int connections;
	
	public HostNetwork() {
		connections = 0;
		this.serverSockets = new ArrayList<ServerSocket>();
		try {
			serverSockets.add(new ServerSocket(port));
			connections++;
			acceptConnections();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void acceptConnections() throws IOException {
		serverSockets.get(connections - 1).accept();
	}
	
}
