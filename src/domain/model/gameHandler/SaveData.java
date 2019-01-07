package domain.model.gameHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import domain.model.cards.ChanceCard;
import domain.model.cards.CommunityChestCard;
import domain.model.cards.Deck;
import domain.model.cards.Roll3Card;
import domain.model.dice.Cup;
import domain.model.dice.FaceValue;
import domain.model.players.Player;
import domain.model.squares.properties.Property;
import domain.model.squares.properties.Street;

public class SaveData implements Serializable{
	private static SaveData data;
	HashMap<String, String> map;
	Gson g = new Gson();
	String dat = "";
	
	
	private SaveData() {
	}
	
	public static synchronized SaveData getInstance() {
		if(data==null) {
			
			data = new SaveData();
			
		}
		return data;
		
	}


	
	public JsonObject convertGameToSave(GameState game, Cup cup) {
		JsonObject result = new JsonObject();
		result.addProperty("num", game.getPlayerCount());
		result.addProperty("currentPlayer", game.getCurrentPlayer().getName());
		JsonArray orderedPlayers = new JsonArray();
	    for(Player p: game.getOrderedPlayerList()) {
	    	JsonObject player = new JsonObject();
		    player.addProperty("name",p.getName());
		    player.addProperty("id",p.getID());
		    player.addProperty("balance", p.getBalance());
		    player.addProperty("layer", Board.getInstance().getSquareLayerIndex(p.getPiece().getCurrentSquare()));
		    player.addProperty("index", Board.getInstance().getSquareIndex(p.getPiece().getCurrentSquare()));
		    JsonArray props = new JsonArray();
		    for(Property prop:p.getPrList()) {
		    	Street pr = (Street) prop;
		    	JsonObject property = new JsonObject();
		    	property.addProperty("name", pr.getName());
		    	property.addProperty("isMortgaged",  pr.isMortgaged());
		    	property.addProperty("houseCount", pr.getHouseCount());
		    	property.addProperty("hasHotel", pr.isHasHotel());
		    	property.addProperty("hasSkyscraper", pr.isHasSkyscraper());
		    	
		    	props.add(property);	
		    }
		    player.add("props", props);
		    player.addProperty("isTurn", p.isTurn());
		    player.addProperty("inJail", p.isInJail());
		    player.addProperty("rolledDouble", p.isRolledDouble());
		    player.addProperty("hasMoved", p.isMoved());
		    player.addProperty("hasRolled", p.isRolled());
		    player.addProperty("isBankrupt", p.isBankrupt());
		    player.addProperty("doubleRollCounter", p.getDoubleRollCounter());
		    player.addProperty("rolledTriple", p.isRolledTriple());
		    player.addProperty("rolledMrMonopoly", p.isRolledMrMonopoly());
		    player.addProperty("rolledBus", p.isRolledBus());
		    player.addProperty("hasPaused", p.hasPaused());
		    player.addProperty("enableBuy",  p.isEnableBuy());
		    JsonArray chance = new JsonArray();
		    for(ChanceCard c :p.getChanceCards()) {
		    	props.add(c.getName());
		    }
		    player.add("chance", chance);
		    JsonArray chest = new JsonArray();
		    for(CommunityChestCard c:p.getCommunityCards()) {
		    	props.add(c.getName());
		    }
		    player.add("communityChest", chest);
		    JsonArray roll3 = new JsonArray();
		    for(Roll3Card c:p.getRoll3Cards()) {
		    	props.add(c.getName());
		    }
		    player.add("roll3", roll3);
		    player.addProperty("changingLayer", p.isChangingLayer());
		    orderedPlayers.add(player);
	    }
	    result.add("orderedPlayerList", orderedPlayers);
	    List<FaceValue> lst = cup.getFaceValues();
	    for (int i = 0; i < lst.size(); i++) {
		    result.addProperty("faceValue"+i, lst.get(i).toString());
		}

		
		return result;
	}
	public void converDataToGame(JsonObject json, GameState game, Cup cup) {
		JsonArray orderedLst =json.get("orderedPlayerList").getAsJsonArray();
		ArrayList<Player> lst = new ArrayList<Player>();
		Board bd = Board.getInstance();
		
		
		for(JsonElement element:orderedLst) {
			JsonObject p = (JsonObject) element;
			Player player = new Player(p.get("name").getAsString(),  p.get("id").getAsInt());
			player.setBalance(p.get("balance").getAsInt());
			int layer = p.get("layer").getAsInt();
			int index = p.get("index").getAsInt();
			player.getPiece().setCurrentSquare(bd.getSquares().get(layer).get(index));
			JsonArray props = p.get("props").getAsJsonArray();
			ArrayList<Property> prLst = new ArrayList<>();
			for(JsonElement a:props) {
				JsonObject prop = (JsonObject) a;
				String nameOfProp = prop.get("name").getAsString();
				boolean isMortgaged = prop.get("isMotgaged").getAsBoolean();
				int houseCount = prop.get("houseCount").getAsInt();
				boolean hasHotel = prop.get("hasHotel").getAsBoolean();
				boolean hasSkyscraper = prop.get("hasSkyscraper").getAsBoolean();
				Street st =  (Street) bd.findSquare(nameOfProp, bd.getSquares());
				st.setMortgaged(isMortgaged);
				st.setHouseCount(houseCount);
				st.setHasHotel(hasHotel);
				st.setHasSkyscraper(hasSkyscraper);
				prLst.add((Property) bd.findSquare(nameOfProp, bd.getSquares()));
			}
			player.setPrList(prLst);
			player.setTurn(p.get("isTurn").getAsBoolean());
			player.setInJail(p.get("inJail").getAsBoolean());
			player.setRolledDouble(p.get("rolledDouble").getAsBoolean());
			player.setMoved(p.get("hasMoved").getAsBoolean());
			player.setRolled(p.get("hasRolled").getAsBoolean());
			player.setBankrupt(p.get("isBankrupt").getAsBoolean());
			player.setDoubleRollCounter(p.get("doubleRollCounter").getAsInt());
			player.setRolledTriple(p.get("rolledTriple").getAsBoolean());
			player.setRolledMrMonopoly(p.get("rolledMrMonopoly").getAsBoolean());
			player.setRolledBus(p.get("rolledBus").getAsBoolean());
			player.setHasPaused(p.get("hasPaused").getAsBoolean());
			player.setEnableBuy(p.get("enableBuy").getAsBoolean());
			
			
			JsonArray chances = p.get("chance").getAsJsonArray();
			ArrayList<ChanceCard> chanceCards = new ArrayList<>();
			for(JsonElement a:chances) {
				JsonObject chance = (JsonObject) a;
				String nameOfCard = chance.getAsString();
				chanceCards.add( (ChanceCard) new Deck("chance").findCard(nameOfCard));
			}
			player.setChanceCards(chanceCards);
			
			JsonArray communities = p.get("communityChest").getAsJsonArray();
			ArrayList<CommunityChestCard> communityCards = new ArrayList<>();
			for(JsonElement a:communities) {
				JsonObject community = (JsonObject) a;
				String nameOfCard = community.getAsString();
				communityCards.add(  (CommunityChestCard) new Deck("community").findCard(nameOfCard));
			}
			player.setCommunityCards(communityCards);
			
			JsonArray roll3s = p.get("roll3").getAsJsonArray();
			ArrayList<Roll3Card> roll3Cards = new ArrayList<>();
			for(JsonElement a:roll3s) {
				JsonObject roll3 = (JsonObject) a;
				String nameOfCard = roll3.getAsString();
				roll3Cards.add(  (Roll3Card) new Deck("roll3").findCard(nameOfCard));
			}
			player.setRoll3Cards(roll3Cards);
			player.setChangingLayer(p.get("changingLayer").getAsBoolean());
			lst.add(player);
		}
		game.setOrderedPlayerList(lst);
		String name = json.get("currentPlayer").getAsString();
		Player p = game.findPlayer(name);
		game.setCurrentPlayer(p);
		ArrayList<FaceValue> faceValues = new ArrayList<FaceValue>();
		for (int i = 0; i < 3; i++) {
			faceValues.add(FaceValue.valueOf(json.get("faceValue"+i).getAsString()));
		}

		Cup.getInstance().setFaceValues(faceValues);
	}
	

}
