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

	private static JButton hostGameButton;
	private static JButton joinAGameButton;
	private static MainMenuFrame menu;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new MainMenuFrame();
					hostGameButton = menu.getHostAGameButton();
					joinAGameButton = menu.getJoinAGameButton();
					menu.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton)e.getSource();
		if(source.equals(hostGameButton)) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						menu.setEnabled(false);
						HostGameFrame frame = new HostGameFrame();
						frame.setVisible(true);
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						frame.addWindowListener(new java.awt.event.WindowAdapter() {
						    @Override
						    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
						    	menu.setEnabled(true);
						    }
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else if(source.equals(joinAGameButton)) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						menu.setEnabled(false);
						JoinGameFrame frame = new JoinGameFrame();
						frame.setVisible(true);
						frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
						frame.addWindowListener(new java.awt.event.WindowAdapter() {
							@Override
							public void windowClosing(java.awt.event.WindowEvent windowEvent) {
								menu.setEnabled(true);
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

}

