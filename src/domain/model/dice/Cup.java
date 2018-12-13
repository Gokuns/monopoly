package domain.model.dice;

import java.util.ArrayList;
import java.util.List;

public class Cup {
	
	private Die regularDie;
	private Die speedDie;
	private static Cup cup;
	private List<FaceValue> faceValues;

	@SuppressWarnings("serial")
	private Cup() {
		regularDie = RegularDie.getInstance();
		speedDie = SpeedDie.getInstance();
		faceValues = new ArrayList<FaceValue>(){{add(FaceValue.ONE);add(FaceValue.ONE);add(FaceValue.ONE);}};
		
	}
	
	public static synchronized Cup getInstance() {
		if(cup == null) {
			cup = new Cup();
		}
		return cup;
		
	}
	
	
	public List<FaceValue> rollCup() {
		regularDie.roll();
		FaceValue regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(0, regularVal);
		
		regularDie.roll();
		regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(1, regularVal);
		FaceValue speedValue = speedDie.roll();
		faceValues.set(2, speedValue);
		return faceValues;
		
	}
	
	public List<Integer> convertFaceValueToInt() {
		int first = faceValues.get(0).ordinal()+1;
		int second = faceValues.get(1).ordinal()+1;
		int speed = 0;
		if(faceValues.get(2).ordinal()<3) {
		speed = faceValues.get(2).ordinal()+1;
		}
		List<Integer> result = new ArrayList<Integer>();
		result.add(first);
		result.add(second);
		result.add(speed);
		System.out.println("First: " + first + ", Second: " + second + ", Third: " + speed);
		return result;
		
	}
	
	public List<FaceValue> getFaceValues() {
		return faceValues;
	}

	public void setFaceValues(List<FaceValue> faceValues) {
		this.faceValues = faceValues;
	}
	
	public List<FaceValue> roll3Dice(){
		
		regularDie.roll();
		FaceValue regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(0, regularVal);
		
		regularDie.roll();
		regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(1, regularVal);
		
		regularDie.roll();
		regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(2, regularVal);
		
		return faceValues;
	}

}
