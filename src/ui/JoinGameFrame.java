package ui;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import domain.controller.ClientNetworkController;
import domain.controller.GameController;
import domain.controller.NetworkControllerListener;
import domain.model.GameState;
import domain.controller.NetworkController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class JoinGameFrame extends JFrame implements NetworkControllerListener{

	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	
	private JTextField IPTextField;
	private JTextField portTextField;
	private JTextField usernameTextField;
	private JLabel lblPort;
	private JButton joinWithIPButton;
	private JLabel lblUsername;
	
	private MainMenuFrame mainMenu;
	private ClientNetworkController clientNetworkController;

	/**
	 * Create the frame.
	 */
	public JoinGameFrame(MainMenuFrame mainMenu) {
		setBounds(new Rectangle(100, 100, 450, 350));
		this.mainMenu = mainMenu;
		
		clientNetworkController = new ClientNetworkController();
		clientNetworkController.addNetworkControllerListener(this);
		
		setTitle("Join Game");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		IPTextField = new JTextField();
		IPTextField.setText("localhost");
		IPTextField.setBounds(115, 98, 302, 44);
		contentPane.add(IPTextField);
		IPTextField.setColumns(10);
		
		joinWithIPButton = new JButton("Join with IP");
		joinWithIPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clientNetworkController.initializeClientNetwork(IPTextField.getText(), 
						portTextField.getText(), usernameTextField.getText());
			}
		});
		joinWithIPButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		joinWithIPButton.setBounds(145, 257, 150, 45);
		contentPane.add(joinWithIPButton);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(350, 70, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(45, 15, 350, 70);
			contentPane.add(monopolyLogoPanel);
			
			JLabel lblNewLabel = new JLabel("Host IP:");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel.setBounds(24, 98, 74, 44);
			contentPane.add(lblNewLabel);
			
			portTextField = new JTextField();
			portTextField.setText("9999");
			portTextField.setColumns(10);
			portTextField.setBounds(115, 149, 302, 44);
			contentPane.add(portTextField);
			
			lblPort = new JLabel("Port:");
			lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPort.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPort.setBounds(24, 149, 74, 44);
			contentPane.add(lblPort);
			
			usernameTextField = new JTextField();
			usernameTextField.setText("Player 2");
			usernameTextField.setColumns(10);
			usernameTextField.setBounds(115, 200, 302, 44);
			contentPane.add(usernameTextField);
			
			lblUsername = new JLabel("Username\r\n");
			lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblUsername.setBounds(12, 200, 86, 44);
			contentPane.add(lblUsername);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}
	
	public void connected() {
		IPTextField.setText("Connected to host.");
		IPTextField.setEditable(false);
		portTextField.setText("Waiting for game to start.");
		portTextField.setEditable(false);
		joinWithIPButton.setEnabled(false);
		usernameTextField.setEditable(false);
	}

	@Override
	public void onNetworkEvent(NetworkController source, HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "newConnection":
			connected();
			break;
		case "gameStarted":
			GameFrame gameFrame = new GameFrame(clientNetworkController);
			mainMenu.dispose();
			dispose();
			gameFrame.setVisible(true);
			break;
		}
	}
}
