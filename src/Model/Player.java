package Model;

import java.util.ArrayList;

public class Player {
	private String name;
	private String ID;
	private int balance=3200;
	private Piece piece;
	private ArrayList<Property> propList;
	private ArrayList<Card> cards;
	private boolean isTurn;
	private boolean inJail;
	private boolean throwsDoubleDice;
	private boolean hasMoved;
	private boolean hasRolled;

	public Player (String name, String ID, Piece piece) {
		this.name = name;
		this.ID = ID;
		this.piece = new Piece();
		this.propList = new ArrayList<Property>();
		this.cards = new ArrayList<Card>();
		this.isTurn = false;
		this.inJail = false;
		this.throwsDoubleDice = false;
		this.hasRolled = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
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

	public boolean isDoubleDice() {
		return throwsDoubleDice;
	}

	public void setDoubleDice(boolean doubleDice) {
		this.throwsDoubleDice = doubleDice;
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
	
	
}
