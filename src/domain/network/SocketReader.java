package domain.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SocketReader implements Runnable{
	private InputStream inputStream;
	private Network network;
	private Socket socket;
	private Gson gson;

	public SocketReader(Socket socket, Network network) {
		super();
		try {
			this.gson = new Gson();
			this.network = network;
			this.inputStream = socket.getInputStream();
			this.socket = socket;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			boolean expectingLoadData = false;
			while (true) {
				line = bufferedReader.readLine();
				if(line != null) {
					if(!expectingLoadData) {
						HashMap<String, String> map = gson.fromJson(line, HashMap.class);
						if(map.get("type").equals("loadDataIncoming")) {
							expectingLoadData = true;
						}
						network.handleMessage(socket, map);
					} else {
						JsonObject loadData = gson.fromJson(line, JsonObject.class);
						network.handleLoadData(loadData);
						expectingLoadData = false;
					}
				}
			}
		} catch (IOException e) {

		}
	}

}
