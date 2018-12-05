package domain.model.squares;

public class Railroad extends Property {

	public Railroad(String name, int price, String description, Deed deed) {
		super(name, price, description, deed);
		this.setTransit(true);
		// TODO Auto-generated constructor stub
	}

}
