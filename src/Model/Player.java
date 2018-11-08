package Model;

import java.util.ArrayList;

public class Player {
	private String name;
	private int ID;
	private int balance=3200;
	private Piece piece;
	private ArrayList<Property> propList;
	private ArrayList<Card> cards;
	private boolean isTurn;
	private boolean inJail;
	private boolean rolledDouble;
	private boolean hasMoved;
	private boolean hasRolled;
	private boolean isBankrupt;
	private int doubleRollCounter;
	private boolean rolledTriple;
	private boolean rolledMrMonopoly;
	private boolean rolledBus;

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
