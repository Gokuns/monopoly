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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class JoinGameFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinGameFrame frame = new JoinGameFrame();
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
		
		panel = new JPanel();
		panel.setBounds(51, 13, 342, 68);
		contentPane.add(panel);
	}

}
