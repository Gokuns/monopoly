package domain.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.google.gson.JsonObject;

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

	/**
	 * Used for sending a message to all clients.
	 * Converts the given HashMap input into a json string.
	 * Then, writes this string into the output streams of all client sockets.
	 * @modifies The input map
	 * @param map The String to String HashMap containing the message
	 */
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

	@Override
	public void sendLoadData(JsonObject loadData) {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(loadData);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
