package domain.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import domain.controller.NetworkController;

public class ClientNetwork extends Network{
	private Socket socket;
	private SocketReader socketReader;
	
	public ClientNetwork(NetworkController networkController, String host, String port) {
		super(networkController);
		try {
			socket = new Socket(host, Integer.parseInt(port));
			socketReader = new SocketReader(socket, this);
			new Thread(socketReader).start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToPlayers(HashMap<String, String> map) {
		try {
			map.put("source", socket.getLocalAddress().getHostAddress());
			String json = gson.toJson(map);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
