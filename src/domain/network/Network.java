package domain.network;

import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import domain.controller.NetworkController;

public abstract class Network {
	protected final Gson gson;
	private final NetworkController networkController;

	protected Network(NetworkController networkController) {
		gson = new Gson();
		this.networkController = networkController;
	}
	
	public void handleMessage(Socket source, HashMap<String, String> map){
		networkController.handleMessage(map);
	}
}
