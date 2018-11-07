package Model.Dice;

import java.util.Random;

public class SpeedDie extends Die {

	private faceValue fValues[];
	private faceValue currentFaceValue;
	
	public SpeedDie() {
		this.fValues = new faceValue[]{faceValue.ONE, faceValue.TWO, faceValue.THREE, faceValue.MRMONOPOLY, faceValue.MRMONOPOLY, faceValue.BUS};
	}

	@Override
	protected faceValue roll() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randIndex = rand.nextInt(6);
		faceValue randVal = fValues[randIndex];
		this.currentFaceValue = randVal;
		return randVal;
	}
	
	public faceValue getCurrentFaceValue() {
		return currentFaceValue;
	}
	
}
