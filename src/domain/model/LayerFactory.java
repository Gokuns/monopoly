package domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.model.specialSquares.CommunityChest;
import domain.model.specialSquares.IncomeTax;
import domain.model.specialSquares.TransitStation;
import domain.model.specialSquares.payCorners.Go;

public class LayerFactory {
	private static LayerFactory layerFactory;
	
	private LayerFactory() {
		
	}
	
	public static synchronized LayerFactory getInstance() {
		if(layerFactory == null) {
			layerFactory = new LayerFactory();
		}
		return layerFactory;
	}
	
	public List<Square> createLayer(String type) {
		List<Square> result = new ArrayList<Square>();
		if(type.equals("inner")) {
			
			
		}else if(type.equals("middle")) {
			result.add(Go.getInstance());
			result.add(new Street("Mediterranean Avenue", 60,"", "Purple", new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",2);
				put("oneHouse", 10);
				put("twoHouse", 30);
				put("threeHouse", 90);
				put("fourHouse", 160);
				put("hotel", 250);
				put("sky", 750);
				put("mortgage", 30);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}})));
			result.add(new CommunityChest("Community Chest", "Draw one Community Chest card from the deck"));
			result.add(new Street("Baltic Avenue", 60,"", "Purple", new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",4);
				put("oneHouse", 20);
				put("twoHouse", 60);
				put("threeHouse", 180);
				put("fourHouse", 320);
				put("hotel", 450);
				put("sky", 900);
				put("mortgage", 30);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}})));
			
			result.add(new IncomeTax("Income Tax", "Pay %10 or $200"));
			result.add(new TransitStation("Reading", "Odd roll moves forward, even roll changes layer"));
			result.add(new Street("Oriental Avenue", 60,"", "Light Blue", new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",6);
				put("oneHouse", 30);
				put("twoHouse", 90);
				put("threeHouse", 270);
				put("fourHouse", 400);
				put("hotel", 550);
				put("sky", 1050);
				put("mortgage", 50);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}})));
			result.add(new Street("Oriental Avenue", 60,"", "Light Blue", new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",6);
				put("oneHouse", 30);
				put("twoHouse", 90);
				put("threeHouse", 270);
				put("fourHouse", 400);
				put("hotel", 550);
				put("sky", 1050);
				put("mortgage", 50);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}})));
		}else if(type.equals("outer")) {
			
		}
		return result;
	}
	
}
