package domain.model;

import java.util.ArrayList;

import domain.model.specialSquares.payCorners.Go;

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
	

	public Player (String name, int ID, Piece piece) {
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
	
	
}
