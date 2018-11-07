package Model.Dice;

import java.util.ArrayList;
import java.util.List;

public class Cup {
	Die regularDie1;
	Die regularDie2;
	Die speedDie1;
	private static Cup cup;
	List<faceValue> faceValues;
	
	private Cup() {
		regularDie1 = new RegularDie();
		regularDie2 = new RegularDie();
		speedDie1 = new SpeedDie();
		faceValues = new ArrayList<faceValue>();
		
	}
	
	public static synchronized Cup getInstance() {
		if(cup == null) {
			cup = new Cup();
		}return cup;
		
	}
	
	
	public List<faceValue> rollCup() {
		faceValue faceValue1 = regularDie1.roll();
		faceValue faceValue2 = regularDie2.roll();
		faceValue speedValue = speedDie1.roll();
		faceValues.add(faceValue1);
		faceValues.add(faceValue2);
		faceValues.add(speedValue);
		return faceValues;
		
	}
	
	public List<Integer> convertFaceValueToInt() {
		int first = regularDie1.getCurrentFaceValue().ordinal();
		int second = regularDie2.getCurrentFaceValue().ordinal();
		int speed = speedDie1.getCurrentFaceValue().ordinal();
		List<Integer> result = new ArrayList<Integer>();
		result.add(first);
		result.add(second);
		result.add(speed);
		return result;
		
	}

}
