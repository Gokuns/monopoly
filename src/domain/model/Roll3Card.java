package domain.model;

import java.util.List;

import domain.model.dice.Cup;
import domain.model.dice.RegularDie;
import domain.model.dice.faceValue;

public class Roll3Card extends Card{
	
	List<faceValue> roll3Values;
	
	public Roll3Card(faceValue fVal1, faceValue fVal2, faceValue fVal3){
		roll3Values.add(fVal1);
		roll3Values.add(fVal2);
		roll3Values.add(fVal3);
	}
	
	@Override
	protected void action() {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		GameState game = GameState.getInstance();
		Player currentPlayer = game.getCurrentPlayer();
		
		Cup cup = Cup.getInstance();
		roll3Values = cup.roll3Dice();
		
		
		
	}

	
	
}
