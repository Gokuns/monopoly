package domain.controller;

import java.util.HashMap;

public interface HostNetworkControllerListener {
	public void onNetworkEvent(HostNetworkController source, HashMap<String, String> map);
}
