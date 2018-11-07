package view;

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

public class HostGameFrame extends JFrame {

	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	private JLabel textLabel;
	
	private int connectedPlayers;
	private JButton startGameButton;

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
		
		startGameButton = new JButton("Start game");
		startGameButton.setEnabled(false);
		startGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		startGameButton.setBounds(145, 160, 150, 45);
		contentPane.add(startGameButton);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(350, 70, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(45, 15, 350, 70);
			contentPane.add(monopolyLogoPanel);
			
			textLabel = new JLabel("Waiting for players...");
			textLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			textLabel.setBounds(12, 100, 420, 30);
			contentPane.add(textLabel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		connectedPlayers = 0;
		
		setVisible(true);
	}

	public void clientConnected() {
		connectedPlayers++;
		setLabelText(connectedPlayers + " players connected.");
		if(connectedPlayers>0) {
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
		return startGameButton;
	}
}
