package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.controller.HostNetworkController;
import domain.controller.NetworkControllerListener;
import domain.controller.NetworkController;
import domain.model.Board;
import domain.model.GameState;
import domain.model.dice.Cup;
import domain.model.dice.faceValue;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;

public class GameFrame extends JFrame implements NetworkControllerListener{

	private JPanel contentPane;
	private GameController gameController;
	private GameState game;
	private NetworkController networkController;
	private int numberOfPlayers;
	ArrayList<Ball> balls;
	private BoardLayers boardLayers;

	private JLabel rollLabel;
	private JLabel coords;

	/**
	 * Create the frame.
	 */
	public GameFrame(NetworkController networkController) {
		setTitle("Monopoly");

		this.networkController = networkController;
		networkController.addNetworkControllerListener(this);
		
		boardLayers = new BoardLayers();
		balls = new ArrayList<Ball>();
		gameController = GameController.getInstance();
		game = GameState.getInstance();
		setBounds(new Rectangle(0, 0, 1300, 800));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			
			
			numberOfPlayers = game.getOrderedPlayerList().size();
			for(int i = 0;i<numberOfPlayers; i++) {
				String x = "Piece" + Integer.toString(i);
				Ball ballx = new Ball(x, i);
				balls.add(ballx);
				ballx.setBounds(585 - i*5 ,575, 30, 30);
				contentPane.add(ballx);
				//System.out.println(balls.get(i).getName());
				
			}
			
			//Ball ball1 = new Ball("ball1",3);
			//balls.add(ball1);
			//ball1.setBounds(585, 575, 30, 30);
			//contentPane.add(ball1);
			
			//ball1.setBounds(575,575, 30, 30);
			//contentPane.add(ball1);
			Image logoImage = ImageIO.read(new File("monopolyBoard.png"));
			logoImage = logoImage.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
			JPanel monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setBounds(20, 20, 700, 700);
			//monopolyLogoPanel.setVisible(false);
			contentPane.add(monopolyLogoPanel);
			
			JPanel panel = new JPanel();
			panel.setBounds(972, 20, 510, 932);
			contentPane.add(panel);
			panel.setLayout(null);

			JLabel playerLabel = new JLabel(game.getCurrentPlayer().getName());
			playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
			playerLabel.setBounds(0, 0, 300, 100);
			panel.add(playerLabel);
			
			
			JButton endTurnButton = new JButton("End Turn");
			endTurnButton.setBounds(0, 119, 300, 40);
			panel.add(endTurnButton);

			JButton rollButton = new JButton("Roll");
			rollButton.setBounds(0, 172, 300, 40);

			panel.add(rollButton);

			JButton moveButton = new JButton("Move");
			moveButton.setBounds(0, 225, 300, 40);
			panel.add(moveButton);
			
			endTurnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameController.endTurn();
					playerLabel.setText(game.getCurrentPlayer().getName());
					//System.out.println("It is " + game.getCurrentPlayer().getName() +"'s turn");

				}


			});
			
			
			moveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameController.move();
					int index = game.getOrderedPlayerList().indexOf(game.getCurrentPlayer());
					int layer = game.getPlayerCurrentSquare().getLayer();
					int number = game.getPlayerCurrentSquare().getNumber();
					int x = boardLayers.getSquareCoordinates(layer-1, number).getX() - 15;
					int y = boardLayers.getSquareCoordinates(layer-1, number).getY() - 15;
					balls.get(index).setLocation(x-index*5, y);
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
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("type", "roll");
					gameController.roll();
					Cup cup = Cup.getInstance();
					List<faceValue> faceValList = cup.getFaceValues();
					String str = "=>";
					for(int i=0; i<3;i++) {
						str += " " + faceValList.get(i).toString();
						map.put("faceValue" + i, faceValList.get(i).toString());
					}
					rollLabel.setText(str);
					networkController.sendMessageToPlayers(map);
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	@Override
	public void onNetworkEvent(NetworkController source, HashMap<String, String> map) {
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
		}
	}
}