package domain.controller;

public interface ClientNetworkControllerListener {
	public void onNetworkEvent(ClientNetworkController source, String eventName);
}
