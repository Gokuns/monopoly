package domain.model;

import java.util.List;

import domain.model.communityChestCards.payHospitalBills;
import domain.model.specialSquares.ChanceAction;
import domain.model.specialSquares.CommunityChest;

public class Deck {
	
	private List<Card> cards;
	private String type;
	
	public Deck(String type){
		
		this.type = type;
		
		if(type.equals("chance")){
			initializeChanceCards(cards);
		}else if(type.equals("community")){
			initializeCommunityCards(cards);
		}else if(type.equals("roll3")){
			initializeRoll3Cards(cards);
		}
	}
	
	private Card draw(){
		Card card = cards.get(0);
		return card;
	}
	
	private void initializeCommunityCards(List<Card> cards){
		Card c = new payHospitalBills();
		cards.add(c);
	}
	
	private void initializeChanceCards(List<Card> cards){
		Card c = new payHospitalBills();
		cards.add(c);
	}
	
	private void initializeRoll3Cards(List<Card> cards){
		Card c = new payHospitalBills();
		cards.add(c);
	}
}
