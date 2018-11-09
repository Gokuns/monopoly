package ui;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.controller.HostNetworkController;
import domain.controller.HostNetworkControllerListener;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HostGameFrame extends JFrame implements HostNetworkControllerListener{

	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	private JLabel textLabel;
	private JButton hostGameButton;
	private JTextField portTextField;

	/**
	 * Create the frame.
	 */
	public HostGameFrame() {
		setTitle("Host Game");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		hostGameButton = new JButton("Host Game");
		hostGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hostGameButton.getText()=="Host Game") {
					HostNetworkController hostNetworkController = new HostNetworkController(portTextField.getText());
					hostNetworkController.addHostNetworkControllerListener(HostGameFrame.this);
					hostGameButton.setText("Start Game");
					hostGameButton.setEnabled(false);
					textLabel.setText("Waiting for players");
					portTextField.setEditable(false);
				} else if(hostGameButton.getText().equals("Start Game")) {
					TestGameFrame gf = new TestGameFrame();
					gf.setVisible(true);
				}
			}
		});
		hostGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostGameButton.setBounds(145, 200, 150, 45);
		contentPane.add(hostGameButton);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(350, 70, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(45, 15, 350, 70);
			contentPane.add(monopolyLogoPanel);
			
			textLabel = new JLabel("Please enter a port number");
			textLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textLabel.setBounds(12, 100, 420, 30);
			contentPane.add(textLabel);
			
			portTextField = new JTextField();
			portTextField.setBounds(145, 143, 150, 44);
			contentPane.add(portTextField);
			portTextField.setColumns(10);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}

	public void clientConnected(int connectionCount) {
		setLabelText(connectionCount + " players connected.");
		if(connectionCount>0) {
			getStartGameButton().setEnabled(true);
		}
	}

	public String getLabelText() {
		return textLabel.getText();
	}
	public void setLabelText(String text) {
		textLabel.setText(text);
	}
	public JButton getStartGameButton() {
		return hostGameButton;
	}

	@Override
	public void onNetworkEvent(HostNetworkController source, String eventName) {
		if(eventName.equals("newConnection")) {
			clientConnected(source.getConnectionCount());
		}
	}
}
