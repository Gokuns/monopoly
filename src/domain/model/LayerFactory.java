package domain.model;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

//import domain.model.specialSquares.CommunityChest;
//import domain.model.specialSquares.IncomeTax;
//import domain.model.specialSquares.TransitStation;
import domain.model.specialSquares.payCorners.Go;

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
			result.add(squareFactory.createSquare("Roll Three"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			
			
		}else if(type.equals("middle")) {
			result.add(Go.getInstance());
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
			result.add(squareFactory.createSquare("Go To Jail"));
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
			
		}
		return result;
	}
	
}
