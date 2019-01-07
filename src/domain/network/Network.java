package domain.network;

import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import domain.controller.NetworkController;
import domain.model.gameHandler.GameState;
import domain.model.gameHandler.GameStateListener;

public abstract class Network  implements GameStateListener{
	protected final Gson gson;
	private final NetworkController networkController;

	protected Network(NetworkController networkController) {
		gson = new Gson();
		this.networkController = networkController;
		GameState.getInstance().addNetworkListener(this);
	}
	
	public void handleMessage(Socket source, HashMap<String, String> map){
		networkController.handleMessage(map);
	}
	
	@Override
	public void update(GameState source, HashMap<String, String> map) {
		sendMessageToPlayers(map);
	}

	protected abstract void sendMessageToPlayers(HashMap<String, String> map);

	public void handleLoadData(JsonObject loadData) {
		networkController.handleLoadData(loadData);
	}

	public abstract void sendLoadData(JsonObject loadData);
}
