package domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import domain.model.specialSquares.CommunityChest;
import domain.model.specialSquares.FreeParking;
import domain.model.specialSquares.IncomeTax;
import domain.model.specialSquares.TransitStation;
import domain.model.specialSquares.payCorners.Go;

public class SquareFactory {
	private static SquareFactory squareFactory;
	private DeedFactory deedFactory;
	
	private SquareFactory() {
		this.deedFactory = DeedFactory.getInstance();
	}
	
	public static synchronized SquareFactory getInstance() {
		if(squareFactory == null) {
			squareFactory = new SquareFactory();
		}
		return squareFactory;
	}
	
	public Square createSquare(String name) {
		if(name.equals("Mediterranean")) {
			return new Street("Mediterranean Avenue", 60,"", "Purple", deedFactory.createDeed(name));
		}
	else return null;
	}

}
