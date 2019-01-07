package domain.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import domain.model.cards.Card;
import domain.model.cards.Deck;
import domain.model.cards.chanceCards.Hurricane;
import com.google.gson.JsonObject;
import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.gameHandler.Board;
import domain.model.gameHandler.GameLoader;
import domain.model.gameHandler.GameSaver;
import domain.model.gameHandler.GameState;
import domain.model.gameHandler.SaveData;
import domain.model.players.Player;
import domain.model.players.Bot.PlayerBot;
import domain.model.squares.Square;
import domain.model.squares.properties.Property;

/**
 * @Overview This class is the first class after UI Layer,
 * it receives messages from UI and does the necessary things or
 * notifies the responsible classes
 */

public class GameController {
	private static GameController controller;
	private Board board = Board.getInstance();
	private GameState gameState = GameState.getInstance();
	@SuppressWarnings("unused")
	private NetworkController networkController;
	private Player localPlayer;
	private PlayerBot bot;
	private GameController() {}

	public static synchronized GameController getInstance() {
		if(controller == null) {
			controller = new GameController();
		}
		return controller;
	}
	
	public String properties(){
		
		
		ArrayList<Property> propList = gameState.getCurrentPlayer().getPrList();
		
		String prop = "Properties: ";
		
		for(int i=0;i<propList.size();i++) {
			String mortgaged = "";
			int number = i+1;
			prop = prop + number + "- " + propList.get(i).getName() + " ";
		}
		
		return prop;
		
		
	}
	
	public HashMap<String, String> buyProperty(){
		Player currentPlayer = gameState.getCurrentPlayer();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "buy");
		map.put("successfullyBought", "false");
		boolean successfullyBought = currentPlayer.buyProperty();
		if(successfullyBought){
			map.put("successfullyBought", "true");
		}
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
		
