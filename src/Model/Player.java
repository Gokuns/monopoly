package Model;

import java.util.ArrayList;

public class Player {
	private String name;
	private String ID;
	private int balance=3200;
	private Piece piece;
	private ArrayList<Property> PrList;
	private ArrayList<Card> Cards;
	private boolean isTurn = false;
	private boolean inJail =false;
	private boolean doubleDice = false;
	private boolean moved = false;
	private boolean rolled = false;

	public Player (String name, String ID, Piece piece) {
		this.name = name;
		this.ID = ID;
		this.piece = new Piece();
		this.PrList = new ArrayList<Property>();
		this.Cards = new ArrayList<Card>();
		
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
		return PrList;
	}

	public void setPrList(ArrayList<Property> prList) {
		PrList = prList;
	}

	public ArrayList<Card> getCards() {
		return Cards;
	}

	public void setCards(ArrayList<Card> cards) {
		Cards = cards;
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
		return doubleDice;
	}

	public void setDoubleDice(boolean doubleDice) {
		this.doubleDice = doubleDice;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean isRolled() {
		return rolled;
	}

	public void setRolled(boolean rolled) {
		this.rolled = rolled;
	}
	
	
}
