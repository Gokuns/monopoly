package domain.model.players;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.model.cards.Card;
import domain.model.cards.ChanceCard;
import domain.model.cards.CommunityChestCard;
import domain.model.cards.Roll3Card;
import domain.model.gameHandler.Board;
import domain.model.squares.Square;
import domain.model.squares.properties.Deed;
import domain.model.squares.properties.Property;
import domain.model.squares.properties.Street;

//import domain.model.squares.specialSquares.ChanceAction;
//import domain.model.squares.specialSquares.CommunityChest;
//import domain.model.squares.specialSquares.payCorners.Go;

public class Player {
	private String name;  //Name of the player
	private int ID;		//Identification number
	private int balance=3200;		//starting money is initialized as 3200
	private Piece piece;		//A game piece owned by the player
	private ArrayList<Property> propList;		//Property list of the player
	private ArrayList<Card> cards;		//Cards owned by the player
	private boolean isTurn;		//true if its this player's turn
	private boolean inJail;		//true if the player is in jail
	private boolean rolledDouble;		//true if the player rolled double
	private boolean hasMoved;		//true if the player made their move
	private boolean hasRolled;		//true if the player rolled the dice
	private boolean isBankrupt;		//true when the player is out of the game, disconnect, lose
	private int doubleRollCounter;		//counts the time player rolls double, goes in jail if rolled 3 times in a row
	private boolean rolledTriple;		//true if the player rolled triple
	private boolean rolledMrMonopoly;		//true if the player rolled Mr Monopoly in speed die
	private boolean rolledBus;		//true if the player rolled bus in speed die
	private List<ChanceCard> chanceCards; //chance action cards belonging to the player
	private List<CommunityChestCard> communityCards; //community chest cards belonging to the player
	private List<Roll3Card> roll3Cards; //roll 3 cards belonging to the player
	private boolean changingLayer; //true when the player is changing the layer.
	private boolean hasPaused; // true if this player paused the game.
	private boolean enableBuy;
	private boolean isBot = false;
	private boolean enableBuildHouse;
	private boolean enableBuildHotel;
	private boolean enableBuildSkyscraper;
	private HashMap<String, Integer> ownedColoredDisctricts= new HashMap<String, Integer>();
	
	public boolean isEnableBuildSkyscraper() {
		return enableBuildSkyscraper;
	}

	public void setEnableBuildSkyscraper(boolean enableBuildSkyscraper) {
		this.enableBuildSkyscraper = enableBuildSkyscraper;
	}



	public Player (String name, int ID) {
		this.name = name;
		this.ID = ID;
		this.piece = new Piece();
		this.propList = new ArrayList<Property>();
		this.cards = new ArrayList<Card>();
		this.isTurn = false;
		this.inJail = false;
		this.rolledDouble = false;
		this.hasRolled = false;
		this.setBankrupt(false);
		this.doubleRollCounter = 0;
		this.rolledTriple = false;
		this.rolledMrMonopoly = false;
		this.rolledBus = false;
		this.setChangingLayer(false);
		chanceCards  = new ArrayList<ChanceCard>();
		communityCards  = new ArrayList<CommunityChestCard>();
		roll3Cards  = new ArrayList<Roll3Card>();
		this.changingLayer = false;
		this.hasPaused=false;
		initializeOwnedColoredDistricts();
	}
	
	private void initializeOwnedColoredDistricts(){
		ownedColoredDisctricts.put("Purple", 0);
		ownedColoredDisctricts.put("Light Blue", 0);
		ownedColoredDisctricts.put("Magenta", 0);
		ownedColoredDisctricts.put("Orange", 0);
		ownedColoredDisctricts.put("Red", 0);
		ownedColoredDisctricts.put("Yellow", 0);
		ownedColoredDisctricts.put("Dark Blue", 0);
		ownedColoredDisctricts.put("Dark Green", 0);
		ownedColoredDisctricts.put("Dark Orange", 0);
		ownedColoredDisctricts.put("White", 0);
		ownedColoredDisctricts.put("Black", 0);
		ownedColoredDisctricts.put("Grey", 0);
		ownedColoredDisctricts.put("Pink", 0);
		ownedColoredDisctricts.put("Light Green", 0);
		ownedColoredDisctricts.put("Light Yellow", 0);
		ownedColoredDisctricts.put("Turquiose", 0);
		ownedColoredDisctricts.put("Wine Red", 0);
		ownedColoredDisctricts.put("Dark Yellow", 0);
		ownedColoredDisctricts.put("Tan", 0);
		ownedColoredDisctricts.put("Dark Red", 0);
	}
	
