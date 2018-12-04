package domain.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import domain.controller.NetworkController;

public class ClientNetwork extends Network{
	private Socket socket;
	
	public ClientNetwork(NetworkController networkController, String host, String port) {
		super(networkController);
		try {
			socket = new Socket(host, Integer.parseInt(port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToPlayers(HashMap<String, String> map) {
		try {
			String json = gson.toJson(map);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
