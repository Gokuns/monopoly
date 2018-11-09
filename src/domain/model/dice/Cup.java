package domain.model.dice;

import java.util.ArrayList;
import java.util.List;

public class Cup {
	
	Die regularDie;
	Die speedDie;
	private static Cup cup;
	private List<faceValue> faceValues;

	private Cup() {
		regularDie = RegularDie.getInstance();
		speedDie = SpeedDie.getInstance();
		faceValues = new ArrayList<faceValue>(){{add(faceValue.ONE);add(faceValue.ONE);add(faceValue.ONE);}};
		
	}
	
	public static synchronized Cup getInstance() {
		if(cup == null) {
			cup = new Cup();
		}
		return cup;
		
	}
	
	
	public List<faceValue> rollCup() {
		regularDie.roll();
		faceValue regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(0, regularVal);
		
		regularDie.roll();
		regularVal = regularDie.getCurrentFaceValue();
		faceValues.set(1, regularVal);
		faceValue speedValue = speedDie.roll();
		faceValues.set(2, speedValue);
		return faceValues;
		
	}
	
	public List<Integer> convertFaceValueToInt() {
		int first = faceValues.get(0).ordinal()+1;
		int second = faceValues.get(0).ordinal()+1;
		int speed = 0;
		if(faceValues.get(2).ordinal()<3) {
		speed = faceValues.get(0).ordinal()+1;
		}
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
