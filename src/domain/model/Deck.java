package domain.model;

import java.util.List;

import domain.model.chanceCards.GoToJailCard;
import domain.model.communityChestCards.PayHospitalBillsCard;
import domain.model.dice.faceValue;
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
	
	private void initializeCommunityCards(List<Card> cards){
		Card c = new PayHospitalBillsCard();
		cards.add(c);
	}
	
	private void initializeChanceCards(List<Card> cards){
		Card c = new GoToJailCard();
		cards.add(c);
	}
	
	private void initializeRoll3Cards(List<Card> cards){
		Card c = new Roll3Card(faceValue.ONE, faceValue.TWO, faceValue.THREE);
		cards.add(c);
	}
	
	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private Card draw(){
		Card card = cards.get(0);
		return card;
	}
}
