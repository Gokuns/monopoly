package domain.model.squares;

import domain.model.squares.specialSquares.BirthdayGift;
import domain.model.squares.specialSquares.BusTicket;
import domain.model.squares.specialSquares.ChanceAction;
import domain.model.squares.specialSquares.CommunityChest;
import domain.model.squares.specialSquares.GoToJail;
import domain.model.squares.specialSquares.HollandTunnel;
import domain.model.squares.specialSquares.IncomeTax;
import domain.model.squares.specialSquares.Jail;
import domain.model.squares.specialSquares.LuxuryTax;
import domain.model.squares.specialSquares.ReverseDirection;
import domain.model.squares.specialSquares.RollThree;
import domain.model.squares.specialSquares.SqueezePlay;
import domain.model.squares.specialSquares.StockExchange;
import domain.model.squares.specialSquares.Subway;
import domain.model.squares.specialSquares.payCorners.Bonus;
import domain.model.squares.specialSquares.payCorners.PayDay;

public class SquareFactory {
	private static SquareFactory squareFactory;
	private DeedFactory deedFactory;

	private SquareFactory() {
		this.deedFactory = DeedFactory.getInstance();
	}

	public static synchronized SquareFactory getInstance() {
		if (squareFactory == null) {
			squareFactory = new SquareFactory();
		}
		return squareFactory;
	}

