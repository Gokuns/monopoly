package domain.model.dice;

import java.util.Random;

public class RegularDie extends Die {
	
	private faceValue fValues[];
	private faceValue currentFaceValue;
	private static RegularDie regularDie;
	
	private RegularDie() {
		this.fValues = new faceValue[]{faceValue.ONE, faceValue.TWO, faceValue.THREE, faceValue.FOUR, faceValue.FIVE, faceValue.SIX};
	}
	
	public static synchronized RegularDie getInstance(){
		if(regularDie==null)
			regularDie = new RegularDie();
		return regularDie;
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
