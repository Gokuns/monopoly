package domain.model.squares.properties;

import domain.model.players.Player;
import domain.model.squares.Square;

public abstract class Property extends Square {
	private Player owner;
	private int price;
	private boolean isMortgaged;
	private Deed deed;

	public Property(String name, int price, String description, Deed deed) {
		super(name, description);
		this.price = price;
		this.owner = null;
		this.isMortgaged = false;
		setProperty(true);
		this.deed = deed;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isMortgaged() {
		return isMortgaged;
	}

	public void setMortgaged(boolean isMortgaged) {
		this.isMortgaged = isMortgaged;
	}

	public Deed getDeed() {
		return deed;
	}

	public void setDeed(Deed deed) {
		this.deed = deed;
	}


}
