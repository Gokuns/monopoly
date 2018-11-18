package domain.model;

import java.util.List;

import domain.model.dice.Cup;
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
		GameState game = GameState.getInstance();
		Player currentPlayer = game.getCurrentPlayer();
		
		Cup cup = Cup.getInstance();
		List<faceValue> roll3Dice = cup.roll3Dice();
		
		List<Player> playerList = game.getPlayerList();
		
		for(Player p:playerList){//award all the players except currentPlayer.
			if(!p.equals(currentPlayer))
				awardPlayer(p, roll3Dice, 1000);
		}
		awardPlayer(currentPlayer, roll3Dice, 1500);//award currentPlayer
		
	}
	
	private void awardPlayer(Player p, List<faceValue> roll3Dice, int threeMatchPrice){
		
		int newBalance = p.getBalance();
		
		List<Roll3Card> playerRoll3Cards = p.getRoll3Cards();
		
		int oneMatchPrice = 50;
		int twoMatchPrice = 200;
		
		for(Roll3Card r3Card:playerRoll3Cards){
			List<faceValue> fValList = r3Card.roll3Values;
			int nOfMatch = nOfMatch(roll3Dice, fValList);
			
			if(nOfMatch == 3){
				newBalance += threeMatchPrice;
			}else if(nOfMatch == 2){
				newBalance += twoMatchPrice;
			}else if(nOfMatch == 1){
				newBalance += oneMatchPrice;
			}
			
			playerRoll3Cards.remove(r3Card);//removal of the roll3Card from player's inventory upon usage.
			p.setRoll3Cards(playerRoll3Cards);
		}
		
		
		p.setBalance(newBalance);
	}

	private int nOfMatch(List<faceValue> roll3Dice, List<faceValue> fValList){
		int nOfMatch = 0;
		
		for(faceValue fVal:fValList){
			if(roll3Dice.contains(fVal))
				nOfMatch++;
		}
		
		return nOfMatch;
	}
	
}
