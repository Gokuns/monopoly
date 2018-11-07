package controller;

import network.ClientNetwork;

public class ClientNetworkController {
	private ClientNetwork network;
	private MenuController menuController;
	
	public ClientNetworkController(MenuController menuController) {
		this.menuController = menuController;
	}
	
	public void initializeClientNetwork(String IP) {
		network = new ClientNetwork(IP);
	}
}
