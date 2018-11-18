package domain.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;

import domain.controller.HostNetworkController;

public class HostNetwork implements Runnable{
	private ServerSocket serverSocket;
	private ArrayList<Socket> socketList;
	private HostNetworkController hostNetworkController;
	
	protected int port;
	
	public HostNetwork(String port, HostNetworkController hostNetworkController) {
		try {
			this.hostNetworkController = hostNetworkController;
			socketList = new ArrayList<Socket>();
			this.port = Integer.parseInt(port);
			serverSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				socketList.add(socket);
				hostNetworkController.newConnection(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Socket> getSocketList() {
		return socketList;
	}
}