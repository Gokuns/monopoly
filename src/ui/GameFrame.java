package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.controller.NetworkController;
import domain.controller.NetworkControllerListener;
import domain.model.GameState;
import domain.model.GameStateListener;
import domain.model.dice.Cup;
import domain.model.dice.faceValue;

public class GameFrame extends JFrame implements GameStateListener{

	private JPanel contentPane;
	private GameController gameController;
	private GameState gameState;

	private JLabel rollLabel;
	private JLabel playerLabel;

	/**
	 * Create the frame.
	 */
	public GameFrame(NetworkController networkController) {
		setTitle("Monopoly");

		gameController = GameController.getInstance();
		gameController.setNetworkController(networkController);
		gameState = GameState.getInstance();

		setBounds(new Rectangle(0, 0, 1500, 1000));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			Image boardImage = ImageIO.read(new File("monopolyBoard.png"));
			boardImage = boardImage.getScaledInstance(932, 932, Image.SCALE_SMOOTH);
			JPanel monopolyBoardPanel = new BackgroundImagePanel(boardImage);
			monopolyBoardPanel.setBounds(20, 20, 932, 932);
			contentPane.add(monopolyBoardPanel);

			JPanel panel = new JPanel();
			panel.setBounds(972, 20, 510, 932);
			contentPane.add(panel);
			panel.setLayout(null);

			playerLabel = new JLabel(gameState.getCurrentPlayer().getName());
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

			moveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameController.move();
				}
			});

			rollLabel = new JLabel("You rolled: X X X");
			rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			rollLabel.setBounds(105, 278, 300, 40);
			panel.add(rollLabel);

			rollButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gameController.roll();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameState source, HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "roll":
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					String str = "=>";
					for(int i=0; i<3;i++) {
						str += " " + map.get("faceValue"+i);
					}
					rollLabel.setText(str);
				}
			});
			break;
		case "endTurn":
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					String str = map.get("currentPlayer");
					playerLabel.setText(str);
				}
			});
		}
	}
}