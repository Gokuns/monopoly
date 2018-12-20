package domain.controller;

import java.util.HashMap;

public abstract class NetworkController {
	public void handleMessage(HashMap<String, String> map) {};
	public void addNetworkControllerListener(NetworkControllerListener listener) {};
	public void sendMessageToPlayers(HashMap<String, String> map) {};
	public void publishToListeners(HashMap<String, String> map) {};
}
