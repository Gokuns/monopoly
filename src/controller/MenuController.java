package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import network.ClientNetwork;
import network.HostNetwork;
import view.MainMenuFrame;

public class MenuController implements ActionListener{
	
	private HostNetworkController hostNetworkController;
	private ClientNetworkController clientNetworkController;
	
	private JButton hostGameButton;
	private JButton joinAGameButton;
	private JButton joinWithIPButton;
	private MainMenuFrame menu;

	public MenuController(MainMenuFrame menu){
		this.menu = menu;
		hostGameButton = menu.getHostAGameButton();
		joinAGameButton = menu.getJoinAGameButton();
		hostGameButton.addActionListener(this);
		joinAGameButton.addActionListener(this);
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		if(source.equals(hostGameButton)) {
			menu.initHostAGameFrame();
			hostNetworkController = new HostNetworkController();
		}
		else if(source.equals(joinAGameButton)) {
			menu.initJoinAGameFrame();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			joinWithIPButton = menu.getJoinWithIPButton();
			joinWithIPButton.addActionListener(this);
		}
		else if(source.equals(joinWithIPButton)) {
			String IP = menu.getIP();
			clientNetworkController = new ClientNetworkController(IP);
		}
	}
}