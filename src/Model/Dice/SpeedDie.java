package Model.Dice;

import java.util.Random;

import Model.Dice.Die.faceValue;

public class SpeedDie extends Die {

	faceValue fValues[];
	
	public SpeedDie() {
		this.fValues = new faceValue[]{faceValue.ONE, faceValue.TWO, faceValue.THREE, faceValue.MRMONOPOLY, faceValue.MRMONOPOLY, faceValue.BUS};
	}

	@Override
	protected faceValue roll() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randIndex = rand.nextInt(6);
		faceValue randVal = fValues[randIndex];
		return randVal;
	}
	
}
