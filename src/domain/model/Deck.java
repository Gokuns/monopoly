package domain.model;

import java.util.ArrayList;
import java.util.List;

import domain.model.chanceCards.GoToJailCard;
import domain.model.communityChestCards.PayHospitalBillsCard;
import domain.model.dice.faceValue;

public class Deck {
	
	private List<Card> cards;
	private String type;
	
	public Deck(String type){
		
		this.type = type;
		this.cards = new ArrayList<Card>();
		
		if(type.equals("chance")){
			initializeChanceCards(cards);
		}else if(type.equals("community")){
			initializeCommunityCards(cards);
		}else if(type.equals("roll3")){
			initializeRoll3Cards(cards);
		}
	}
	
	private void initializeCommunityCards(List<Card> cards){//creating & adding a sample community chest card to the deck
		Card c = new PayHospitalBillsCard();
		cards.add(c);
	}
	
	private void initializeChanceCards(List<Card> cards){//creating & adding a sample chance action card to the deck
		Card c = new GoToJailCard();
		cards.add(c);
	}
	
	private void initializeRoll3Cards(List<Card> cards){//creating & adding a sample roll three card to the deck
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
	
	public Card draw(){//drawing a card from the top of the deck & conducting its corresponding action.
		Card card = cards.get(0);
		card.action();
		return card;
	}
}
