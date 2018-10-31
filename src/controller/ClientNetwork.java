package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetwork {
	private Socket socket;
	private final int port = 9999;
	
	public ClientNetwork(String host) {
		try {
			socket = new Socket(host, port);
			if(socket.isConnected()) {
				System.out.println("Connected");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
