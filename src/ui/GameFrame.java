package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setBounds(new Rectangle(0, 0, 1500, 1000));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyBoard.png"));
			logoImage = logoImage.getScaledInstance(932, 932, Image.SCALE_SMOOTH);
			JPanel monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(20, 20, 932, 932);
			contentPane.add(monopolyLogoPanel);
			
			JPanel panel = new JPanel();
			panel.setBounds(972, 20, 510, 932);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel playerLabel = new JLabel("Player 1\r\n");
			playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			playerLabel.setBounds(105, 0, 300, 100);
			panel.add(playerLabel);
			
			JButton rollButton = new JButton("Roll");
			rollButton.setBounds(105, 172, 300, 40);
			panel.add(rollButton);
			
			JButton moveButton = new JButton("Move");
			moveButton.setBounds(105, 225, 300, 40);
			panel.add(moveButton);
			
			JLabel lblYouRolledX = new JLabel("You rolled: X X X");
			lblYouRolledX.setHorizontalAlignment(SwingConstants.CENTER);
			lblYouRolledX.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblYouRolledX.setBounds(105, 278, 300, 40);
			panel.add(lblYouRolledX);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}