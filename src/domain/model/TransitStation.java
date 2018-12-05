package domain.model;

public class TransitStation extends Square{

	public TransitStation(String name, String description) {
		super(name, description);
		this.setTransit(true);
		// TODO Auto-generated constructor stub
	}
}
