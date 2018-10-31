package Model.Dice;

public abstract class Die {
	protected enum faceValue{
		ONE, TWO, THREE, FOUR, FIVE, SIX, MRMONOPOLY, BUS;
	}
	
	protected abstract faceValue roll();
	

}
