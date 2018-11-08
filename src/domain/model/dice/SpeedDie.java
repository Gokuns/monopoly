package domain.model.dice;

import java.util.Random;

public class SpeedDie extends Die {

	private static SpeedDie speedDie;
	private faceValue fValues[];
	private faceValue currentFaceValue;
	
	private SpeedDie() {
		this.fValues = new faceValue[]{faceValue.ONE, faceValue.TWO, faceValue.THREE, faceValue.MRMONOPOLY, faceValue.MRMONOPOLY, faceValue.BUS};
	}
	
	public static synchronized SpeedDie getInstance(){
		if(speedDie==null)
			return new SpeedDie();
		return speedDie;
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
