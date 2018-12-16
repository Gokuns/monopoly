package domain.network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import domain.controller.NetworkController;

public class HostNetwork extends Network implements Runnable{
	private ServerSocket serverSocket;
	private List<Socket> socketList = Collections.synchronizedList(
			new ArrayList<Socket>());

	private String lastSenderIP = "";

	public HostNetwork(String port, NetworkController hostNetworkController) {
		super(hostNetworkController);
		try {
			serverSocket = new ServerSocket(Integer.parseInt(port));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				socketList.add(socket);
				SocketReader socketReader = new SocketReader(socket, this);
				new Thread(socketReader).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Used for sending a message to all clients.
	 * Converts the given HashMap input into a json string.
	 * Then, writes this string into the output streams of all client sockets.
	 * @param map
	 */
	public void sendMessageToPlayers(HashMap<String, String> map) {
		for  (Socket s : socketList) {
			try {
				map.put("source", s.getLocalAddress().getHostAddress());
				String json = gson.toJson(map);
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println(json);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Used for relaying a message sent by a client to the other clients.
	 * Converts the given HashMap input into a json string.
	 * Then, writes this string into the output streams of all client sockets, 
	 * except for the player who sent the message in the first place.
	 * @param map
	 */
	public void relayMessageToPlayers(HashMap<String, String> map) {
		for  (Socket s : socketList) {
			try {
				if(!s.getInetAddress().getHostAddress().equals(lastSenderIP)) {
					map.put("source", s.getLocalAddress().getHostAddress());
					String json = gson.toJson(map);
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					out.println(json);
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Used for replying to the latest message received.
	 * Converts the given HashMap input into a json string.
	 * Then, writes this string into the output stream of the last sender.
	 * The last sender is determined by IP checking.
	 * @param map
	 */
	public void sendMessageToLastSender(HashMap<String, String> map) {
		for  (Socket s : socketList) {
			try {
				if(s.getInetAddress().getHostAddress().equals(lastSenderIP)) {
					map.put("source", s.getLocalAddress().getHostAddress());
					String json = gson.toJson(map);
					PrintWriter out = new PrintWriter(s.getOutputStream(), true);
					out.println(json);
					out.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getConnectionCount() {
		return socketList.size();
	}

	@Override
	public void handleMessage(HashMap<String, String> map) {
		lastSenderIP = map.get("source");
		super.handleMessage(map);
	}
}