package domain.controller;

import java.util.HashMap;

public interface NetworkControllerListener {
	public void onNetworkEvent(NetworkController source, HashMap<String, String> map);
}
