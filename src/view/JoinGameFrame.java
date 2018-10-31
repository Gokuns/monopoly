package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class JoinGameFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel monopolyLogoPanel;

	/**
	 * Create the frame.
	 */
	public JoinGameFrame() {
		setTitle("Join Game");
		setMaximumSize(new Dimension(400, 200));
		setMinimumSize(new Dimension(400, 200));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(12, 102, 420, 44);
		textField.setMinimumSize(new Dimension(10, 100));
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Join with IP");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(148, 160, 151, 44);
		contentPane.add(btnNewButton);
		
		try {
			Image logoImage = ImageIO.read(new File("monopolyLogo.png"));
			logoImage = logoImage.getScaledInstance(350, 70, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(50, 15, 350, 70);
			contentPane.add(monopolyLogoPanel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
