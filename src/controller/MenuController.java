package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.WindowConstants;

import view.HostGameFrame;
import view.JoinGameFrame;
import view.MainMenuFrame;

public class MenuController implements ActionListener{

	private JButton hostGameButton;
	private JButton joinAGameButton;
	private JButton joinWithIPButton;
	private MainMenuFrame menu;

	public MenuController(MainMenuFrame menu){
		this.menu = menu;
		hostGameButton = menu.getHostAGameButton();
		joinAGameButton = menu.getJoinAGameButton();
		hostGameButton.addActionListener(MenuController.this);
		joinAGameButton.addActionListener(MenuController.this);
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		if(source.equals(hostGameButton)) {
			menu.initHostAGameFrame();
		}
		else if(source.equals(joinAGameButton)) {
			menu.initJoinAGameFrame();
		}
	}
}


