package domain.network;

import domain.controller.HostNetworkController;

public interface HostNetworkControllerListener {
	public void onNetworkEvent(HostNetworkController source, String eventName);
}
