package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.controller.NetworkController;
import domain.model.Board;
import domain.model.GameState;
import domain.model.GameStateListener;
import domain.model.Player;
import domain.model.Square;
//import java.util.ArrayList;


@SuppressWarnings("serial")
public class GameFrame extends JFrame implements GameStateListener{

	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	private GameController gameController;
	private GameState gameState;
	private int numberOfPlayers;
	private ArrayList<Ball> balls;
	private BoardLayers boardLayers;
	private JLabel playerLabel;
	private JLabel rollLabel;
	private Board board;

	private JButton rollButton;
	private JButton endTurnButton;
	private JButton moveButton;
	


	/**
	 * Create the frame.
	 */
	public GameFrame(NetworkController networkController) {
		setTitle("Monopoly");

		board = Board.getInstance();
		gameController = GameController.getInstance();
		gameController.setNetworkController(networkController);
		gameState = GameState.getInstance();
		gameState.addUIListener(this);

		setBounds(new Rectangle(0, 0, 1300, 800));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {

			boardLayers = new BoardLayers();
			balls = new ArrayList<Ball>();

			//Ball ball1 = new Ball("ball1",3);
			//balls.add(ball1);
			//ball1.setBounds(585, 575, 30, 30);
			//contentPane.add(ball1);

			//ball1.setBounds(575,575, 30, 30);
			//contentPane.add(ball1);
			//initializeBalls();
			Image logoImage = ImageIO.read(new File("monopolyBoard.png"));
			logoImage = logoImage.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(20, 20, 700, 700);
			//monopolyLogoPanel.setVisible(false);
			contentPane.add(monopolyLogoPanel);

			JPanel panel = new JPanel();
			panel.setBounds(972, 20, 510, 932);
			contentPane.add(panel);
			panel.setLayout(null);

			playerLabel = new JLabel("Player X");
			playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			playerLabel.setBounds(0, 0, 300, 100);
			panel.add(playerLabel);

			endTurnButton = new JButton("End Turn");
			endTurnButton.setBounds(0, 119, 300, 40);
			panel.add(endTurnButton);

			rollButton = new JButton("Roll");
			rollButton.setBounds(0, 172, 300, 40);

			panel.add(rollButton);

			moveButton = new JButton("Move");
			moveButton.setBounds(0, 225, 300, 40);
			panel.add(moveButton);

			endTurnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameController.endTurn();
				}
			});


			moveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//Square currentSq = gameState.getPlayerCurrentSquare();
					gameController.move();
					moveUIPiece();
					rollButton.setEnabled(false);
					moveButton.setEnabled(false);
					endTurnButton.setEnabled(true);
				}
			});

			rollLabel = new JLabel("You rolled: X X X");
			rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
			rollLabel.setBounds(0, 278, 300, 40);
			panel.add(rollLabel);

			/*
			coords = new JLabel("You rolled: X X X");
			coords.setHorizontalAlignment(SwingConstants.CENTER);
			coords.setFont(new Font("Tahoma", Font.PLAIN, 20));
			coords.setBounds(0, 400, 300, 40);
			panel.add(coords);

			contentPane.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
					coords.setText("X = " + e.getX() + ", Y = " + e.getY());
				}
			});
			 */

			rollButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					gameController.roll();
					rollButton.setEnabled(false);
					moveButton.setEnabled(true);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initializeBalls() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("Balls initializing");

				numberOfPlayers = gameState.getOrderedPlayerList().size();
				for(int i = 0;i<numberOfPlayers; i++) {
					System.out.println("Ball "+i);
					String x = "Piece" + Integer.toString(i);
					Ball ballx = new Ball(x, i);
					ballx.setBounds(565 - i*5 ,555, 30, 30);
					balls.add(ballx);
					monopolyLogoPanel.add(ballx);
					repaint();
					//System.out.println(balls.get(i).getName());
				}
			}
		});

	}

	public void moveUIPiece() {
		int index = gameState.getOrderedPlayerList().indexOf(gameState.getCurrentPlayer());
		int layer = board.getSquareLayerIndex(gameState.getPlayerCurrentSquare());
		int number = board.getSquareIndex(gameState.getPlayerCurrentSquare());
		SquareCoordinates current = boardLayers.getSquareCoordinates(layer, number);
		int x = current.getX() - 15;
		int y = current.getY() - 15;
		balls.get(index).setLocation(x-index*5, y);

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
		case "roll3":
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					String str = "=>";
					for(String pName:map.keySet()){
						if(!pName.equals("type")){
							String award = map.get(pName);
							str += " / " + pName + " has won $" + award + ".";
						}
								
					}
					rollLabel.setText(str);
				}
			});
			break;
		case "payHospitalBill":
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					String str = "=>";
					for(String name:map.keySet()){
						if(!name.equals("type")){
							String balance = map.get(name);
							str += " / " + " Balance of " + name + " has been updated to $"  + balance + ".";
						}
								
					}
					rollLabel.setText(str);
				}
			});
			break;
		case "goToJail":
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					String str = "=>";
					for(String name:map.keySet()){
						if(!name.equals("type")){
							String info = map.get(name);
							str += name + " " + info;
						}
								
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
					int localID = gameController.getLocalPlayer().getID();
					int currentID = Integer.parseInt(map.get("currentPlayerID"));
					if(localID == currentID) {
						rollButton.setEnabled(true);
						moveButton.setEnabled(false);
						endTurnButton.setEnabled(false);
					} else
					{
						rollButton.setEnabled(false);
						moveButton.setEnabled(false);
						endTurnButton.setEnabled(false);
					}
				}
			});
			break;
		case "gameStarted":
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					String str = map.get("currentPlayer");
					playerLabel.setText(str);
					initializeBalls();int localID = gameController.getLocalPlayer().getID();
					int currentID = Integer.parseInt(map.get("currentPlayerID"));
					if(localID == currentID) {
						rollButton.setEnabled(true);
						moveButton.setEnabled(false);
						endTurnButton.setEnabled(false);
					} else
					{
						rollButton.setEnabled(false);
						moveButton.setEnabled(false);
						endTurnButton.setEnabled(false);
					}
				}
			});
			break;
		}
	}
}