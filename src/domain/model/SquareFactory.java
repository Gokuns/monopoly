package domain.model;


//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

import domain.model.specialSquares.ChanceAction;
import domain.model.specialSquares.CommunityChest;
import domain.model.specialSquares.FreeParking;
//import domain.model.specialSquares.IncomeTax;
import domain.model.specialSquares.Jail;
import domain.model.specialSquares.RollThree;
import domain.model.specialSquares.TransitStation;
//import domain.model.specialSquares.payCorners.Go;

public class SquareFactory {
	private static SquareFactory squareFactory;
	//private DeedFactory deedFactory;
	
	private SquareFactory() {
		//this.deedFactory = DeedFactory.getInstance();
	}
	
	public static synchronized SquareFactory getInstance() {
		if(squareFactory == null) {
			squareFactory = new SquareFactory();
		}
		return squareFactory;
	}
	
<<<<<<< src/domain/model/SquareFactory.java
	public List<List<Square>> createSquares() {
		List<List<Square>> squares = new ArrayList<>();
		Square FreePark = new FreeParking("Free Parking", "desc2",1,3);
		Square Street1 = new Street("street 1", 100, "desc3", "color", null,1,0);
		Square Street2 = new Street("street 2", 120, "desc4", "color", null,1,1);
		Square Street3 = new Street("street 3", 150, "desc5", "color", null,1,2);
		List<Square> layer1 = new ArrayList<>();
		layer1.add(Go.getInstance());
		layer1.add(Street1);
		layer1.add(Street2);
		layer1.add(Street3);
		layer1.add(FreePark);
		squares.add(layer1);
		return squares;
=======
	public Square createSquare(String name) {
		if(name.equals("Roll3")) {
			return new RollThree("Roll3", "Draw a roll three card.");
		}else if(name.equals("Community Chest")){
			return new CommunityChest("Community Chest", "Draw a community chest card.");
		}else if(name.equals("Chance")){
			return new ChanceAction("Chance", "Draw a chance action card.");
		}else if(name.equals("Transit")){
			return new TransitStation("Transit", "You can transfer to another layer from here.");
		}else if(name.equals("Jail")){
			return new Jail("Jail", "Stay in or visit the jail.");
		}else
			return new FreeParking(name, "You can stay here for free!!!");
		}
>>>>>>> src/domain/model/SquareFactory.java
	}
