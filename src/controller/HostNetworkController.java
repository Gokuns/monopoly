package controller;

import java.util.Observable;
import java.util.Observer;

import network.HostNetwork;

public class HostNetworkController implements Observer{
	private HostNetwork network;
	private MenuController menuController;
	
	private int connectionCount;

	public HostNetworkController(MenuController menuController) {
		super();
		connectionCount = 0;
		this.menuController = menuController;
		network = new HostNetwork();
		network.addObserver(this);
		new Thread(network).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		connectionCount++;
		menuController.clientJoinedToHost(connectionCount);
	}
	
}
