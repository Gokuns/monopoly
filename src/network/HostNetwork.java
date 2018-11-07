package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observer;
import java.util.Observable;

public class HostNetwork extends Observable implements Runnable{
	ServerSocket serverSocket;
	
	protected final int port = 9999;
	
	public HostNetwork() {
		try {
			serverSocket = new ServerSocket(port);
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
				// TODO handle connections
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}