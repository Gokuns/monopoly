package domain.model.dice;

import java.util.Random;

public class SpeedDie extends Die {

	private static SpeedDie speedDie;
	private FaceValue fValues[];
	private FaceValue currentFaceValue;
	
	private SpeedDie() {
		this.fValues = new FaceValue[]{FaceValue.ONE, FaceValue.TWO, FaceValue.THREE, FaceValue.MRMONOPOLY, FaceValue.MRMONOPOLY, FaceValue.BUS};
	}
	
	public static synchronized SpeedDie getInstance(){
		if(speedDie==null)
			speedDie = new SpeedDie();
		return speedDie;
	}

	@Override
	protected FaceValue roll() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int randIndex = rand.nextInt(6);
		FaceValue randVal = fValues[randIndex];
		this.currentFaceValue = randVal;
		return randVal;
	}
	
	public FaceValue getCurrentFaceValue() {
		return currentFaceValue;
	}
	
}
