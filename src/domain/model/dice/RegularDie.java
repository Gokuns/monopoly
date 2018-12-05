package domain.model.dice;

import java.util.Random;

public class RegularDie extends Die {
	
	private FaceValue fValues[];
	private FaceValue currentFaceValue;
	private static RegularDie regularDie;
	
	private RegularDie() {
		this.fValues = new FaceValue[]{FaceValue.ONE, FaceValue.TWO, FaceValue.THREE, FaceValue.FOUR, FaceValue.FIVE, FaceValue.SIX};
	}
	
	public static synchronized RegularDie getInstance(){
		if(regularDie==null)
			regularDie = new RegularDie();
		return regularDie;
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
