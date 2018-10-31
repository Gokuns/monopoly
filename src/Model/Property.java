package Model;

public abstract class Property extends Square{
	private String name;
	private Player owner;
	private int price;
	private boolean isMortgaged;
	
	public Property(String name, int price, String description) {
		super(name,description);
		this.name = name;
		this.owner=null;
		this.price = price;
		this.isMortgaged = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
