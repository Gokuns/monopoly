package domain.controller;

import java.util.HashMap;

public interface ClientNetworkControllerListener {
	public void onNetworkEvent(ClientNetworkController source, HashMap<String, String> map);
}
