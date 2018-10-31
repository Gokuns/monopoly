package Model.Dice;

import java.util.Random;

import Model.Dice.Die.faceValue;

public class SpeedDie extends Die {

	faceValue fValues[] = {faceValue.ONE, faceValue.TWO, faceValue.THREE, faceValue.MRMONOPOLY, faceValue.MRMONOPOLY, faceValue.BUS};
	
	public SpeedDie() {
		
	}

	@Override
	protected faceValue roll() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randVal = rand.nextInt(5);
		return fValues[randVal];
	}
	
}
