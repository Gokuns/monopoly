package domain.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import domain.controller.ClientNetworkController;
import domain.controller.NetworkController;

public class SocketReader implements Runnable{
	private InputStream inputStream;
	private NetworkController networkController;

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
					HashMap<String, String> map = gson.fromJson(line, HashMap.class);
					networkController.publishNetworkEvent(map);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
