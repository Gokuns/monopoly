package domain.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;
//import com.google.gson.JsonObject;

//import domain.controller.ClientNetworkController;
import domain.controller.NetworkController;

public class SocketReader implements Runnable{
	private InputStream inputStream;
	private NetworkController networkController;
	private Socket socket;
	private Gson gson;

	public SocketReader(Socket socket, NetworkController networkController) {
		super();
		try {
			this.gson = new Gson();
			this.networkController = networkController;
			this.inputStream = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while (true) {
				line = bufferedReader.readLine();
				if(line != null) {
					@SuppressWarnings("unchecked")
					HashMap<String, String> map = gson.fromJson(line, HashMap.class);
					System.out.println(map.get("type"));
					networkController.handleMessage(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
