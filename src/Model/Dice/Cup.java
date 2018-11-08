package Model.Dice;

import java.util.ArrayList;
import java.util.List;

public class Cup {
	Die regularDie1;
	Die regularDie2;
	Die speedDie1;
	private static Cup cup;
	private List<faceValue> faceValues;

	private Cup() {
		regularDie1 = new RegularDie();
		regularDie2 = new RegularDie();
		speedDie1 = new SpeedDie();
		faceValues = new ArrayList<faceValue>(){{add(faceValue.ONE);add(faceValue.ONE);add(faceValue.ONE);}};
		
	}
	
	public static synchronized Cup getInstance() {
		if(cup == null) {
			cup = new Cup();
		}
		return cup;
		
	}
	
	
	public List<faceValue> rollCup() {
		faceValue faceValue1 = regularDie1.roll();
		faceValue faceValue2 = regularDie2.roll();
		faceValue speedValue = speedDie1.roll();
		faceValues.set(0, faceValue1);
		faceValues.set(1, faceValue2);
		faceValues.set(2, speedValue);
		return faceValues;
		
	}
	
	public List<Integer> convertFaceValueToInt() {
		int first = regularDie1.getCurrentFaceValue().ordinal() + 1;
		int second = regularDie2.getCurrentFaceValue().ordinal() + 1;
		int speed = speedDie1.getCurrentFaceValue().ordinal() + 1;
		List<Integer> result = new ArrayList<Integer>();
		result.add(first);
		result.add(second);
		result.add(speed);
		return result;
		
	}
	
	public List<faceValue> getFaceValues() {
		return faceValues;
	}

	public void setFaceValues(List<faceValue> faceValues) {
		this.faceValues = faceValues;
	}

}
