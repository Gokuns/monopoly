package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class HostNetwork extends Thread{
	ServerSocket serverSocket;
	protected final int port = 9999;
	
	public HostNetwork() {
		try {
			serverSocket = new ServerSocket(port);
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				serverSocket.accept();
				// TODO handle connections
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}