package domain.network;

import domain.controller.ClientNetworkController;

public interface ClientNetworkControllerListener {
	public void onNetworkEvent(ClientNetworkController source, String eventName);
}
