package domain.controller;

import java.util.HashMap;

public interface NetworkControllerListener {
	public void onNetworkEvent(NetworkEventPublisher source, HashMap<String, String> map);
}