	public HashMap<String, Integer> propertyList2ownedColoredDistricts(){
		HashMap<String, Integer> ownedColoredDistrictMap = new HashMap<String, Integer>();
		for(Property prop:propList){
			Street street = (Street) prop;
			String propColor = street.getColor();
			if(!ownedColoredDistrictMap.containsKey(propColor)){
				ownedColoredDistrictMap.put(propColor, 1);
			}else{
				ownedColoredDistrictMap.put(propColor, ownedColoredDistrictMap.get(propColor)+1);
			}
		}
		return ownedColoredDistrictMap;
	}
	
	public boolean buyProperty(){
		int playerBalance = this.getBalance();
		Piece playerPiece = this.getPiece();
		Square playerSquare = playerPiece.getCurrentSquare();
		if(playerSquare.isProperty()){
			Property playerProperty = (Property) playerSquare;
			Player propertyOwner = playerProperty.getOwner();
			if(propertyOwner == null){
				int propertyPrice = playerProperty.getPrice();
				if(playerBalance >= propertyPrice){
					playerBalance -= propertyPrice;
					this.setBalance(playerBalance);
					playerProperty.setOwner(this);
					this.propList.add(playerProperty);
					System.out.println(this.name + " has bought " + playerProperty.getName() +  " for $" + playerProperty.getPrice());
					Street street = (Street) playerProperty;
					String propertyColor = street.getColor();
					ownedColoredDisctricts.put(propertyColor, ownedColoredDisctricts.get(propertyColor)+1);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean buildHouse(){
		int playerBalance = this.getBalance();
		Piece playerPiece = this.getPiece();
		Square playerSquare = playerPiece.getCurrentSquare();
		if(playerSquare.isProperty()){
			Property playerProperty = (Property) playerSquare;
			Player owner = playerProperty.getOwner();
			Street street = (Street) playerProperty;
			String propertyColor = street.getColor();
			if(owner.getName().equals(this.getName())){
				int referenceColorCount = Board.getInstance().getColoredDistricts().get(propertyColor);
				int ownedColorCount = this.ownedColoredDisctricts.get(propertyColor);
				if(referenceColorCount == ownedColorCount){
					Deed propertyDeed = playerProperty.getDeed();
					int housePrice = propertyDeed.getValues().get("housePrice");
					if(playerBalance >= housePrice){
						playerBalance -= housePrice;
						this.setBalance(playerBalance);
						int currentHouseCount = street.getHouseCount();
						street.setHouseCount(currentHouseCount + 1);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean buildHotel(){
		int playerBalance = this.getBalance();
		Piece playerPiece = this.getPiece();
		Square playerSquare = playerPiece.getCurrentSquare();
		if(playerSquare.isProperty()){
			Property playerProperty = (Property) playerSquare;
			Player owner = playerProperty.getOwner();
			Street street = (Street) playerProperty;
			if(owner.getName().equals(this.getName())){
				Deed propertyDeed = playerProperty.getDeed();
				int houseCount = street.getHouseCount();
				int hotelPrice = propertyDeed.getValues().get("houseCount");
				if(houseCount==4){
					if(playerBalance>=hotelPrice){
						street.setHouseCount(0);
						street.setHasHotel(true);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean buildSkyscraper(){
		int playerBalance = this.getBalance();
		Piece playerPiece = this.getPiece();
		Square playerSquare = playerPiece.getCurrentSquare();
		if(playerSquare.isProperty()){
			Property playerProperty = (Property) playerSquare;
			Player owner = playerProperty.getOwner();
			Street street = (Street) playerProperty;
			if(owner.getName().equals(this.getName())){
				Deed propertyDeed = playerProperty.getDeed();
				boolean hasHotel = street.isHasHotel();
				int skyscraperPrice = propertyDeed.getValues().get("skyPrice");
				if(hasHotel){
					if(playerBalance>=skyscraperPrice){
						street.setHasHotel(false);
						street.setHasSkyscraper(true);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public HashMap<String, Integer> getOwnedColoredDisctricts() {
		return ownedColoredDisctricts;
	}

	public void setOwnedColoredDisctricts(HashMap<String, Integer> ownedColoredDisctricts) {
		this.ownedColoredDisctricts = ownedColoredDisctricts;
	}

	public String intToString(int i) {
		return new Integer(i).toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public ArrayList<Property> getPrList() {
		return propList;
	}

	public void setPrList(ArrayList<Property> prList) {
		propList = prList;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}

	public boolean isRolledDouble() {
		return rolledDouble;
	}

	public void setRolledDouble(boolean rolledDouble) {
		this.rolledDouble = rolledDouble;
	}

	public boolean isMoved() {
		return hasMoved;
	}

	public void setMoved(boolean moved) {
		this.hasMoved = moved;
	}

	public boolean isRolled() {
		return hasRolled;
	}

	public void setRolled(boolean rolled) {
		this.hasRolled = rolled;
	}

	public boolean isBankrupt() {
		return isBankrupt;
	}

	public void setBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}

	public int getDoubleRollCounter() {
		return doubleRollCounter;
	}

	public void setDoubleRollCounter(int doubleRollCounter) {
		this.doubleRollCounter = doubleRollCounter;
	}

	public boolean isRolledTriple() {
		return rolledTriple;
	}

	public void setRolledTriple(boolean rolledTriple) {
		this.rolledTriple = rolledTriple;
	}

	/**
	 * @return the rolledMrMonopoly
	 */
	public boolean isRolledMrMonopoly() {
		return rolledMrMonopoly;
	}

	/**
	 * @param rolledMrMonopoly the rolledMrMonopoly to set
	 */
	public void setRolledMrMonopoly(boolean rolledMrMonopoly) {
		this.rolledMrMonopoly = rolledMrMonopoly;
	}

	/**
	 * @return the rolledBus
	 */
	public boolean isRolledBus() {
		return rolledBus;
	}

	/**
	 * @param rolledBus the rolledBus to set
	 */
	public void setRolledBus(boolean rolledBus) {
		this.rolledBus = rolledBus;
	}

	public List<Roll3Card> getRoll3Cards() {
		return roll3Cards;
	}

	public void setRoll3Cards(List<Roll3Card> roll3Cards) {
		this.roll3Cards = roll3Cards;
	}

	public List<CommunityChestCard> getCommunityCards() {
		return communityCards;
	}

	public void setCommunityCards(List<CommunityChestCard> communityCards) {
		this.communityCards = communityCards;
	}

	public List<ChanceCard> getChanceCards() {
		return chanceCards;
	}

	public void setChanceCards(List<ChanceCard> chanceCards) {
		this.chanceCards = chanceCards;
	}

	/**
	 * @return the changingLayer
	 */
	public boolean isChangingLayer() {
		return changingLayer;
	}

	/**
	 * @param changingLayer the changingLayer to set
	 */
	public void setChangingLayer(boolean changingLayer) {
		this.changingLayer = changingLayer;
	}

	/**
	 * @return the hasPaused
	 */
	public boolean hasPaused() {
		return hasPaused;
	}

	/**
	 * @param hasPaused the hasPaused to set
	 */
	public void setHasPaused(boolean hasPaused) {
		this.hasPaused = hasPaused;
	}
	
	public void resetState() {
		setRolled(false);
		setMoved(false);
		setChangingLayer(false);
		setDoubleRollCounter(0);
		setHasPaused(false);
		setRolledBus(false);
		setRolledDouble(false);
		setRolledMrMonopoly(false);
		setRolledTriple(false);
		setTurn(false);
		setEnableBuy(false);
		setEnableBuildHouse(false);
		setEnableBuildHotel(false);
		setEnableBuildSkyscraper(false);
	}

	public boolean isEnableBuildHotel() {
		return enableBuildHotel;
	}

	public void setEnableBuildHotel(boolean enableBuildHotel) {
		this.enableBuildHotel = enableBuildHotel;
	}

	/**
	 * @return the enableBuy
	 */
	public boolean isEnableBuy() {
		return enableBuy;
	}

	/**
	 * @param enableBuy the enableBuy to set
	 */
	public void setEnableBuy(boolean enableBuy) {
		this.enableBuy = enableBuy;
	}

	/**
	 * @return the isBot
	 */
	public boolean isBot() {
		return isBot;
	}
	/**
	 * @param isBot the isBot to set
	 */
	public void setBot(boolean isBot) {
		this.isBot = isBot;
	}
		
	public boolean isEnableBuildHouse() {
		return enableBuildHouse;
	}

	public void setEnableBuildHouse(boolean enableBuildHouse) {
		this.enableBuildHouse = enableBuildHouse;

	}
	
	
}
