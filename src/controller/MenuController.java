package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import network.ClientNetwork;
import network.HostNetwork;
import view.MainMenuFrame;

public class MenuController implements ActionListener{

	private JButton hostGameButton;
	private JButton joinAGameButton;
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
			HostNetwork hn = new HostNetwork();
			hn.start();
		}
		else if(source.equals(joinAGameButton)) {
			menu.initJoinAGameFrame();
			ClientNetwork cn = new ClientNetwork("localhost");
		}
	}
}


