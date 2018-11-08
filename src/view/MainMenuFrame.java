package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainMenuFrame() {		
		setTitle("Monopoly");
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
			logoImage = logoImage.getScaledInstance(600, 120, Image.SCALE_SMOOTH);
			JPanel monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(100, 60, 600, 120);
			contentPane.add(monopolyLogoPanel);

		} catch (IOException e) {
			e.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBounds(300, 250, 200, 200);
		contentPane.add(panel);

		JButton hostAGameButton = new JButton("Host a Game");
		hostAGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initHostAGameFrame();
			}
		});
		hostAGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hostAGameButton.setBounds(0, 0, 200, 50);

		JButton joinAGameButton = new JButton("Join a Game");
		joinAGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initJoinAGameFrame();
			}
		});
		joinAGameButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		joinAGameButton.setBounds(0, 60, 200, 50);

		panel.setLayout(null);
		panel.add(hostAGameButton);
		panel.add(joinAGameButton);
		setVisible(true);
	}

	public void initHostAGameFrame() {
		setEnabled(false);
		HostGameFrame hostGameFrame = new HostGameFrame();
		hostGameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				setEnabled(true);
			}
		});
	}

	public void initJoinAGameFrame() {
		setEnabled(false);
		JoinGameFrame joinGameFrame = new JoinGameFrame();
		joinGameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				setEnabled(true);
			}
		});
	}
}
