package Model.Dice;

import java.util.Random;

public class RegularDie extends Die {
	
	faceValue fValues[] = {faceValue.ONE, faceValue.TWO, faceValue.THREE, faceValue.FOUR, faceValue.FIVE, faceValue.SIX};
	
	public RegularDie() {
		
	}

	@Override
	protected faceValue roll() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randVal = rand.nextInt(5);
		return fValues[randVal];
	}
	

}
