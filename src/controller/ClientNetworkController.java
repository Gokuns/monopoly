package controller;

import network.ClientNetwork;

public class ClientNetworkController {
	private ClientNetwork network;
	
	public ClientNetworkController(String IP) {
		network = new ClientNetwork(IP);
	}
}