	public Square createSquare(String name) {
		Square result;
		if (name.equals("Roll Three")) {
			result = new RollThree(name, "Draw a roll three card.");
		} else if (name.equals("Community Chest")) {
			result = new CommunityChest(name, "Draw a community chest card.");
		} else if (name.equals("Chance")) {
			result = new ChanceAction(name, "Draw a chance action card.");
		} else if (name.equals("Jail")) {
			result = new Jail(name, "Stay in or visit the jail.");
		} else if (name.equals("Mediterranean Avenue")) {
			result = new Street(name, 60, "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		} else if (name.equals("Baltic Avenue")) {
			result = new Street(name, 60, "A nice place to buy.", "Purple", deedFactory.createDeed(name));
		} else if (name.equals("Oriental Avenue") || name.equals("Vermont Avenue")) {
			result = new Street(name, 100, "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		} else if (name.equals("Connecticut Avenue")) {
			result = new Street(name, 120, "A nice place to buy.", "Light Blue", deedFactory.createDeed(name));
		} else if (name.equals("St. Charles Place") || name.equals("States Avenue")) {
			result = new Street(name, 140, "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		} else if (name.equals("Virginia Avenue")) {
			result = new Street(name, 160, "A nice place to buy.", "Magenta", deedFactory.createDeed(name));
		} else if (name.equals("St. James Place") || name.equals("Tennessee Avenue")) {
			result = new Street(name, 180, "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		} else if (name.equals("New York Avenue")) {
			result = new Street(name, 200, "A nice place to buy.", "Orange", deedFactory.createDeed(name));
		} else if (name.equals("Kentucky Avenue") || name.equals("Indiana Avenue")) {
			result = new Street(name, 220, "A nice place to buy.", "Red", deedFactory.createDeed(name));
		} else if (name.equals("Illinois Avenue")) {
			result = new Street(name, 240, "A nice place to buy.", "Red", deedFactory.createDeed(name));
		} else if (name.equals("Atlantic Avenue") || name.equals("Ventnor Avenue")) {
			result = new Street(name, 260, "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		} else if (name.equals("Marvin Garden")) {
			result = new Street(name, 260, "A nice place to buy.", "Yellow", deedFactory.createDeed(name));
		} else if (name.equals("Pacific Avenue") || name.equals("North Carolina Avenue")) {
			result = new Street(name, 300, "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		} else if (name.equals("Pennsylvania Avenue")) {
			result = new Street(name, 320, "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		} else if (name.equals("Park Place")) {
			result = new Street(name, 320, "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		} else if (name.equals("Boardwalk")) {
			result = new Street(name, 400, "A nice place to buy.", "Dark Green", deedFactory.createDeed(name));
		} else if (name.equals("Transit")) {
			result = new TransitStation(name,
					"Passengers wishing to use the metrobus line should change at Sisli Mecidiyekoy or Gayrettepe stations.");
		} else if (name.equals("Pennsylvania Railroad") || name.equals("Reading Railroad")
				|| name.equals("BnO Raidroad") || name.equals("Short Railroad")) {
			result = new Railroad(name, 200, "A nice place to buy.", deedFactory.createDeed(name));
		} else if (name.equals("Biscayne Avenue")) {
			result = new Street(name, 150, "A nice place to buy.", "Dark Orange", deedFactory.createDeed(name));
		} else if (name.equals("Miami Avenue") || name.equals("Florida Avenue")) {
			result = new Street(name, 150, "A nice place to buy.", "Dark Orange", deedFactory.createDeed(name));
		} else if (name.equals("Lombard Street") || name.equals("The Embarcadero")) {
			result = new Street(name, 210, "A nice place to buy.", "White", deedFactory.createDeed(name));
		} else if (name.equals("Fisherman's Wharf")) {
			result = new Street(name, 250, "A nice place to buy.", "White", deedFactory.createDeed(name));
		} else if (name.equals("Beacon Street") || name.equals("Boylston Street")) {
			result = new Street(name, 330, "A nice place to buy.", "Black", deedFactory.createDeed(name));
		} else if (name.equals("Newbury Street")) {
			result = new Street(name, 380, "A nice place to buy.", "Black", deedFactory.createDeed(name));
		} else if (name.equals("Fifth Avenue") || name.equals("Madison Avenue")) {
			result = new Street(name, 430, "A nice place to buy.", "Gray", deedFactory.createDeed(name));
		} else if (name.equals("Wall Street")) {
			result = new Street(name, 500, "A nice place to buy.", "Gray", deedFactory.createDeed(name));
		} else if (name.equals("Lake Street") || name.equals("Nicollet Avenue")) {
			result = new Street(name, 30, "A nice place to buy.", "Pink", deedFactory.createDeed(name));
		} else if (name.equals("Hennepin Avenue")) {
			result = new Street(name, 60, "A nice place to buy.", "Pink", deedFactory.createDeed(name));
		} else if (name.equals("Esplanade Avenue") || name.equals("Canal Street")) {
			result = new Street(name, 90, "A nice place to buy.", "Light Green", deedFactory.createDeed(name));
		} else if (name.equals("Magazine Street") || name.equals("Bourbon Street")) {
			result = new Street(name, 120, "A nice place to buy.", "Light Green", deedFactory.createDeed(name));
		} else if (name.equals("Katy Freeway") || name.equals("WestHeimer Road")) {
			result = new Street(name, 150, "A nice place to buy.", "Light Yellow", deedFactory.createDeed(name));
		} else if (name.equals("Kirby Drive") || name.equals("Cullen Boulevard")) {
			result = new Street(name, 180, "A nice place to buy.", "Light Yellow", deedFactory.createDeed(name));
		} else if (name.equals("Dekalb Avenue") || name.equals("Andrew Young Intl Boulevard")) {
			result = new Street(name, 210, "A nice place to buy.", "Turqoise", deedFactory.createDeed(name));
		} else if (name.equals("Decatur Street") || name.equals("Peachtree Street")) {
			result = new Street(name, 240, "A nice place to buy.", "Turqoise", deedFactory.createDeed(name));
		} else if (name.equals("Randolph Street") || name.equals("Lake Shore Drive")) {
			result = new Street(name, 270, "A nice place to buy.", "Wine Red", deedFactory.createDeed(name));
		} else if (name.equals("Wacker Drive") || name.equals("Michigan Avenue")) {
			result = new Street(name, 300, "A nice place to buy.", "Wine Red", deedFactory.createDeed(name));
		} else if (name.equals("South Temple") || name.equals("West Temple")) {
			result = new Street(name, 330, "A nice place to buy.", "Dark Yellow", deedFactory.createDeed(name));
		} else if (name.equals("North Temple") || name.equals("Temple Square")) {
			result = new Street(name, 360, "A nice place to buy.", "Dark Yellow", deedFactory.createDeed(name));
		} else if (name.equals("South Street") || name.equals("Broad Street")) {
			result = new Street(name, 390, "A nice place to buy.", "Tan", deedFactory.createDeed(name));
		} else if (name.equals("Walnut Street") || name.equals("Market Street")) {
			result = new Street(name, 420, "A nice place to buy.", "Tan", deedFactory.createDeed(name));
		} else if (name.equals("Mulholland Drive")) {
			result = new Street(name, 450, "A real nice place to buy.", "Dark Red", deedFactory.createDeed(name));
		} else if (name.equals("Ventura Boulevard")) {
			result = new Street(name, 480, "A real nice place to buy.", "Dark Red", deedFactory.createDeed(name));
		} else if (name.equals("Rodeo Drive")) {
			result = new Street(name, 510, "A real nice place to buy.", "Dark Red", deedFactory.createDeed(name));
		} else if (name.equals("Checker Cab Co.") || name.equals("Black n WhiteCab Co.")
				|| name.equals("Yellow Cab Co.") || name.equals("Ute Cab Co.")) {
			result = new CabCo(name, 300, "A Cab Company to buy", deedFactory.createDeed(name));
		} else if (name.equals("Water Works") || name.equals("Cable Company") || name.equals("Electric Company")
				|| name.equals("Internet Service Provider") || name.equals("Gas Company")
				|| name.equals("Telephone Company") || name.equals("Trash Collector") || name.equals("Sewage System")) {
			result = new Company(name, 150, "A Cab Company to buy", deedFactory.createDeed(name));
		} else if (name.equals("Income Tax")) {
			result = new IncomeTax(name, "Pay 10% or $200");
		} else if (name.equals("Go To Jail")) {
			result = new GoToJail(name, "Go Directly to Jail");
		} else if (name.equals("Luxury Tax")) {
			result = new LuxuryTax(name, "Pay $75");
		} else if (name.equals("Squeeze Play")) {
			result = new SqueezePlay(name,
					"If your roll equal those numbers, collect the corresponding vale from each player");
		} else if (name.equals("Bonus")) {
			result = new Bonus(name, "A pay corner");
		} else if (name.equals("Holland Tunnel")) {
			result = new HollandTunnel(name, "Go to the opposite tunnel");
		} else if (name.equals("Reverse Direction")) {
			result = new ReverseDirection(name, "Go in reverse direction next turn.");
		} else if (name.equals("Tax Refund")) {
			result = new GoToJail(name, "Collect 50% from pool");
		} else if (name.equals("Bus Ticket")) {
			result = new BusTicket(name, "Draw a bus card and keep");
		}
		// else if(name.equals("Auction")){
		// return new GoToJail(name, "A pay corner");
		// }
		else if (name.equals("Pay Day")) {
			result = new PayDay(name, "A pay corner");
		} else if (name.equals("Subway")) {
			result = new Subway(name, "Travel to any street next turn.");
		} else if (name.equals("Birthday Gift")) {
			result = new BirthdayGift(name, "You get 50% of the pool");
		} else if (name.equals("Stock Exchange")) {
			result = new StockExchange(name, "A pay corner");
		}

		else
			result = new FreeParking(name, "You can stay here for free!!!");
		return result;
	}
}
