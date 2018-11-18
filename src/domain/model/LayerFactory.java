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
			
			
		}else if(type.equals("middle")) {
			result.add(Go.getInstance());
			result.add(squareFactory.createSquare("Mediterranean"));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare("Baltic"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Transit"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Jail"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Community Chest"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Free Parking"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Transit"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("CommunityChest"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare("Chance"));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
			result.add(squareFactory.createSquare(""));
		}else if(type.equals("outer")) {
			
		}
		return result;
	}
	
}
