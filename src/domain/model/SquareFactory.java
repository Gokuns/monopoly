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
		}else if(name.equals("Mediterranean Avenue")){
			return new Street(name, 60,  "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		}else if(name.equals("Baltic Avenue")){
			return new Street(name, 60,  "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		}else if(name.equals("Oriental Avenue")){
			return new Street(name, 100,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}else if(name.equals("Vermont Avenue")){
			return new Street(name, 100,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}else if(name.equals("Connecticut Avenue")){
			return new Street(name, 120,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}else if(name.equals("St. Charles Place")){
			return new Street(name, 140,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}else if(name.equals("States Avenue")){
			return new Street(name, 140,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}else if(name.equals("Virginia Avenue")){
			return new Street(name, 160,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}else if(name.equals("St. James Place")){
			return new Street(name, 180,  "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		}else if(name.equals("Tennessee Avenue")){
			return new Street(name, 180,  "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		}else if(name.equals("New York Avenue")){
			return new Street(name, 200,  "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		}else if(name.equals("Kentucky Avenue")){
			return new Street(name, 220,  "A nice place to buy.", "Red", deedFactory.createDeed(name));
		}else if(name.equals("Indiana Avenue")){
			return new Street(name, 220,  "A nice place to buy.", "Red", deedFactory.createDeed(name));
		}else if(name.equals("Illinois Avenue")){
			return new Street(name, 240,  "A nice place to buy.", "Red", deedFactory.createDeed(name));
		}else if(name.equals("Atlantic Avenue")){
			return new Street(name, 260,  "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		}else if(name.equals("Ventnor Avenue")){
			return new Street(name, 260,  "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		}else if(name.equals("Marvin Garden")){
			return new Street(name, 260,  "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		}else if(name.equals("Pacific Avenue")){
			return new Street(name, 300,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}else if(name.equals("North Carolina Avenue")){
			return new Street(name, 300,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}else if(name.equals("Pennsylvania Avenue")){
			return new Street(name, 320,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}else if(name.equals("Park Place")){
			return new Street(name, 320,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}else if(name.equals("Boardwalk")){
			return new Street(name, 400,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}else if(name.equals("Transit")){
			return new TransitStation(name,  "Passengers wishing to use the metrobus line should change at Sisli Mecidiyekoy or Gayrettepe stations.");
		}else if(name.equals("Pennsylvania Railroad")){
			return new Railroad(name, 200,  "A nice place to buy.", deedFactory.createDeed(name));
		}else if(name.equals("Reading Railroad")){
			return new Railroad(name, 200,  "A nice place to buy.", deedFactory.createDeed(name));
		}else if(name.equals("BnO Raidroad")){
			return new Railroad(name, 200,  "A nice place to buy.", deedFactory.createDeed(name));
		}else if(name.equals("Short Railroad")){
			return new Railroad(name, 200,  "A nice place to buy.", deedFactory.createDeed(name));
		}else if(name.equals("Biscayne Avenue")){
			return new Street(name, 150,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}else if(name.equals("Miami Avenue")){
			return new Street(name, 150,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}
		
		else
			return new FreeParking(name, "You can stay here for free!!!");
		}
	}
