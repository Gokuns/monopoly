package domain.controller;

public interface NetworkEventPublisher {
	public void publishNetworkEvent(String eventName);
}
