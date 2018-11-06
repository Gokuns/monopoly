package Model;

public abstract class Property extends Square{
	private String name;
	private Player owner;
	private int price;
	private boolean isMortgaged;
	private Deed deed;

	public Property(String name, int price, String description, Deed deed) {
		super(name,description);
		this.price = price;
		this.owner=null;
		this.isMortgaged = false;
		this.deed = deed;
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
	
	public Deed getDeed() {
		return deed;
	}

	public void setDeed(Deed deed) {
		this.deed = deed;
	}
	
}
