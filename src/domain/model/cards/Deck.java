package domain.model.cards;

import java.util.ArrayList;
import java.util.List;

import domain.model.cards.chanceCards.GoToJailCard;
import domain.model.cards.communityChestCards.PayHospitalBillsCard;
import domain.model.dice.FaceValue;
import domain.model.players.Player;

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
		Card c = new PayHospitalBillsCard("Pay hospital bills.\nPay $100 ");
		cards.add(c);
	}
	
	private void initializeChanceCards(List<Card> cards){//creating & adding a sample chance action card to the deck
<<<<<<< HEAD
		Card c = new GoToJailCard("Chance card\n Go to jail immediately.");
=======
		Card c = new GoToJailCard("Chance card\nGo immediately to Jail.");
>>>>>>> refs/remotes/origin/Onat
		cards.add(c);
	}
	
	private void initializeRoll3Cards(List<Card> cards){//creating & adding a sample roll three card to the deck
		Card c = new Roll3Card(FaceValue.ONE, FaceValue.TWO, FaceValue.THREE, "1\t2\t3\t");
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
	
	public Card draw(Player p){//drawing a card from the top of the deck & conducting its corresponding action.
		Card card = cards.get(0);
		card.action(p);
		return card;
	}
}