		return map;
	}
	
	public HashMap<String, String> buildHouse(){
		int layer = board.getSquareLayerIndex(gameState.getPlayerCurrentSquare());
		int index = board.getSquareIndex(gameState.getPlayerCurrentSquare());
		Player currentPlayer = gameState.getCurrentPlayer();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "buildHouse");
		map.put("successfullyBuilt", "false");
		map.put("layer", Integer.toString(layer));
		map.put("index", Integer.toString(index));
		boolean successfullyBuilt = currentPlayer.buildHouse();
		if(successfullyBuilt){
			map.put("successfullyBuilt", "true");
		}
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
		
		return map;
	}
	
	public HashMap<String, String> buildHotel(){
		int layer = board.getSquareLayerIndex(gameState.getPlayerCurrentSquare());
		int index = board.getSquareIndex(gameState.getPlayerCurrentSquare());
		Player currentPlayer = gameState.getCurrentPlayer();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "buildHotel");
		map.put("successfullyBuilt", "false");
		map.put("layer", Integer.toString(layer));
		map.put("index", Integer.toString(index));
		boolean successfullyBuilt = currentPlayer.buildHotel();
		if(successfullyBuilt){
			map.put("successfullyBuilt", "true");
		}
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
		
		return map;
	}
	
	public HashMap<String, String> buildSkyscraper(){
		int layer = board.getSquareLayerIndex(gameState.getPlayerCurrentSquare());
		int index = board.getSquareIndex(gameState.getPlayerCurrentSquare());
		Player currentPlayer = gameState.getCurrentPlayer();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "buildSkyscraper");
		map.put("successfullyBuilt", "false");
		map.put("layer", Integer.toString(layer));
		map.put("index", Integer.toString(index));
		boolean successfullyBuilt = currentPlayer.buildSkyscraper();
		if(successfullyBuilt){
			map.put("successfullyBuilt", "true");
		}
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
		
		return map;
	}
	
	public void setHurricaneColorOfDistrict2ChosenColorOfDistrict(HashMap<String, String> map){
		String chosenColorOfDistrict = map.get("colorOfDistrict");
		Board board = Board.getInstance();
		List<Card> chanceDeck = Deck.getCardListInCurrentDeck();
		for(Card c:chanceDeck){
			String cardName = c.getName();
			if(cardName.equals("HurricaneCard")){
				Hurricane hurricane = (Hurricane) c;
				hurricane.setColorOfDistrict(chosenColorOfDistrict);
				break;
			}
		}
	}

	public void roll() {
		gameState.getCurrentPlayer().setRolled(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "roll");
		board.rollCup(gameState.getCurrentPlayer());
		board.setDieToPlayerState(gameState.getCurrentPlayer());
		Cup cup = Cup.getInstance();
		map.putAll(createFaceValMap(cup));
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	
	public HashMap<String, String> createFaceValMap(Cup cup){
		HashMap<String, String> map = new HashMap<String, String>();
		List<FaceValue> faceValList = cup.getFaceValues();
		for(int i=0; i<3;i++) {
			map.put("faceValue" + i, faceValList.get(i).toString());
		}
		
		return map;
	}
	
	public ArrayList<int[]> getMoveSquares(ArrayList<Square> squareList) {
		ArrayList<int[]> squareIntList = new ArrayList<int[]>(); 
		for(int i=0; i<squareList.size();i++){
			int layer = board.getSquareLayerIndex(squareList.get(i));
			int index = board.getSquareIndex(squareList.get(i));
			int[] intArray = new int[2];
			intArray[0] = layer;
			intArray[1] = index;
			squareIntList.add(intArray);
			}
		return squareIntList;
	}
	
	
	
	/**
	 * @param map The String to String HashMap containing the message
	 * @modifies orderedPlayerList and currentPlayer
	 * @effects  creates and initializes as many players using playerCount from input map, 
	 * adds them to a newly created playerList that was equal to gameState.getPlayerList(),
	 * sets orderedPlayerList and currentPlayer using newly created arrayList,
	 * puts the message "gameStarted" and currentPlayer information in a HashMap,
	 * publishes it to UIListeners
	 */
	public void initializePlayers(HashMap<String, String> map) {
		ArrayList<Player> playerList = gameState.getPlayerList();
		int playerCount = Integer.parseInt(map.get("playerCount"));
		for(int i=0; i<playerCount; i++) {
			String username = map.get("player" + i + "Name");
			int ID = Integer.parseInt(map.get("player" + i + "ID"));
			Player p = new Player(username, ID);
			playerList.add(p);
		}
		playerList.add(bot);
		gameState.setOrderedPlayerList(playerList);
		gameState.setCurrentPlayer(playerList.get(0));
		HashMap<String, String> gameStartedMap = new HashMap<String, String>();
		gameStartedMap.put("type", "gameStarted");
		gameStartedMap.put("currentPlayer", gameState.getCurrentPlayer().getName());
		gameStartedMap.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		gameState.publishToUIListeners(gameStartedMap);
	}
	/**
	 * @param isLocalCommand: 
	 * @modifies specialMap
	 * @effects method calls the moveCommand with the boolean parameter isLocalComand. 
	 * Then, move method gets which square the player must land on,puts these information
	 * into a map and publishes this information to UI listeners and network listeners.
	 */
	public void move(boolean isLocalCommand) {
		ArrayList<Square> moveList = Board.getInstance().movePiece(
				GameState.getInstance().getCurrentPlayer());
		Square landedSquare = moveList.get(moveList.size()-1);
		ArrayList<int[]> moveSquares = getMoveSquares(moveList);
		System.out.println("Piece move Completed");
		sendMoveCommand(isLocalCommand, moveSquares);
		if(landedSquare.getSqStrat()!=null) {
			HashMap<String, String> specialMap = new HashMap<String, String>();
			specialMap.put("type", "special");
			String desc = landedSquare.tryToAct(GameState.getInstance().getCurrentPlayer());
			if(desc==null) desc = landedSquare.getDescription();
			specialMap.put("description", desc);
			GameState.getInstance().publishToUIListeners(specialMap);
			if(isLocalCommand) {
				gameState.publishToNetworkListeners(specialMap);
			}
		}
		gameState.getCurrentPlayer().setMoved(true);
	}
	
	/**
	 *@param isLocalCommand
	 *@modifies map
	 *@effects This method takes a boolean as a parameter, generates a hashMap. Then puts the
	 *information about movement such as player ID, layer and square index info in this map.
	 *It publishes this map to the UI listeners and if the input boolean is true, publishes map 
	 *also to the network listeners.
	 */
	private void sendMoveCommand(boolean isLocalCommand, ArrayList<int[]> moveData) {
		HashMap<String, String> map = new HashMap<String, String>();
		Player p = GameState.getInstance().getCurrentPlayer();
		map.put("type", "move");
		map.put("ID", p.getID()+"");
		map.put("layer", Board.getInstance().getSquareLayerIndex(
				GameState.getInstance().getPlayerCurrentSquare())+"");
		map.put("number", Board.getInstance().getSquareIndex(
				GameState.getInstance().getPlayerCurrentSquare())+"");
		map.put("enableBuy", p.isEnableBuy()+"");
		map.put("enableBuildHouse", p.isEnableBuildHouse()+"");
		map.put("enableBuildHotel", p.isEnableBuildHotel()+"");
		map.put("enableBuildSkyscraper", p.isEnableBuildSkyscraper()+"");
		for(int i = 0; i<moveData.size(); i++) {
			int[] squareData = moveData.get(i);
			map.put("squareLayer"+i, Integer.toString(squareData[0]));
			map.put("squareIndex"+i, Integer.toString(squareData[1]));
		}
		map.put("steps", Integer.toString(moveData.size()));
		GameState.getInstance().publishToUIListeners(map);
		if(isLocalCommand) {
			gameState.publishToNetworkListeners(map);
		}
	}


	public void setNetworkController(NetworkController networkController) {
		this.networkController = networkController;
	}

	/**
	 * @param isLocalCommand: 
	 * @modifies isTurn and map
	 * @effects end turn method sets the turn of the current player as false, which terminates
	 * the players turn. Then it takes the next player to have turn from gameState and sets this players
	 * turn true, which initiates the players' turn. Method also puts the currentPlayer and currentPlayerID
	 * in map and publishes this map to UI listeners. If the input boolean of this method is true, it also
	 * publishes this map to network listeners too.
	 */
	public void endTurn(boolean isLocalCommand) {
		gameState = GameState.getInstance();
		Player p = gameState.getCurrentPlayer();
		p.resetState();
		gameState.setCurrentPlayer(gameState.getNextPlayer());
		gameState.getCurrentPlayer().setTurn(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "endTurn");
		map.put("currentPlayer", gameState.getCurrentPlayer().getName());
		map.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		gameState.publishToUIListeners(map);
		if(isLocalCommand) {
			gameState.publishToNetworkListeners(map);
		}
		if(gameState.getCurrentPlayer().isBot()) {
			playBotTurn(bot);
		}
	}
	
	private void playBotTurn(PlayerBot p) {
		if(getLocalPlayer().getID()==0) {
		boolean decision = p.tryToAct();
		this.roll();
		this.move(true);
		if(decision) this.buyProperty();
		this.endTurn(true);
		}
		
	}

	public void pauseGame() {
		gameState.setGamePaused(true);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "pause");
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	public void resumeGame() {
		gameState.setGamePaused(false);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type",  "resume");
		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	
	public void saveGame(File file) throws Exception {
		GameSaver.writeJsonOnject(file, gameState, Cup.getInstance());
	}
	
	public void loadGame(File file) throws Exception {
		SaveData data = SaveData.getInstance();
		JsonObject loadData = GameLoader.readJsonSimpleDemo(file);
		data.converDataToGame(loadData, gameState, Cup.getInstance());
		HashMap<String, String> loadNotificationMap = new HashMap<String, String>();
		loadNotificationMap.put("type", "loadDataIncoming");
		gameState.publishToNetworkListeners(loadNotificationMap);
		networkController.distributeLoadData(loadData);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "load");
		ArrayList<Player> lst = gameState.getOrderedPlayerList();
		for (int i = 0; i < lst.size(); i++) {
			Player p = lst.get(i);
			Square playerSq = p.getPiece().getCurrentSquare();
			map.put("name"+i,p.getName());
			map.put("ID"+i,p.getID()+"");
			map.put("layer"+i, board.getSquareLayerIndex(playerSq)+"");
			map.put("number"+i, board.getSquareIndex(playerSq)+"");
			map.put("balance"+i, p.getBalance()+"");
			map.put("enableBuy"+i, p.isEnableBuy()+"");
			map.put("enableBuildHouse"+i, p.isEnableBuildHouse()+"");
			map.put("enableBuildHotel"+i, p.isEnableBuildHotel()+"");
			map.put("enableBuildSkyscraper"+i, p.isEnableBuildSkyscraper()+"");
			
		}
		map.put("currentPlayer",  gameState.getCurrentPlayer().getName());
		map.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		map.putAll(createFaceValMap(Cup.getInstance()));

		gameState.publishToNetworkListeners(map);
		gameState.publishToUIListeners(map);
	}
	
	public void loadReceivedGame(JsonObject loadData) throws Exception {
		SaveData data = SaveData.getInstance();
		data.converDataToGame(loadData, gameState, Cup.getInstance());
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "load");
		ArrayList<Player> lst = gameState.getOrderedPlayerList();
		for (int i = 0; i < lst.size(); i++) {
			Player p = lst.get(i);
			Square playerSq = p.getPiece().getCurrentSquare();
			map.put("name"+i,p.getName());
			map.put("ID"+i,p.getID()+"");
			map.put("layer"+i, board.getSquareLayerIndex(playerSq)+"");
			map.put("number"+i, board.getSquareIndex(playerSq)+"");
			map.put("balance"+i, p.getBalance()+"");
			map.put("enableBuy"+i, p.isEnableBuy()+"");
			map.put("enableBuildHouse"+i, p.isEnableBuildHouse()+"");
			map.put("enableBuildHotel"+i, p.isEnableBuildHotel()+"");
			map.put("enableBuildSkyscraper"+i, p.isEnableBuildSkyscraper()+"");
			
		}
		map.put("currentPlayer",  gameState.getCurrentPlayer().getName());
		map.put("currentPlayerID", Integer.toString(gameState.getCurrentPlayer().getID()));
		map.putAll(createFaceValMap(Cup.getInstance()));
		gameState.publishToUIListeners(map);
	}

	public Player getLocalPlayer() {
		return localPlayer;
	}

	public void setLocalPlayer(Player localPlayer) {
		this.localPlayer = localPlayer;
	}

	/**
	 * @param username: The username of the player
	 * @param ID: the ID number of the player
	 * @modifies localPlayer
	 * @effects This method creates a new instance of Player with the input string and integer,
	 * then sets the local player to this new created player.
	 */
	public void initializeLocalPlayer(String username, int ID) {
		Player player = new Player(username, ID);
		localPlayer = player;
	}

	public void setDice(List<String> faceValueStrings) {
		List<FaceValue> faceValues = new ArrayList<FaceValue>();
		for(String str : faceValueStrings) {
			faceValues.add(FaceValue.valueOf(str));
		}
		Cup.getInstance().setFaceValues(faceValues);
	}

	public void setLocalPlayerID(int ID) {
		localPlayer.setID(ID);
	}
	
	public ArrayList<Boolean> getPlayerState(Player p) {
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		boolean hasBeenBought = true;
		Square currentPlayerSquare = p.getPiece().getCurrentSquare();
		if(currentPlayerSquare.isProperty()){
			Property currentPlayerProperty = (Property) currentPlayerSquare;
			Player currentPlayerPropertyOwner = currentPlayerProperty.getOwner();
			hasBeenBought = (currentPlayerPropertyOwner != null);
		}
		
		result.add(p.isRolled());
		result.add(p.isMoved());
		result.add(p.isTurn());
		result.add(p.hasPaused());
		result.add(hasBeenBought);
		
		return result;
	}

	/**
	 * @return the bot
	 */
	public PlayerBot getBot() {
		return bot;
	}

	/**
	 * @param bot the bot to set
	 */
	public void setBot(PlayerBot bot) {
		this.bot = bot;
	}
}
