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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import domain.controller.GameController;
import domain.model.cards.communityChestCards.OpeningNightTickets;
import domain.model.gameHandler.GameState;
import domain.model.gameHandler.GameStateListener;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements GameStateListener{

	private JPanel panel;
	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	private GameController gameController = GameController.getInstance();
	private GameState gameState;
	private int numberOfPlayers;
	private ArrayList<Ball> balls;
	private Animator animator;
	private BoardLayers boardLayers;
	private JLabel playerLabel;
	private JLabel rollLabel;

	private JButton rollButton;
	private JButton endTurnButton;
	private JButton moveButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton pauseButton;
	private JButton resumeButton;
	
	private Image dieImage1;
	private Image dieImage2;
	private Image dieImage3;
	
	private JLabel picLabel1;
	private JLabel picLabel2;
	private JLabel picLabel3;

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setTitle("Monopoly");

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
		animator = new Animator();

		try {
			Image logoImage = ImageIO.read(new File("monopolyBoard.png"));
			monopolyLogoPanel = new BackgroundImagePanel(logoImage);
			monopolyLogoPanel.setLayout(null);
			monopolyLogoPanel.setBounds(20, 20, 700, 700);
			contentPane.add(monopolyLogoPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			dieImage1 = ImageIO.read(new File("imgDice/1.png"));
			dieImage2 = ImageIO.read(new File("imgDice/1.png"));
			dieImage3 = ImageIO.read(new File("imgDice/1.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		picLabel1 = new JLabel(new ImageIcon(dieImage1));
		picLabel2 = new JLabel(new ImageIcon(dieImage2));
		picLabel3 = new JLabel(new ImageIcon(dieImage3));
		
		picLabel1.setBounds(60, 66, 50, 50);
		picLabel2.setBounds(120, 66, 50, 50);
		picLabel3.setBounds(180, 66, 50, 50);
		
		System.out.println(monopolyLogoPanel.getWidth() +" , " + monopolyLogoPanel.getHeight());
		panel = new JPanel();
		panel.setBounds(860, 20, 300, 700);
		contentPane.add(panel);
		panel.setLayout(null);

		panel.add(picLabel1);
		panel.add(picLabel2);
		panel.add(picLabel3);
		
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
		
		saveButton = new JButton("Save");
		saveButton.setBounds(0, 384, 300, 40);
		panel.add(saveButton);
		
		loadButton = new JButton("Load");
		loadButton.setBounds(0, 437, 300, 40);
		panel.add(loadButton);
		
		pauseButton = new JButton("Pause");
		pauseButton.setBounds(0, 278, 300, 40);
		panel.add(pauseButton);
		
		resumeButton = new JButton("Resume");
		resumeButton.setBounds(0, 331, 300, 40);
		panel.add(resumeButton);
		
		

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
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gameController.saveGame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gameController.loadGame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					gameController.pauseGame();
			}
		});
		
		resumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					gameController.resumeGame();
			}
		});

		rollLabel = new JLabel("You rolled: ");
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

	private void initializeBalls() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("Balls initializing");
				int coorX = boardLayers.getSquareCoordinates(1, 0).getX()-10;
				int coorY = boardLayers.getSquareCoordinates(1, 0).getY()-5;
				numberOfPlayers = gameState.getOrderedPlayerList().size();
				for(int i = 0;i<numberOfPlayers; i++) {
					System.out.println("Ball "+i);
					String x = "Piece" + Integer.toString(i);
					Ball ballx = new Ball(x, i,coorX + i*6 -15 ,coorY -15);//    4
					monopolyLogoPanel.add(ballx);
					ballx.setBounds(coorX + i*6 -15 ,coorY -15, 20, 20);
					balls.add(ballx);


				}
			}
		});
	}

	private void moveUIPiece(int playerIndex, int layer, int number) {
		SquareCoordinates current = boardLayers.getSquareCoordinates(layer, number);
		int x = current.getX() - 45;
		int y = current.getY() - 25;
		animator.animate(balls.get(playerIndex),x + playerIndex * 6, y, 10);
	}



	private void moveCase(HashMap<String, String> map) {
		int playerIndex = Integer.parseInt(map.get("ID"));
		int layer = Integer.parseInt(map.get("layer"));
		int number = Integer.parseInt(map.get("number"));
		System.out.println(layer + "-" + number);
		moveUIPiece(playerIndex, layer, number);
	}

	private void specialCase(HashMap<String, String> map) {
		JOptionPane.showMessageDialog(this.getContentPane(),
			    map.get("description"));
	}

	private void gameStartedCase(HashMap<String, String> map) {
		String currentPlayerStr = map.get("currentPlayer");
		playerLabel.setText(currentPlayerStr);
		initializeBalls();
		if(GameController.getInstance().getLocalPlayer().getID() == 
				Integer.parseInt(map.get("currentPlayerID"))) {
			rollButton.setEnabled(true);
			moveButton.setEnabled(false);
			endTurnButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			saveButton.setEnabled(false);
			loadButton.setEnabled(false);
		} else
		{
			rollButton.setEnabled(false);
			moveButton.setEnabled(false);
			endTurnButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(false);
			saveButton.setEnabled(false);
			loadButton.setEnabled(false);
		}
	}

	private void endTurnCase(HashMap<String, String> map) {
		String endTurnStr = map.get("currentPlayer");
		playerLabel.setText(endTurnStr);
		int localID = gameController.getLocalPlayer().getID();
		int currentID = Integer.parseInt(map.get("currentPlayerID"));
		if(localID == currentID) {
			rollButton.setEnabled(true);
			moveButton.setEnabled(false);
			endTurnButton.setEnabled(false);
			pauseButton.setEnabled(true);
		} else
		{
			rollButton.setEnabled(false);
			moveButton.setEnabled(false);
			endTurnButton.setEnabled(false);
			pauseButton.setEnabled(false);
		}
	}
	
	private void jailCase(HashMap<String, String> map) {
		String jailStr = "=>";
		for(String name:map.keySet()){
			if(!name.equals("type")){
				String info = map.get(name);
				jailStr += name + " " + info;
			}

		}
		rollLabel.setText(jailStr);
	}
	
	
	
	private void inheritHundredDollarsCase(HashMap<String, String> map) {
		String inheritHundredDollarsStr = "=>";
		for(String name:map.keySet()){
			if(!name.equals("type")){
				String balance = map.get(name);
				inheritHundredDollarsStr += " Balance of " + name + " has been updated to $"  + balance + ".";
			}

		}
		rollLabel.setText(inheritHundredDollarsStr);
	}

	private void genericBalanceDescriptionLabeller(HashMap<String, String> map) {
		String desc = "=>";
		for(String name:map.keySet()){
			if(!name.equals("type")){
				String balance = map.get(name);
				desc += " / " + " Balance of " + name + " has been updated to $"  + balance + ".";
			}
		}
		rollLabel.setText(desc);
	}

	private void roll3Case(HashMap<String, String> map) {
		String roll3Str = "=>";
		for(String pName:map.keySet()){
			if(!pName.equals("type")){
				String award = map.get(pName);
				roll3Str += " / " + pName + " has won $" + award + ".";
			}
		}
		rollLabel.setText(roll3Str);
	}
	
	private void pickDieImage(int i, String s) {
		if(i == 0) {
			try {
				dieImage1 = ImageIO.read(new File("imgDice/"+s+".png"));
				picLabel1.setIcon(new ImageIcon(dieImage1));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(i == 1) {
			try {
				dieImage2 = ImageIO.read(new File("imgDice/"+s+".png"));
				picLabel2.setIcon(new ImageIcon(dieImage2));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				dieImage3 = ImageIO.read(new File("imgDice/"+s+".png"));
				picLabel3.setIcon(new ImageIcon(dieImage3));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void dieCase(HashMap<String, String> map) {
		String rollStr;
		for(int i=0; i<3;i++) {
			rollStr = map.get("faceValue"+i);
			switch(rollStr) {
			case "ONE":
				pickDieImage(i, "1");
				break;
			case "TWO":
				pickDieImage(i, "2");
				break;
			case "THREE":
				pickDieImage(i, "3");
				break;
			case "FOUR":
				pickDieImage(i, "4");
				break;
			case "FIVE":
				pickDieImage(i, "5");
				break;
			case "SIX":
				pickDieImage(i, "6");
				break;
			case "MRMONOPOLY":
				pickDieImage(i, "mrmonopoly");
				break;
			case "BUS":
				pickDieImage(i, "bus");
				break;
			}		
		}
	}
	public void disableButtons() {
		rollButton.setEnabled(false);
		moveButton.setEnabled(false);
		endTurnButton.setEnabled(false);
		pauseButton.setEnabled(false);
		resumeButton.setEnabled(false);
		saveButton.setEnabled(false);
		loadButton.setEnabled(false);
	}
	public void refreshButtons() {

		ArrayList<Boolean> states = gameController.getPlayerState();
		boolean rolled = states.get(0);
		boolean moved = states.get(1);
		boolean turn = states.get(2);
		boolean paused = states.get(3);
		rollButton.setEnabled(!rolled);
		moveButton.setEnabled(rolled && !moved);
		endTurnButton.setEnabled(moved && turn);
		pauseButton.setEnabled(turn);
		resumeButton.setEnabled(paused);
		saveButton.setEnabled(paused);
		loadButton.setEnabled(paused);
	}
	
	@Override
	public void update(GameState source, HashMap<String, String> map) {
		String type = map.get("type");
		switch(type){
		case "roll":
			dieCase(map); 
			break;
		case "roll3":
			roll3Case(map);
			break;
		case "chairPerson":
			genericBalanceDescriptionLabeller(map);
			break;
		case "goToJail":
			jailCase(map);
			break;
		case "schoolFees":
			genericBalanceDescriptionLabeller(map);
			break;
		case "trafficTicket":
			genericBalanceDescriptionLabeller(map);
			break;
		case "doctorsFee":
			genericBalanceDescriptionLabeller(map);
			break;
		case "inheritHundredDollars":
			inheritHundredDollarsCase(map);
			break;
		case "openingNightTickets":
			genericBalanceDescriptionLabeller(map);
			break;
		case "payHospitalBill":
			genericBalanceDescriptionLabeller(map);
			break;
		case "endTurn":
			endTurnCase(map);
			break;
		case "gameStarted":
			gameStartedCase(map);
			break;
		case "special":	
			specialCase(map);
			break;
		case "load":
			loadCase(map);
		break;
		case "pause":
			pauseCase(map);
		break;
		case "resume":
			resumeCase(map);
		break;
		case "move":
			moveCase(map);
		}
	}

	private void resumeCase(HashMap<String, String> map) {
		refreshButtons();
		
	}

	private void pauseCase(HashMap<String, String> map) {
		disableButtons();
		resumeButton.setEnabled(true);
		saveButton.setEnabled(true);
		loadButton.setEnabled(true);
		
		
	}

	private void loadCase(HashMap<String, String> map) {
		refreshButtons();
		
	}
}