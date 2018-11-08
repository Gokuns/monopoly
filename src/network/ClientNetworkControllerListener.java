package network;

import controller.ClientNetworkController;

public interface ClientNetworkControllerListener {
	public void onNetworkEvent(ClientNetworkController source, String eventName);
}
