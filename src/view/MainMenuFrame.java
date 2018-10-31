package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenuFrame() {
		setMinimumSize(new Dimension(800, 600));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(400, 79, Image.SCALE_SMOOTH);
			JPanel monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(200, 80, 400, 79);
			contentPane.add(monopolyLogoPanel);
			
			JPanel panel = new JPanel();
			panel.setBounds(300, 250, 200, 200);
			contentPane.add(panel);
			
			JButton btnHostAGame = new JButton("Host a Game");
			btnHostAGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			
			JButton btnJoinAGame = new JButton("Join a Game");
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel.add(btnHostAGame);
			panel.add(btnJoinAGame);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
}
