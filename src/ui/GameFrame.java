package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.Random;

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
import domain.model.squares.properties.Property;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements GameStateListener{

	private JPanel panel;
	private JPanel contentPane;
	private JPanel monopolyLogoPanel;
	private GameController gameController = GameController.getInstance();
	private GameState gameState;
	private int numberOfPlayers;
	private ArrayList<Ball> balls;
	private ArrayList<Building> buildings;
	private Animator animator;
	private BoardLayers boardLayers;
	private JLabel playerLabel;
	private JLabel localPlayerLabel;
	private JLabel rollLabel;
	private JLabel balanceLabel;
	private JLabel actionLabel;

	private JButton rollButton;
	private JButton endTurnButton;
	private JButton moveButton;
	private JButton saveButton;
	private JButton loadButton;
	private JButton pauseButton;
	private JButton resumeButton;
	private JButton buyButton;
	private JButton buildHouseButton;
	private JButton buildHotelButton;
	private JButton buildSkyscraperButton;
	private JButton cardsButton;
	
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
		
		picLabel1.setBounds(153, 130, 50, 50);
		picLabel2.setBounds(213, 130, 50, 50);
		picLabel3.setBounds(273, 130, 50, 50);
		
		System.out.println(monopolyLogoPanel.getWidth() +" , " + monopolyLogoPanel.getHeight());
		panel = new JPanel();
		panel.setBounds(770, 20, 480, 700);
		contentPane.add(panel);
		panel.setLayout(null);

		panel.add(picLabel1);
		panel.add(picLabel2);
		panel.add(picLabel3);
		
		localPlayerLabel = new JLabel("You are: " +gameController.getLocalPlayer().getName());
		localPlayerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		localPlayerLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		localPlayerLabel.setBounds(0, -40, 300, 100);
		panel.add(localPlayerLabel);
		
		balanceLabel = new JLabel("Balance: $"+gameController.getLocalPlayer().getBalance());
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		balanceLabel.setBounds(0, 0, 300, 100);
		panel.add(balanceLabel);
		
		playerLabel = new JLabel("Player X");
		playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		playerLabel.setBounds(93, 40, 300, 100);
		panel.add(playerLabel);
		
		
		rollButton = new JButton("Roll");
		rollButton.setBounds(70, 200, 100, 40);
		panel.add(rollButton);

		moveButton = new JButton("Move");
		moveButton.setBounds(70, 250, 100, 40);
		panel.add(moveButton);
		
		buyButton = new JButton("Buy");
		buyButton.setBounds(190, 200, 100, 40);
		panel.add(buyButton);
		
		buildHouseButton = new JButton("Build House");
		buildHouseButton.setBounds(190, 250, 100, 40);
		panel.add(buildHouseButton);
		
		endTurnButton = new JButton("End Turn");
		endTurnButton.setBounds(310, 200, 100, 40);
		panel.add(endTurnButton);
		
		pauseButton = new JButton("Pause");
		pauseButton.setBounds(380, 450, 100, 40);
		panel.add(pauseButton);
		
		resumeButton = new JButton("Resume");
		resumeButton.setBounds(380, 500, 100, 40);
		panel.add(resumeButton);
		
		saveButton = new JButton("Save");
		saveButton.setBounds(380, 550, 100, 40);
		panel.add(saveButton);
		
		loadButton = new JButton("Load");
		loadButton.setBounds(380, 600, 100, 40);
		panel.add(loadButton);
		
		cardsButton = new JButton("Your Properties");
		cardsButton.setBounds(120, 320, 240, 40);
		panel.add(cardsButton);
		
		actionLabel = new JLabel("");
		actionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		actionLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		actionLabel.setBounds(0, 350, 600, 100);
		panel.add(actionLabel);
		
		buildHotelButton = new JButton("Build Hotel");
		buildHotelButton.setBounds(310, 250, 100, 40);
		panel.add(buildHotelButton);
		
		buildSkyscraperButton = new JButton("Build Skyscraper");
		buildSkyscraperButton.setBounds(430, 250, 100, 40);
		panel.add(buildSkyscraperButton);
		

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
				new SaveFrame();
			}
		});
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoadFrame();
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
		
		buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> map = gameController.buyProperty();
				if(map.get("successfullyBought").equals("false")){
					JOptionPane.showMessageDialog(GameFrame.this.getContentPane(),"You have insufficient funds to purchase, it may have been bought previously or it is an invalid square to buy.");
				}
				buyButton.setEnabled(false);
			}
		});
		
		buildHouseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> map = gameController.buildHouse();
				if(map.get("successfullyBuilt").equals("false")){
					JOptionPane.showMessageDialog(GameFrame.this.getContentPane(),"You can't build a house in this square since you don't have sufficient funds to build a house, it's an invalid square, you don't own the square or you don't own the entire colored district.");
				}
				buildHouseButton.setEnabled(false);
			}
		});
		
		buildHotelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> map = gameController.buildHotel();
				if(map.get("successfullyBuilt").equals("false")){
					JOptionPane.showMessageDialog(GameFrame.this.getContentPane(),"You can't build a hotel in this square since you don't have sufficient funds to build a house, it's an invalid square, you don't own the square or you don't own 4 houses for the time being.");
				}
				buildHotelButton.setEnabled(false);
			}
		});
		
		buildSkyscraperButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<String, String> map = gameController.buildSkyscraper();
				if(map.get("successfullyBuilt").equals("false")){
					JOptionPane.showMessageDialog(GameFrame.this.getContentPane(),"You can't build a hotel in this square since you don't have sufficient funds to build a house, it's an invalid square, you don't own the square or you haven't built a hotel at this square yet.");
				}
				buildSkyscraperButton.setEnabled(false);
			}
		});

		rollLabel = new JLabel("Rolled: ");
		rollLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rollLabel.setBounds(93, 100, 300, 40);
		panel.add(rollLabel);

		rollButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.roll();
				rollButton.setEnabled(false);
				moveButton.setEnabled(true);
			}
		});
		
		cardsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame f = new JFrame();
				f.setSize(1000, 800);
				setLayout(null);
				f.setVisible(true);
				
				
			}
		});
	}
	
	private void deleteBuilding(int layer, int number, int buildingType) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				for(int i=buildings.size()-1;i>=0;i--) {
					if(buildings.get(i).getLayer() == layer && buildings.get(i).getIndex() == number &&
							buildings.get(i).getBuildingType() == buildingType){
						System.out.println("House Found");
						System.out.println("Layer: " + layer +" Number: " + number);
						monopolyLogoPanel.remove(buildings.get(i));
						buildings.remove(i);
						break;
					}
				}
			}
		});
	}
	
	private void buildBuilding(int layer, int number, int buildingType) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("House Being built.");
				int coorX = boardLayers.getSquareCoordinates(layer, number).getX()-37;
				int coorY = boardLayers.getSquareCoordinates(layer, number).getY()-35;
				int numberOfBuildings = 0;
				for(int i=buildings.size()-1;i>=0;i--) {
					if(buildings.get(i).getLayer() == layer && buildings.get(i).getIndex() == number) {
						numberOfBuildings ++;
					}
				}
				coorX = coorX + numberOfBuildings * 5;
				coorY = coorY + numberOfBuildings * 3;
				int[] id = new int[2];
				id[0] = layer;
				id[1] = number;
				Building building = new Building(coorX, coorY, buildingType,id);
				monopolyLogoPanel.add(building);
				building.setBounds(coorX , coorY, 15, 15);
				buildings.add(building);
			}
		});
		
	}

	private void initializeBalls() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println("Balls initializing");
				int coorX = boardLayers.getSquareCoordinates(1, 0).getX()-25;
				int coorY = boardLayers.getSquareCoordinates(1, 0).getY()-20;
				numberOfPlayers = gameState.getOrderedPlayerList().size();
				for(int i = 0;i<numberOfPlayers; i++) {
					System.out.println("Ball "+i);
					String x = "Piece" + Integer.toString(i);
					Random random = new Random();
					int red = random.nextInt(256); int green = random.nextInt(256); 
					int blue = random.nextInt(256); Color randomColor = new Color(red,green,blue);
					Ball ballx = new Ball(x, randomColor,coorX + i*6, coorY);//    4
					monopolyLogoPanel.add(ballx);
					ballx.setBounds(coorX + i*6, coorY, 20, 20);
					balls.add(ballx);


				}
			}
		});
	}

	private void moveUIPiece(int playerIndex, ArrayList<int[]> squareList) {
		//System.out.println(squareList);
		ArrayList<Point> coordinateList = new ArrayList<Point>();
		for(int i=0;i<squareList.size();i++){
			int layer = squareList.get(i)[0];
			int number = squareList.get(i)[1];
			System.out.println(layer);
			System.out.println(number);
			SquareCoordinates current = boardLayers.getSquareCoordinates(layer, number);
			int x = current.getX() - 45 + playerIndex * 6;
			int y = current.getY() - 25;
			Point a = new Point(x,y);
			coordinateList.add(a);
		}
		//System.out.println(coordinateList);
		animator.animate(balls.get(playerIndex),coordinateList, 5);

	}



	private void moveCase(HashMap<String, String> map, String i) {
		int playerIndex = Integer.parseInt(map.get("ID"+i));
		int layer = Integer.parseInt(map.get("layer"+i));
		int number = Integer.parseInt(map.get("number"+i));
		String isEnableBuy = map.get("enableBuy");
		String isEnableBuildHouse = map.get("enableBuildHouse");
		String isEnableBuildHotel = map.get("enableBuildHotel");
		String isEnableBuildSkyscraper = map.get("enableBuildSkyscraper");
		if(GameController.getInstance().getLocalPlayer().getID() == 
				Integer.parseInt(map.get("ID"+i))) {
			if(isEnableBuy.equals("true")) {
				buyButton.setEnabled(true);
			}
			if(isEnableBuildHouse.equals("true")){
				buildHouseButton.setEnabled(true);
			}
			if(isEnableBuildHotel.equals("true")){
				buildHotelButton.setEnabled(true);
			}
			if(isEnableBuildSkyscraper.equals("true")){
				buildSkyscraperButton.setEnabled(true);
			}
		}
		ArrayList<int[]> squareList = new ArrayList<int[]>();
		int stepCount = Integer.parseInt(map.get("steps"));
		for(int j = 0; j < stepCount; j++) {
			int[] data = new int[2];
			data[0] = Integer.parseInt(map.get("squareLayer"+j));
			data[1] = Integer.parseInt(map.get("squareIndex"+j));
			squareList.add(data);
		}
		System.out.println(layer + "-" + number);
		moveUIPiece(playerIndex, squareList);
	}

	private void specialCase(HashMap<String, String> map) {
		JOptionPane.showMessageDialog(this.getContentPane(),
			    map.get("description"));
	}

	private void gameStartedCase(HashMap<String, String> map) {
		String currentPlayerStr = map.get("currentPlayer");
		playerLabel.setText(currentPlayerStr+"'s Turn!");
		initializeBalls();
		gameController.buildHouse();
		gameController.buildHotel();
		gameController.buildSkyscraper();
		/*
		buildBuilding(1,0,0); buildBuilding(1,0,0); buildBuilding(1,0,0); 
		deleteBuilding(1,0,0);
		buildBuilding(1,1,0); buildBuilding(1,2,2);
		buildBuilding(0,1,0); buildBuilding(0,1,0); buildBuilding(0,1,2); 
		buildBuilding(0,2,1); buildBuilding(0,0,1); deleteBuilding(0,0,1);
		buildBuilding(2,0,1); buildBuilding(2,1,2); buildBuilding(2,2,0);
		buildBuilding(0,11,0); buildBuilding(0,12,1); buildBuilding(1,15,2);*/
		if(GameController.getInstance().getLocalPlayer().getID() == 
				Integer.parseInt(map.get("currentPlayerID"))) {
			rollButton.setEnabled(true);
			moveButton.setEnabled(false);
			endTurnButton.setEnabled(false);
			pauseButton.setEnabled(true);
			resumeButton.setEnabled(false);
			saveButton.setEnabled(false);
			loadButton.setEnabled(false);
			buyButton.setEnabled(false);
			buildHouseButton.setEnabled(false);
			buildHotelButton.setEnabled(false);
			buildSkyscraperButton.setEnabled(false);
		} else
		{
			rollButton.setEnabled(false);
			moveButton.setEnabled(false);
			endTurnButton.setEnabled(false);
			pauseButton.setEnabled(false);
			resumeButton.setEnabled(false);
			saveButton.setEnabled(false);
			loadButton.setEnabled(false);
			buyButton.setEnabled(false);
			buildHouseButton.setEnabled(false);
			buildHotelButton.setEnabled(false);
			buildSkyscraperButton.setEnabled(false);
		}
	}

	private void endTurnCase(HashMap<String, String> map) {
		String endTurnStr = map.get("currentPlayer");
		playerLabel.setText(endTurnStr+"'s Turn!");
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
			buyButton.setEnabled(false);
			buildHouseButton.setEnabled(false);
			buildHotelButton.setEnabled(false);
			buildSkyscraperButton.setEnabled(false);
		}
	}
	
	private void jailCase(HashMap<String, String> map) {
		String jailStr = "=> ";
		for(String name:map.keySet()){
			if(!name.equals("type")){
				String info = map.get(name);
				jailStr += name + " " + info;
			}

		}
		actionLabel.setText(jailStr);
	}
	
	private void hurricaneCase(HashMap<String, String> map) {
		String label = "";
		String successfulHurricane = map.get("successfulHurricane");
		String detail = map.get("detail");
		if(successfulHurricane.equals("false")){
			label = "Hurricane card cannot be applied to the given colored district.";
		}else{//successfulHurricane.equals("true")
			label = detail;
		}
		rollLabel.setText(label);
	}
	
	private void inheritHundredDollarsCase(HashMap<String, String> map) {
		String inheritHundredDollarsStr = "=>";
		for(String name:map.keySet()){
			if(!name.equals("type")){
				String balance = map.get(name);
				inheritHundredDollarsStr += " Balance of " + name + " has been updated to $"  + balance + ".";
			}

		}
		actionLabel.setText(inheritHundredDollarsStr);
	}

	private void genericBalanceDescriptionLabeller(HashMap<String, String> map) {
		String desc = "=>";
		for(String name:map.keySet()){
			if(!name.equals("type")){
				String balance = map.get(name);
				desc += " / " + " Balance of " + name + " has been updated to $"  + balance + ".";
			}
		}
		actionLabel.setText(desc);
	}

	private void roll3Case(HashMap<String, String> map) {
		String roll3Str = "=>";
		for(String pName:map.keySet()){
			if(!pName.equals("type")){
				String award = map.get(pName);
				roll3Str += " / " + pName + " has won $" + award + ".";
			}
		}
		actionLabel.setText(roll3Str);
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

		ArrayList<Boolean> states = gameController.getPlayerState(gameState.getCurrentPlayer());
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
		case "buy":
			buyCase(map);
			break;
		case "buildHouse":
			buildHouseCase(map);
			break;
		case "buildHotel":
			buildHotelCase(map);
			break;
		case "buildSkyscraper":
			buildSkyscraperCase(map);
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
		case "hurricane":
			hurricaneCase(map);
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
//			specialCase(map);
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
			moveCase(map, "");
		}
	}

	private void buildSkyscraperCase(HashMap<String, String> map) {
		buildSkyscraperButton.setEnabled(false);
		if(map.get("succesfullyBuilt").equals("true")) {
			int layer = Integer.parseInt(map.get("layer"));
			int index = Integer.parseInt(map.get("index"));
			buildBuilding(layer, index, 2);
		}
	}

	private void buildHotelCase(HashMap<String, String> map) {
		buildHotelButton.setEnabled(false);
		if(map.get("succesfullyBuilt").equals("true")) {
			int layer = Integer.parseInt(map.get("layer"));
			int index = Integer.parseInt(map.get("index"));
			buildBuilding(layer, index, 1);
		}
	}

	private void buildHouseCase(HashMap<String, String> map) {
		buildHouseButton.setEnabled(false);
		if(map.get("succesfullyBuilt").equals("true")) {
			int layer = Integer.parseInt(map.get("layer"));
			int index = Integer.parseInt(map.get("index"));
			buildBuilding(layer, index, 0);
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
		dieCase(map);
		endTurnCase(map);
		for(int i=0; i<GameState.getInstance().getPlayerCount();i++) {
			moveCase(map, i+"");
		}
		
	}
	private void buyCase(HashMap<String, String> map) {
		buyButton.setEnabled(false);
	}
}