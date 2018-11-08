package view;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ClientNetworkController;
import network.ClientNetworkControllerListener;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinGameFrame extends JFrame implements ClientNetworkControllerListener{

	private JPanel contentPane;
	private JTextField textField;
	private JPanel monopolyLogoPanel;
	private JButton joinWithIPButton;
	private JTextField textField_1;
	private JLabel lblPort;

	/**
	 * Create the frame.
	 */
	public JoinGameFrame() {
		setTitle("Join Game");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(98, 102, 334, 44);
		contentPane.add(textField);
		textField.setColumns(10);
		
		joinWithIPButton = new JButton("Join with IP");
		joinWithIPButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		joinWithIPButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		joinWithIPButton.setBounds(145, 210, 150, 45);
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
			lblNewLabel.setBounds(12, 102, 74, 44);
			contentPane.add(lblNewLabel);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(98, 153, 334, 44);
			contentPane.add(textField_1);
			
			lblPort = new JLabel("Port:");
			lblPort.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPort.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPort.setBounds(12, 153, 74, 44);
			contentPane.add(lblPort);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}
	
	public void connected() {
		textField.setText("Connected to host. Waiting for game to start.");
		textField.setEditable(false);
		joinWithIPButton.setEnabled(false);
	}

	@Override
	public void onNetworkEvent(ClientNetworkController source, String eventName) {
		if(eventName.equals("connectedToHost")) {
			connected();
		}
	}
}
