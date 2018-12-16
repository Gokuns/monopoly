package domain.model.dice;

import java.util.ArrayList;
import java.util.List;

public class Cup {
	
	private Die regularDie;
	private Die speedDie;
	private static Cup cup;
	private List<FaceValue> faceValues;
	
	
	/**
	 * @overview This class implements a cup which consists of dice. It generates an instance of a RegularDie
	 * and a SpeedDie. 
	 */
	
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
	
	
	/**
	 * @modifies faceValues
	 * @effects When a dice is rolled with the cup, this method sets the face values of rolled dices. There 
	 * are two regular dices and a speed die. Method gets currentFaceValue of these dice from either 
	 * RegularDie or SpeedDie object and sets these values inside an ArrayList which is called faceValues.
	 * @return a list of faceValues of 3 dice.
	 */
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
	
	/** 
	 * @effects Board object calls this method to move piece. This method simply turns the face values 
	 * to integers and writes them into a list called result.
	 * @return a list that holds face values of dices as integers.
	 */
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
	
	/**
	 *  A getter method to get the faceValues.
	 * @return a list that holds faceValues of dice.
	 */
	public List<FaceValue> getFaceValues() {
		return faceValues;
	}

	/**
	 * @modifies faceValues
	 * A setter method to set the list of faceValues to a new list of faceValues.
	 * @param faceValues
	 */
	public void setFaceValues(List<FaceValue> faceValues) {
		this.faceValues = faceValues;
	}
	
	
	/**
	 * @modifies faceValues
	 * @effects When Roll3Card is called, it calls this method roll3Dice. This method gets the faceValues of 
	 * three regular dices from the regularDie object and a list faceValues holds these values of dices.
	 * @return a list that holds faceValues of three regularDices. 
	 */
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
