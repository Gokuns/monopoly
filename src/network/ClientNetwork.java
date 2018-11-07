package network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetwork {
	private Socket socket;
	private final int port = 9999;
	
	public ClientNetwork(String host) {
		try {
			socket = new Socket(host, port);
			System.out.println("connected to host");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}