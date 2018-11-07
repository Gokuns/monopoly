package controller;

import network.HostNetwork;

public class HostNetworkController {
	private HostNetwork network;
	private MenuController menuController;

	public HostNetworkController(MenuController menuController) {
		super();
		this.menuController = menuController;
		network = new HostNetwork();
		new Thread(network).start();
	}
	
}
