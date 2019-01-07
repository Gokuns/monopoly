package domain.controller;

import java.util.HashMap;

import com.google.gson.JsonObject;

import domain.network.Network;

public abstract class NetworkController {
	public void handleMessage(HashMap<String, String> map) {};
	public void addNetworkControllerListener(NetworkControllerListener listener) {};
	public void sendMessageToPlayers(HashMap<String, String> map) {};
	public void publishToListeners(HashMap<String, String> map) {};
	
	public void handleLoadData(JsonObject loadData) {};
	public void distributeLoadData(JsonObject loadData) {};
}
