package domain;

import java.awt.EventQueue;

import ui.MainMenuFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame menu = new MainMenuFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
