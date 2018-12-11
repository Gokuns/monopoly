package domain.model.squares;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

public class LayerFactory {
	private static LayerFactory layerFactory;
	private SquareFactory squareFactory;
	
	
	private LayerFactory() {
		this.squareFactory = SquareFactory.getInstance();
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
			result.add(squareFactory.createSquare("Squeeze Play"));
			result.add(squareFactory.createSquare("The Embarcadero"));
			result.add(squareFactory.createSquare("Fisherman's Wharf"));
			result.add(squareFactory.createSquare("Telephone Company"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Beacon Street"));
			result.add(squareFactory.createSquare("Bonus"));
			result.add(squareFactory.createSquare("Boylston Street"));
			result.add(squareFactory.createSquare("Newbury Street"));
			result.add(squareFactory.createSquare("Transit"));
			result.add(squareFactory.createSquare("Fifth Avenue"));
			result.add(squareFactory.createSquare("Madison Avenue"));
			result.add(squareFactory.createSquare("Stock Exchange"));
			result.add(squareFactory.createSquare("Wall Street"));
			result.add(squareFactory.createSquare("Tax Refund"));
			result.add(squareFactory.createSquare("Gas Company"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Florida Avenue"));
			result.add(squareFactory.createSquare("Holland Tunnel"));
			result.add(squareFactory.createSquare("Miami Avenue"));
			result.add(squareFactory.createSquare("Biscayne Avenue"));
			result.add(squareFactory.createSquare("Transit"));
			result.add(squareFactory.createSquare("Reverse Direction"));
			result.add(squareFactory.createSquare("Lombard Street"));
			
			
		}else if(type.equals("middle")) {
			result.add(squareFactory.createSquare("Go"));
			result.add(squareFactory.createSquare("Mediterranean Avenue"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Baltic Avenue"));
			result.add(squareFactory.createSquare("Income Tax"));
			result.add(squareFactory.createSquare("Transit"));
			result.add(squareFactory.createSquare("Oriental Avenue"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Vermont Avenue"));
			result.add(squareFactory.createSquare("Connecticut Avenue"));
			result.add(squareFactory.createSquare("Jail"));
			result.add(squareFactory.createSquare("St. Charles Place"));
			result.add(squareFactory.createSquare("Electric Company"));
			result.add(squareFactory.createSquare("States Avenue"));
			result.add(squareFactory.createSquare("Virginia Avenue"));
			result.add(squareFactory.createSquare("Pennsylvania Railroad"));
			result.add(squareFactory.createSquare("St. James Place"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Tennessee Avenue"));
			result.add(squareFactory.createSquare("New York Avenue"));
			result.add(squareFactory.createSquare("Free Parking"));
			result.add(squareFactory.createSquare("Kentucky Avenue"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Indiana Avenue"));
			result.add(squareFactory.createSquare("Illinois Avenue"));
			result.add(squareFactory.createSquare("Transit"));
			result.add(squareFactory.createSquare("Atlantic Avenue"));
			result.add(squareFactory.createSquare("Ventnor Avenue"));
			result.add(squareFactory.createSquare("Water Works"));
			result.add(squareFactory.createSquare("Marvin Garden"));
			result.add(squareFactory.createSquare("Roll Three"));
			result.add(squareFactory.createSquare("Pacific Avenue"));
			result.add(squareFactory.createSquare("North Carolina Avenue"));
			result.add(squareFactory.createSquare("CommunityChest"));
			result.add(squareFactory.createSquare("Pennsylvania Avenue"));
			result.add(squareFactory.createSquare("Short Line Railroad"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Park Place"));
			result.add(squareFactory.createSquare("Luxury Tax"));
			result.add(squareFactory.createSquare("Boardwalk"));
		}else if(type.equals("outer")) {
			result.add(squareFactory.createSquare("Subway"));
			result.add(squareFactory.createSquare("Lake Street"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Nicollet Avenue"));
			result.add(squareFactory.createSquare("Hennepin Avenue"));
			result.add(squareFactory.createSquare("Bus Ticket"));
			result.add(squareFactory.createSquare("Checker Cab Co."));
			result.add(squareFactory.createSquare("Reading Railroad"));
			result.add(squareFactory.createSquare("Esplanade Avenue"));
			result.add(squareFactory.createSquare("Canal Street"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Cable Company"));
			result.add(squareFactory.createSquare("Magazine Street"));
			result.add(squareFactory.createSquare("Bourbon Street"));
			result.add(squareFactory.createSquare("Holland Tunnel"));
			result.add(squareFactory.createSquare("Auction"));
			result.add(squareFactory.createSquare("Katy Freeway"));
			result.add(squareFactory.createSquare("Westheimer Road"));
			result.add(squareFactory.createSquare("Internet Service Provider"));
			result.add(squareFactory.createSquare("Kirby Drive"));
			result.add(squareFactory.createSquare("Cullen Boulevard"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Black n White Cab Co."));
			result.add(squareFactory.createSquare("Dekalb Avenue"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Andrew Young Intl Boulevard"));
			result.add(squareFactory.createSquare("Decatur Street"));
			result.add(squareFactory.createSquare("Peachtree Street"));
			result.add(squareFactory.createSquare("Pay Day"));
			result.add(squareFactory.createSquare("Randolph Street"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Lake Shore Drive"));
			result.add(squareFactory.createSquare("Wacker Drive"));
			result.add(squareFactory.createSquare("Michigan Avenue"));
			result.add(squareFactory.createSquare("Yellow Cab Co."));
			result.add(squareFactory.createSquare("BnO Railroad"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("South Temple"));
			result.add(squareFactory.createSquare("West Temple"));
			result.add(squareFactory.createSquare("Trash Collector"));
			result.add(squareFactory.createSquare("North Temple"));
			result.add(squareFactory.createSquare("Temple Square"));
			result.add(squareFactory.createSquare("Go To Jail"));
			result.add(squareFactory.createSquare("South Street"));
			result.add(squareFactory.createSquare("Broad Street"));
			result.add(squareFactory.createSquare("Walnut Street"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Market Street"));
			result.add(squareFactory.createSquare("Bus Ticket"));
			result.add(squareFactory.createSquare("Sewage System"));
			result.add(squareFactory.createSquare("Ute Cab Co."));
			result.add(squareFactory.createSquare("Birthday Gift"));
			result.add(squareFactory.createSquare("Muholland Drive"));
			result.add(squareFactory.createSquare("Ventura Boulevard"));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare("Rodeo Drive"));
		}
		return result;
	}
	
}
