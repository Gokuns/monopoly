package ui;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.controller.HostNetworkController;
import domain.controller.NetworkControllerListener;
import domain.controller.NetworkController;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class HostGameFrame extends JFrame implements NetworkControllerListener{

	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	
	private JLabel yourIPAdress;
	private JLabel portTextLabel;
	private JLabel usernameTextLabel;
	private JTextField portTextField;
	private JTextField usernameTextField;
	private JLabel botBehvaiourLabel;
	private JComboBox<String> botBehaviour;
	
	private JButton hostGameButton;
	
	private HostNetworkController hostNetworkController;
	@SuppressWarnings("unused")
	private MainMenuFrame mainMenu;

	/**
	 * Create the frame.
	 */
	public HostGameFrame(MainMenuFrame mainMenu) {
		setBounds(new Rectangle(100, 100, 430, 520));
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
					hostNetworkController = new HostNetworkController(
							portTextField.getText(),
							usernameTextField.getText());
					hostNetworkController.addNetworkControllerListener(HostGameFrame.this);
					
					portTextLabel.setText("Waiting for players");
					portTextField.setEditable(false);
					usernameTextLabel.setText("0 players connected");
					usernameTextField.setEditable(false);
					hostGameButton.setText("Start Game");
					hostGameButton.setEnabled(false);
				} else if(hostGameButton.getText().equals("Start Game")) {
					GameFrame gameFrame = new GameFrame();
					mainMenu.dispose();
					dispose();
					gameFrame.setVisible(true);
					hostNetworkController.gameStarted(usernameTextField.getText(), botBehaviour.getSelectedIndex());
				}
			}
		});
		hostGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostGameButton.setBounds(145, 358, 150, 45);
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
			
			botBehvaiourLabel = new JLabel("Select bot's behaviour");
			botBehvaiourLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			botBehvaiourLabel.setHorizontalAlignment(SwingConstants.CENTER);
			botBehvaiourLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			botBehvaiourLabel.setBounds(12, 250, 420, 100);
			contentPane.add(botBehvaiourLabel);
			
			String[] botOptions = {"Thrifty", "Random", "Greedy"};
			botBehaviour = new JComboBox<String>(botOptions);
			botBehaviour.setSelectedItem(botOptions[0]);
			botBehaviour.setBounds(110, 320, 220, 30);
			contentPane.add(botBehaviour);

			
			yourIPAdress = new JLabel(InetAddress.getLocalHost().getHostAddress());
			yourIPAdress.setHorizontalTextPosition(SwingConstants.CENTER);
			yourIPAdress.setHorizontalAlignment(SwingConstants.CENTER);
			yourIPAdress.setFont(new Font("Tahoma", Font.PLAIN, 18));
			yourIPAdress.setBounds(10, 420, 420, 30);
			contentPane.add(yourIPAdress);
			

			
			
			
			

			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}

	private void clientConnected(int connectionCount) {
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
