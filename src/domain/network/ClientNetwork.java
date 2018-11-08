package domain.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientNetwork {
	private Socket socket;
	
	public ClientNetwork(String host, String port) {
		try {
			socket = new Socket(host, Integer.parseInt(port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
