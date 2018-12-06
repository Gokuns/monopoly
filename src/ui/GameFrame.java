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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.controller.NetworkController;
import domain.model.GameState;
import domain.model.GameStateListener;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements GameStateListener{

	private JPanel panel;
	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	private GameController gameController;
	private GameState gameState;
	private int numberOfPlayers;
	private ArrayList<Ball> balls;
	private BoardLayers boardLayers;
	private JLabel playerLabel;
	private JLabel rollLabel;

	private JButton rollButton;
	private JButton endTurnButton;
	private JButton moveButton;

	/**
	 * Create the frame.
	 */
	public GameFrame(NetworkController networkController) {
		setTitle("Monopoly");

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

		boardLayers = new BoardLayers();
		balls = new ArrayList<Ball>();

		try {
			Image logoImage = ImageIO.read(new File("monopolyBoard.png"));
			//logoImage = logoImage.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setLayout(null);
			monopolyLogoPanel.setBounds(20, 20, 700, 700);
			contentPane.add(monopolyLogoPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(monopolyLogoPanel.getWidth() +" , " + monopolyLogoPanel.getHeight());
		panel = new JPanel();
		panel.setBounds(860, 20, 300, 700);
		contentPane.add(panel);
		panel.setLayout(null);

		playerLabel = new JLabel("Player X");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		playerLabel.setBounds(0, 0, 300, 100);
		panel.add(playerLabel);

		endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds(0, 225, 300, 40);
		panel.add(endTurnButton);

		rollButton = new JButton("Roll");
		rollButton.setBounds(0, 119, 300, 40);
		panel.add(rollButton);

		moveButton = new JButton("Move");
		moveButton.setBounds(0, 172, 300, 40);
		panel.add(moveButton);

		endTurnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.endTurn(true);
			}
		});

		moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameController.move(true);
				rollButton.setEnabled(false);
				moveButton.setEnabled(false);
				endTurnButton.setEnabled(true);
			}
		});

		rollLabel = new JLabel("You rolled: X X X");
		rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rollLabel.setBounds(0, 278, 300, 40);
		panel.add(rollLabel);

		rollButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.roll();
				rollButton.setEnabled(false);
				moveButton.setEnabled(true);
			}
		});
	}

	public void initializeBalls() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("Balls initializing");
				int coorX = boardLayers.getSquareCoordinates(1, 0).getX();
				int coorY = boardLayers.getSquareCoordinates(1, 0).getY();
				numberOfPlayers = gameState.getOrderedPlayerList().size();
				for(int i = 0;i<numberOfPlayers; i++) {
					System.out.println("Ball "+i);
					String x = "Piece" + Integer.toString(i);
					Ball ballx = new Ball(x, i);
					ballx.setBounds(coorX + i*6 ,coorY, 15, 15); //    4
					balls.add(ballx);
					monopolyLogoPanel.add(ballx);
					repaint();
				}
			}
		});
	}

	public void moveUIPiece(int playerIndex, int layer, int number) {
		SquareCoordinates current = boardLayers.getSquareCoordinates(layer, number);
		System.out.println("It should go to layer "+ layer+" , square index = "+number);
		int x = current.getX();
		int y = current.getY();
		balls.get(playerIndex).setLocation(x + playerIndex * 6, y);
		System.out.println("It goes here : x = " +x+" , y = "+y);
		repaint();
	}

	@Override
	public void update(GameState source, HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "roll":
			String rollStr = "=>";
			for(int i=0; i<3;i++) {
				rollStr += " " + map.get("faceValue"+i);
			}
			rollLabel.setText(rollStr);
			break;
		case "roll3":
			String roll3Str = "=>";
			for(String pName:map.keySet()){
				if(!pName.equals("type")){
					String award = map.get(pName);
					roll3Str += " / " + pName + " has won $" + award + ".";
				}
			}
			rollLabel.setText(roll3Str);
			break;
		case "payHospitalBill":
			String hospitalStr = "=>";
			for(String name:map.keySet()){
				if(!name.equals("type")){
					String balance = map.get(name);
					hospitalStr += " / " + " Balance of " + name + " has been updated to $"  + balance + ".";
				}
			}
			rollLabel.setText(hospitalStr);
			break;
		case "goToJail":
			String jailStr = "=>";
			for(String name:map.keySet()){
				if(!name.equals("type")){
					String info = map.get(name);
					jailStr += name + " " + info;
				}

			}
			rollLabel.setText(jailStr);
			break;
		case "endTurn":
			String endTurnStr = map.get("currentPlayer");
			playerLabel.setText(endTurnStr);
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
			break;
		case "gameStarted":
			String currentPlayerStr = map.get("currentPlayer");
			playerLabel.setText(currentPlayerStr);
			initializeBalls();
			if(GameController.getInstance().getLocalPlayer().getID() == 
					Integer.parseInt(map.get("currentPlayerID"))) {
				rollButton.setEnabled(true);
				moveButton.setEnabled(false);
				endTurnButton.setEnabled(false);
			} else
			{
				rollButton.setEnabled(false);
				moveButton.setEnabled(false);
				endTurnButton.setEnabled(false);
			}
			break;
		case "special":
			
			JOptionPane.showMessageDialog(this.getContentPane(),
				    map.get("description"));
			
			break;
		case "move":
			
			int playerIndex = Integer.parseInt(map.get("ID"));
			int layer = Integer.parseInt(map.get("layer"));
			int number = Integer.parseInt(map.get("number"));
			System.out.println(layer + "-" + number);
			moveUIPiece(playerIndex, layer, number);
		}
	}
}