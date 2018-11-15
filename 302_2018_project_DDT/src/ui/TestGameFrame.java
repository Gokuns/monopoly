package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.model.GameState;
import domain.model.Piece;
import domain.model.Player;
import domain.model.dice.Cup;
import domain.model.dice.faceValue;

public class TestGameFrame extends JFrame {

	private GameController controller;
	private GameState game;
	private JPanel contentPane;
	private JPanel rollPane;
	
	private JButton endTurnButton;
	private JButton rollButton;
	private JLabel lblFaceValues;
	private JLabel lblCurrentPlayer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGameFrame frame = new TestGameFrame();
					frame.setVisible(true);		
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		});
		
//		int a = 1;
//		//asd
//		a=1;
	}

	/**
	 * Create the frame.
	 */
	public TestGameFrame() {
		
		controller = GameController.getInstance();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		rollPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		rollButton = new JButton("ROLL");
		endTurnButton = new JButton("END TURN");
		lblFaceValues = new JLabel("");
		lblCurrentPlayer = new JLabel("");
		
		rollPane.add(rollButton);
		rollPane.add(lblFaceValues);
		rollPane.add(endTurnButton);
		rollPane.add(lblCurrentPlayer);
		
		lblCurrentPlayer.setText("Player 1`s turn");
		
		
		add(rollPane);
//		rollPane.setVisible(true);
		
//		contentPane.setVisible(true);
//		rollButton.setVisible(true);
		
		endTurnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				controller.endTurn();
				String playerName = controller.getCurrentPlayer().getName();
				lblCurrentPlayer.setText(playerName + "'s turn");
			}
		});		

		
		rollButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				controller.roll();
				Cup cup = Cup.getInstance();
				List<faceValue> faceValList = cup.getFaceValues();
				String str = "=>";
				for(int i=0; i<3;i++)
					str += " " + faceValList.get(i).toString();
				lblFaceValues.setText(str);
			}
		});
	}

}
