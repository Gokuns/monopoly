package domain.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class HostNetwork extends Observable implements Runnable{
	ServerSocket serverSocket;
	
	protected int port;
	
	public HostNetwork(String port) {
		try {
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
				setChanged();
				notifyObservers(connection);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}