package ui;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import domain.controller.HostNetworkController;
import domain.controller.NetworkControllerListener;
import domain.controller.NetworkController;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class HostGameFrame extends JFrame implements NetworkControllerListener{

	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	
	private JLabel portTextLabel;
	private JLabel usernameTextLabel;
	private JTextField portTextField;
	private JTextField usernameTextField;
	
	private JButton hostGameButton;
	
	private HostNetworkController hostNetworkController;
	private MainMenuFrame mainMenu;

	/**
	 * Create the frame.
	 */
	public HostGameFrame(MainMenuFrame mainMenu) {
		setBounds(new Rectangle(100, 100, 450, 400));
		setResizable(false);
		this.mainMenu = mainMenu;
		setTitle("Host Game");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		hostGameButton = new JButton("Host Game");
		hostGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hostGameButton.getText()=="Host Game") {
					hostNetworkController = new HostNetworkController(portTextField.getText());
					hostNetworkController.addNetworkControllerListener(HostGameFrame.this);
					
					portTextLabel.setText("Waiting for players");
					portTextField.setEditable(false);
					usernameTextLabel.setText("0 players connected");
					usernameTextField.setEditable(false);
					hostGameButton.setText("Start Game");
					hostGameButton.setEnabled(false);
				} else if(hostGameButton.getText().equals("Start Game")) {
					GameFrame gameFrame = new GameFrame(hostNetworkController);
					mainMenu.dispose();
					dispose();
					gameFrame.setVisible(true);
					hostNetworkController.gameStarted(usernameTextField.getText());
				}
			}
		});
		hostGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostGameButton.setBounds(145, 298, 150, 45);
		contentPane.add(hostGameButton);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(350, 70, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(45, 15, 350, 70);
			contentPane.add(monopolyLogoPanel);
			
			portTextLabel = new JLabel("Please enter a port number");
			portTextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			portTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
			portTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			portTextLabel.setBounds(12, 98, 420, 30);
			contentPane.add(portTextLabel);
			
			portTextField = new JTextField();
			portTextField.setText("9999");
			portTextField.setBounds(145, 141, 150, 44);
			contentPane.add(portTextField);
			portTextField.setColumns(10);
			
			usernameTextField = new JTextField();
			usernameTextField.setText("HostPlayer");
			usernameTextField.setColumns(10);
			usernameTextField.setBounds(145, 241, 150, 44);
			contentPane.add(usernameTextField);
			
			usernameTextLabel = new JLabel("Please enter your name");
			usernameTextLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			usernameTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
			usernameTextLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			usernameTextLabel.setBounds(12, 198, 420, 30);
			contentPane.add(usernameTextLabel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}

	public void clientConnected(int connectionCount) {
		usernameTextLabel.setText(connectionCount + " players connected.");
		if(connectionCount>0) {
			getStartGameButton().setEnabled(true);
		}
	}

	public JButton getStartGameButton() {
		return hostGameButton;
	}

	@Override
	public void onNetworkEvent(NetworkController source, HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "newConnection":
			int connectionCount = Integer.parseInt(map.get("connectionCount"));
			clientConnected(connectionCount);
			break;
		}
	}
}
