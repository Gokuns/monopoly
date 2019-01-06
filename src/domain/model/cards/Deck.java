package domain.model.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.model.cards.chanceCards.ChairPerson;
import domain.model.cards.chanceCards.GoToJailCard;
import domain.model.cards.chanceCards.Hurricane;
import domain.model.cards.chanceCards.SchoolFees;
import domain.model.cards.chanceCards.TrafficTicket;
import domain.model.cards.communityChestCards.DoctorsFee;
import domain.model.cards.communityChestCards.InheritHundredDollars;
import domain.model.cards.communityChestCards.OpeningNightTickets;
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
		//Collections.shuffle(cards);//shuffle cards so as to obtain a randomized drawing order...
	}
	
	private void initializeCommunityCards(List<Card> cards){//creating & adding a sample community chest card to the deck
		
		cards.add(new PayHospitalBillsCard("payHospitalBillsCard", "Pay hospital bills.\nPay $100 "));
		cards.add(new OpeningNightTickets("openingNightTicketCard", "Collect $50 from each player."));
		cards.add(new DoctorsFee("doctorsFeeCard", "Pay $50 to the pool."));
		cards.add(new InheritHundredDollars("inheritHundredDollarsCard", "Collect $100 from the bank."));
	
		
	}
	
	private void initializeChanceCards(List<Card> cards){//creating & adding a sample chance action card to the deck

		//cards.add(new GoToJailCard("goToJailCard", "Goto jail immediately."));
		cards.add(new SchoolFees("schoolFeesCard", "Pay $150 to the pool."));
		cards.add(new TrafficTicket("trafficTicket", "pay $15 to the pool."));
		cards.add(new ChairPerson("ChairPersonCard", "Give every player $50."));
//		cards.add(new Hurricane("HurricaneCard", "A hurricane has come."));
		
	}
	
	private void initializeRoll3Cards(List<Card> cards){//creating & adding a sample roll three card to the deck
		cards.add(new Roll3Card("roll3Card", FaceValue.ONE, FaceValue.TWO, FaceValue.THREE, "1\t2\t3\t"));
		cards.add(new Roll3Card("roll3Card", FaceValue.SIX, FaceValue.TWO, FaceValue.FIVE, "6\t2\t5\t"));
		cards.add(new Roll3Card("roll3Card", FaceValue.THREE, FaceValue.FOUR, FaceValue.THREE, "3\t4\t3\t"));
		cards.add(new Roll3Card("roll3Card", FaceValue.FIVE, FaceValue.ONE, FaceValue.SIX, "5\t1\t6\t"));
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
		cards.remove(0); //remove the drawn card from the deck.
		return card;
	}
	
	public Card findCard(String name) {
		Card result=null;
		for(Card c:cards) {
			if(c.getName().equals(name)) {
				result = c;
			}
		}
		return result;
	}
}
