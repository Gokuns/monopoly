package domain.model;


import domain.model.specialSquares.BirthdayGift;
import domain.model.specialSquares.BusTicket;

//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;

import domain.model.specialSquares.ChanceAction;
import domain.model.specialSquares.CommunityChest;
import domain.model.specialSquares.FreeParking;
import domain.model.specialSquares.GoToJail;
import domain.model.specialSquares.HollandTunnel;
import domain.model.specialSquares.IncomeTax;
//import domain.model.specialSquares.IncomeTax;
import domain.model.specialSquares.Jail;
import domain.model.specialSquares.LuxuryTax;
import domain.model.specialSquares.ReverseDirection;
import domain.model.specialSquares.RollThree;
import domain.model.specialSquares.SqueezePlay;
import domain.model.specialSquares.Subway;
import domain.model.specialSquares.TransitStation;
//import domain.model.specialSquares.payCorners.Go;
import domain.model.specialSquares.payCorners.Bonus;
import domain.model.specialSquares.payCorners.PayDay;

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
		}
		else if(name.equals("Community Chest")){
			return new CommunityChest("Community Chest", "Draw a community chest card.");
		}
		else if(name.equals("Chance")){
			return new ChanceAction("Chance", "Draw a chance action card.");
		}
		else if(name.equals("Transit")){
			return new TransitStation("Transit", "You can transfer to another layer from here.");
		}
		else if(name.equals("Jail")){
			return new Jail("Jail", "Stay in or visit the jail.");
		}
		else if(name.equals("Mediterranean Avenue")){
			return new Street(name, 60,  "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		}
		else if(name.equals("Baltic Avenue")){
			return new Street(name, 60,  "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		}
		else if(name.equals("Oriental Avenue") || name.equals("Vermont Avenue")){
			return new Street(name, 100,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}
		else if(name.equals("Connecticut Avenue")){
			return new Street(name, 120,  "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		}
		else if(name.equals("St. Charles Place") || name.equals("States Avenue")){
			return new Street(name, 140,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}
		else if(name.equals("Virginia Avenue")){
			return new Street(name, 160,  "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		}
		else if(name.equals("St. James Place") || name.equals("Tennessee Avenue")){
			return new Street(name, 180,  "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		}
		else if(name.equals("New York Avenue")){
			return new Street(name, 200,  "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		}
		else if(name.equals("Kentucky Avenue") || name.equals("Indiana Avenue")){
			return new Street(name, 220,  "A nice place to buy.", "Red", deedFactory.createDeed(name));
		}
		else if(name.equals("Illinois Avenue")){
			return new Street(name, 240,  "A nice place to buy.", "Red", deedFactory.createDeed(name));
		}
		else if(name.equals("Atlantic Avenue") || name.equals("Ventnor Avenue")){
			return new Street(name, 260,  "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		}
		else if(name.equals("Marvin Garden")){
			return new Street(name, 260,  "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		}
		else if(name.equals("Pacific Avenue") || name.equals("North Carolina Avenue")){
			return new Street(name, 300,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}
		else if(name.equals("Pennsylvania Avenue")){
			return new Street(name, 320,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}
		else if(name.equals("Park Place")){
			return new Street(name, 320,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}
		else if(name.equals("Boardwalk")){
			return new Street(name, 400,  "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		}
		else if(name.equals("Transit")){
			return new TransitStation(name,  "Passengers wishing to use the metrobus line should change at Sisli Mecidiyekoy or Gayrettepe stations.");
		}
		else if (name.equals("Pennsylvania Railroad") || name.equals("Reading Railroad") ||
				name.equals("BnO Raidroad") || name.equals("Short Railroad")){
			return new Railroad(name, 200,  "A nice place to buy.", deedFactory.createDeed(name));
		}
		else if(name.equals("Biscayne Avenue")){
			return new Street(name, 150,  "A nice place to buy.", "Dark Orange", deedFactory.createDeed(name));
		}
		else if(name.equals("Miami Avenue") || name.equals("Florida Avenue")){
			return new Street(name, 150,  "A nice place to buy.", "Dark Orange", deedFactory.createDeed(name));
		}
		else if(name.equals("Lombard Street") || name.equals("The Embarcadero")){
			return new Street(name, 210,  "A nice place to buy.", "White", deedFactory.createDeed(name));
		}
		else if(name.equals("Fisherman's Wharf")){
			return new Street(name, 250,  "A nice place to buy.", "White", deedFactory.createDeed(name));
		}
		else if(name.equals("Beacon Street") || name.equals("Boylston Street")){
			return new Street(name, 330,  "A nice place to buy.", "Black", deedFactory.createDeed(name));
		}
		else if(name.equals("Newbury Street")){
			return new Street(name, 380,  "A nice place to buy.", "Black", deedFactory.createDeed(name));
		}
		else if(name.equals("Fifth Avenue") || name.equals("Madison Avenue")){
			return new Street(name, 430,  "A nice place to buy.", "Gray", deedFactory.createDeed(name));
		}
		else if(name.equals("Wall Street")){
			return new Street(name, 500,  "A nice place to buy.", "Gray", deedFactory.createDeed(name));
		}
		else if(name.equals("Lake Street") || name.equals("Nicollet Avenue")){
			return new Street(name, 30,  "A nice place to buy.", "Pink", deedFactory.createDeed(name));
		}
		else if(name.equals("Hennepin Avenue")){
			return new Street(name, 60,  "A nice place to buy.", "Pink", deedFactory.createDeed(name));
		}
		else if(name.equals("Esplanade Avenue") || name.equals("Canal Street")){
			return new Street(name, 90,  "A nice place to buy.", "Light Green", deedFactory.createDeed(name));
		}
		else if(name.equals("Magazine Street") || name.equals("Bourbon Street")){
			return new Street(name, 120,  "A nice place to buy.", "Light Green", deedFactory.createDeed(name));
		}
		else if(name.equals("Katy Freeway") || name.equals("WestHeimer Road")){
			return new Street(name, 150,  "A nice place to buy.", "Light Yellow", deedFactory.createDeed(name));
		}
		else if(name.equals("Kirby Drive") || name.equals("Cullen Boulevard")){
			return new Street(name, 180,  "A nice place to buy.", "Light Yellow", deedFactory.createDeed(name));
		}
		else if(name.equals("Dekalb Avenue") || name.equals("Andrew Young Intl Boulevard")){
			return new Street(name, 210,  "A nice place to buy.", "Turqoise", deedFactory.createDeed(name));
		}
		else if(name.equals("Decatur Street") || name.equals("Peachtree Street")){
			return new Street(name, 240,  "A nice place to buy.", "Turqoise", deedFactory.createDeed(name));
		}
		else if(name.equals("Randolph Street") || name.equals("Lake Shore Drive")){
			return new Street(name, 270,  "A nice place to buy.", "Wine Red", deedFactory.createDeed(name));
		}
		else if(name.equals("Wacker Drive") || name.equals("Michigan Avenue")){
			return new Street(name, 300,  "A nice place to buy.", "Wine Red", deedFactory.createDeed(name));
		}
		else if(name.equals("South Temple") || name.equals("West Temple")){
			return new Street(name, 330,  "A nice place to buy.", "Dark Yellow", deedFactory.createDeed(name));
		}
		else if(name.equals("North Temple") || name.equals("Temple Square")){
			return new Street(name, 360,  "A nice place to buy.", "Dark Yellow", deedFactory.createDeed(name));
		}
		else if(name.equals("South Street") || name.equals("Broad Street")){
			return new Street(name, 390,  "A nice place to buy.", "Tan", deedFactory.createDeed(name));
		}
		else if(name.equals("Walnut Street") || name.equals("Market Street")){
			return new Street(name, 420,  "A nice place to buy.", "Tan", deedFactory.createDeed(name));
		}
		else if(name.equals("Mulholland Drive")){
			return new Street(name, 450,  "A real nice place to buy.", "Dark Red", deedFactory.createDeed(name));
		}
		else if(name.equals("Ventura Boulevard")){
			return new Street(name, 480,  "A real nice place to buy.", "Dark Red", deedFactory.createDeed(name));
		}
		else if(name.equals("Rodeo Drive")){
			return new Street(name, 510,  "A real nice place to buy.", "Dark Red", deedFactory.createDeed(name));
		}
		else if(name.equals("Checker Cab Co.") || name.equals("Black n WhiteCab Co.") ||
				name.equals("Yellow Cab Co.") || name.equals("Ute Cab Co.")){
			return new CabCo(name, 300,  "A Cab Company to buy", deedFactory.createDeed(name));
		}
		else if(name.equals("Water Works") || name.equals("Cable Company") ||
				name.equals("Electric Company") || name.equals("Internet Service Provider") ||
				name.equals("Gas Company") || name.equals("Telephone Company") ||
				name.equals("Trash Collector") || name.equals("Sewage System")){
			return new Company(name, 150,  "A Cab Company to buy", deedFactory.createDeed(name));
		}
		else if(name.equals("Income Tax")){
			return new IncomeTax(name,  "Pay 10% or $200");
		}
		else if(name.equals("Go To Jail")){
			return new GoToJail(name,  "Go Directly to Jail");
		}
		else if(name.equals("Luxury Tax")){
			return new LuxuryTax(name,  "Pay $75");
		}
		else if(name.equals("Squeeze Play")){
			return new SqueezePlay(name,  "If your roll equal those numbers, collect the corresponding vale from each player");
		}
		else if(name.equals("Bonus")){
			return new Bonus(name,  "A pay corner");
		}
		else if(name.equals("Holland Tunnel")){
			return new HollandTunnel(name,  "Go to the opposite tunnel");
		}
		else if(name.equals("Reverse Direction")){
			return new ReverseDirection(name,  "Go in reverse direction next turn.");
		}
		else if(name.equals("Tax Refund")){
			return new GoToJail(name,  "Collect 50% from pool");
		}
		else if(name.equals("Bus Ticket")){
			return new BusTicket(name,  "Draw a bus card and keep");
		}
		//else if(name.equals("Auction")){
		//	return new GoToJail(name,  "A pay corner");
		//}
		else if(name.equals("Pay Day")){
			return new PayDay(name,  "A pay corner");
		}
		else if(name.equals("Subway")){
			return new Subway(name,  "A pay corner");
		}
		else if(name.equals("Birthday Gift")){
			return new BirthdayGift(name,  "A pay corner");
		}
		

		else
			return new FreeParking(name, "You can stay here for free!!!");
	}
}
