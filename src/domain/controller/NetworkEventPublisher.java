package domain.controller;

import java.util.HashMap;

public interface NetworkEventPublisher {
	public void publishNetworkEvent(HashMap<String, String>	map);
	public void addNetworkControllerListener(NetworkControllerListener listener);
}
