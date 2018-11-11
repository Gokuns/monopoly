package domain.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import domain.controller.ClientNetworkController;

public class SocketReader implements Runnable{
	InputStream inputStream;

	public SocketReader(Socket socket) {
		super();
		try {
			this.inputStream = socket.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	public void run() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				if(line.equals("gameStarted")) {
					ClientNetworkController
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
