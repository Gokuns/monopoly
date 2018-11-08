package view;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

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
	private JLabel lblNewLabel;
	private InetAddress ip;

	/**
	 * Create the frame.
	 */
	public HostGameFrame() {
		setTitle("Host Game");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start game");
		btnNewButton.setEnabled(false);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(145, 160, 150, 45);
		contentPane.add(btnNewButton);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(350, 70, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(45, 15, 350, 70);
			contentPane.add(monopolyLogoPanel);
			
			lblNewLabel = new JLabel("Waiting for players...");
			lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(12, 100, 420, 30);
			contentPane.add(lblNewLabel);
			
			ip = InetAddress.getLocalHost();
			JLabel lblYourIp = new JLabel("Your IP :   "+ ip.getHostAddress());
			lblYourIp.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblYourIp.setBounds(105, 246, 228, 35);
			contentPane.add(lblYourIp);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		setVisible(true);
	}
}
