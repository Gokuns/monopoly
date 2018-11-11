package domain.controller;

import java.util.HashMap;

public interface NetworkController {
	public void publishNetworkEvent(HashMap<String, String>	map);
	public void addNetworkControllerListener(NetworkControllerListener listener);
	public void sendMessageToPlayers(HashMap<String, String> map);
}
