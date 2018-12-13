package domain.model.cards;

import java.util.HashMap;
import java.util.List;

import domain.model.GameState;
import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.players.Player;

public class Roll3Card extends Card{
	
	private List<FaceValue> roll3Values;
	
	public Roll3Card(FaceValue fVal1, FaceValue fVal2, FaceValue fVal3, String desc){
		super(desc);
		roll3Values.add(fVal1);
		roll3Values.add(fVal2);
		roll3Values.add(fVal3);
	}
	
	@Override
	protected void action(Player currentPlayer) {
		// TODO Auto-generated method stub
		GameState game = GameState.getInstance();
		
		Cup cup = Cup.getInstance();
		List<FaceValue> roll3Dice = cup.roll3Dice();
		
		List<Player> playerList = game.getPlayerList();
		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "roll3");
		
		for(Player p:playerList){//award all the players except currentPlayer.
			int oldBalance = p.getBalance();
			if(!p.equals(currentPlayer))
				awardPlayer(p, roll3Dice, 1000);
			int newBalance = p.getBalance();
			Integer award = newBalance - oldBalance;
			String playerName = p.getName();
			if(award != 0)
				mapForUITransfer.put(playerName, award.toString());
				
		}
		
		int oldBalance = currentPlayer.getBalance();
		
		awardPlayer(currentPlayer, roll3Dice, 1500);//award currentPlayer
		
		int newBalance = currentPlayer.getBalance();
		Integer award = newBalance - oldBalance;
		String playerName = currentPlayer.getName();
		if(award != 0)
			mapForUITransfer.put(playerName, award.toString());
		
		game.publishToUIListeners(mapForUITransfer);//displays player names and their corresponding awards in UI.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
	}
	
	private void awardPlayer(Player p, List<FaceValue> roll3Dice, int threeMatchPrice){
		
		int newBalance = p.getBalance();
		
		List<Roll3Card> playerRoll3Cards = p.getRoll3Cards();
		
		int oneMatchPrice = 50;
		int twoMatchPrice = 200;
		
		for(Roll3Card r3Card:playerRoll3Cards){
			List<FaceValue> fValList = r3Card.roll3Values;
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

	private int nOfMatch(List<FaceValue> roll3Dice, List<FaceValue> fValList){
		int nOfMatch = 0;
		
		for(FaceValue fVal:fValList){
			if(roll3Dice.contains(fVal))
				nOfMatch++;
		}
		
		return nOfMatch;
	}
	
}
