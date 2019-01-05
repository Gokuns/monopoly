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
/**
 * @overview The Network that holds the host's attributes
 * @author Onat
 *
 */
public class HostNetwork extends Network implements Runnable{
	private ServerSocket serverSocket;
	private List<Socket> socketList = Collections.synchronizedList(
			new ArrayList<Socket>());

	private Socket lastSender;

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
	 * @modifies The input map
	 * @param map The String to String HashMap containing the message
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
	 * @requires The field lastSender holds the socket to the sender of the latest message.
	 * @modifies The input map
	 * @param map The String to String HashMap containing the message
	 */
	public void relayMessageToPlayers(HashMap<String, String> map) {
		for  (Socket s : socketList) {
			try {
				if(!s.equals(lastSender)) {
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
	 * @requires The field lastSender holds the socket to the sender of the latest message.
	 * @modifies The input map
	 * @param map The String to String HashMap containing the message
	 */
	public void sendMessageToLastSender(HashMap<String, String> map) {
		try {
			map.put("source", lastSender.getLocalAddress().getHostAddress());
			String json = gson.toJson(map);
			PrintWriter out = new PrintWriter(lastSender.getOutputStream(), true);
			out.println(json);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getConnectionCount() {
		return socketList.size();
	}

	@Override
	public void handleMessage(Socket source, HashMap<String, String> map) {
		lastSender = source;
		super.handleMessage(source, map);
	}
}