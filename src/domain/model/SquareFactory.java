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
		}else if(name.equals("Mediterranean")){
			return new Street("Mediterranean Avenue", 60,  "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		}else if(name.equals("Baltic")){
			return new Street("Baltic Avenue", 60,  "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		}else if(name.equals("Oritenal")){
			return new Street("Oriental Avenue", 100,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}else if(name.equals("Vermont")){
			return new Street("Vermont Avenue", 100,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}else if(name.equals("Connecticut")){
			return new Street("Vermont Avenue", 120,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}else if(name.equals("StCharles")){
			return new Street("St. Charles Place", 140,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}else if(name.equals("StCharles")){
			return new Street("States Avenue", 140,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}else if(name.equals("StCharles")){
			return new Street("States Avenue", 140,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}
		else
			return new FreeParking(name, "You can stay here for free!!!");
		}
	}
