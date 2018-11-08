package network;

import controller.HostNetworkController;

public interface HostNetworkControllerListener {
	public void onNetworkEvent(HostNetworkController source, String eventName);
}
