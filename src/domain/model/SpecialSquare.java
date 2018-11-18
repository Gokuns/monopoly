package domain.model;

public abstract class SpecialSquare extends Square {
	

	public SpecialSquare(String name, String description, int layer, int number) {
		super(name, description, layer, number);
		// TODO Auto-generated constructor stub
	}
	
	protected abstract void action(Player p);
}
