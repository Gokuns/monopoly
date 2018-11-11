package domain.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import domain.controller.ClientNetworkController;
import domain.controller.NetworkEventPublisher;

public class SocketReader implements Runnable{
	InputStream inputStream;
	NetworkEventPublisher networkController;

	public SocketReader(Socket socket, NetworkEventPublisher networkController) {
		super();
		try {
			this.networkController = networkController;
			this.inputStream = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public void run() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while (true) {
				line = bufferedReader.readLine();
				networkController.publishNetworkEvent(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
