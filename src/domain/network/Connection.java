package domain.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection extends Thread{
	InputStream is;
	OutputStream os;
	Socket sc;
	
	public Connection(Socket sc) {
		super();
		this.sc = sc;
		try {
			this.is = sc.getInputStream();
			this.os = sc.getOutputStream();			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
