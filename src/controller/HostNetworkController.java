package controller;

import network.HostNetwork;

public class HostNetworkController {
	HostNetwork network;

	public HostNetworkController() {
		super();
		network = new HostNetwork();
		network.start();
	}
	
}
