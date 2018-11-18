package domain.model;

import java.util.HashMap;

public class DeedFactory {
	private static DeedFactory deedFactory;
	
	private DeedFactory() {
		
	}
	
	public static synchronized DeedFactory getInstance() {
		if(deedFactory == null) {
			deedFactory = new DeedFactory();
		}
		return deedFactory;
	}
	
	@SuppressWarnings("serial")
	public Deed createDeed(String name) {
		
		if (name.equals("Mediterranean Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
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
			}});
		}else if (name.equals("Baltic Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
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
			}});
		}else if (name.equals("Oriental Avenue") || name.equals("Vermont Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
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
			}});
		}else if (name.equals("Connecticut Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",8);
				put("oneHouse", 40);
				put("twoHouse", 100);
				put("threeHouse", 300);
				put("fourHouse", 450);
				put("hotel", 600);
				put("sky", 1100);
				put("mortgage", 60);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("St. Charles Place") || name.equals("States Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",10);
				put("oneHouse", 50);
				put("twoHouse", 150);
				put("threeHouse", 450);
				put("fourHouse", 625);
				put("hotel", 750);
				put("sky", 1250);
				put("mortgage", 70);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Virginia Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",12);
				put("oneHouse", 60);
				put("twoHouse", 180);
				put("threeHouse", 500);
				put("fourHouse", 700);
				put("hotel", 900);
				put("sky", 1400);
				put("mortgage", 80);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("St. James Place") || name.equals("Tennessee Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",14);
				put("oneHouse", 70);
				put("twoHouse", 200);
				put("threeHouse", 550);
				put("fourHouse", 750);
				put("hotel", 950);
				put("sky", 1450);
				put("mortgage", 90);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("New York Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",16);
				put("oneHouse", 80);
				put("twoHouse", 220);
				put("threeHouse", 600);
				put("fourHouse", 800);
				put("hotel", 1000);
				put("sky", 1500);
				put("mortgage", 100);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Kentucky Avenue") || name.equals("Indiana Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",18);
				put("oneHouse", 90);
				put("twoHouse", 250);
				put("threeHouse", 700);
				put("fourHouse", 875);
				put("hotel", 1050);
				put("sky", 2050);
				put("mortgage", 100);
				put("housePrice", 150);
				put("hotelPrice", get("housePrice")*4+150);
				put("skyPrice", get("hotelPrice")+150);
			}});
		}else if (name.equals("Illinois Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",20);
				put("oneHouse", 100);
				put("twoHouse", 300);
				put("threeHouse", 750);
				put("fourHouse", 925);
				put("hotel", 1100);
				put("sky", 2100);
				put("mortgage", 120);
				put("housePrice", 150);
				put("hotelPrice", get("housePrice")*4+150);
				put("skyPrice", get("hotelPrice")+150);
			}});
		}else if (name.equals("Atlantic Avenue") || name.equals("Ventnor Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",22);
				put("oneHouse", 110);
				put("twoHouse", 330);
				put("threeHouse", 800);
				put("fourHouse", 975);
				put("hotel", 1150);
				put("sky", 2150);
				put("mortgage", 130);
				put("housePrice", 150);
				put("hotelPrice", get("housePrice")*4+150);
				put("skyPrice", get("hotelPrice")+150);
			}});
		}else if (name.equals("Marvin Garden")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",24);
				put("oneHouse", 120);
				put("twoHouse", 350);
				put("threeHouse", 850);
				put("fourHouse", 1025);
				put("hotel", 1200);
				put("sky", 2200);
				put("mortgage", 140);
				put("housePrice", 150);
				put("hotelPrice", get("housePrice")*4+150);
				put("skyPrice", get("hotelPrice")+150);
			}});
		}else if (name.equals("Pacific Avenue") || name.equals("North Carolina Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",26);
				put("oneHouse", 130);
				put("twoHouse", 390);
				put("threeHouse", 900);
				put("fourHouse", 1100);
				put("hotel", 1275);
				put("sky", 2275);
				put("mortgage", 150);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("Pennsylvania Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",28);
				put("oneHouse", 150);
				put("twoHouse", 450);
				put("threeHouse", 1000);
				put("fourHouse", 1200);
				put("hotel", 1400);
				put("sky", 2400);
				put("mortgage", 160);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("Park Place")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",35);
				put("oneHouse", 175);
				put("twoHouse", 500);
				put("threeHouse", 1100);
				put("fourHouse", 1300);
				put("hotel", 1500);
				put("sky", 2500);
				put("mortgage", 200);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("Boardwalk")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",50);
				put("oneHouse", 200);
				put("twoHouse", 600);
				put("threeHouse", 1400);
				put("fourHouse", 1700);
				put("hotel", 2000);
				put("sky", 3000);
				put("mortgage", 200);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("Pennsylvania Railroad") || name.equals("Reading Railroad") ||
				name.equals("BnO Raidroad") || name.equals("Short Railroad")) {
			return new Deed("RailRoad", new HashMap<String, Integer>(){{
				put("rent",25);
				put("twoRailRoad", 50);
				put("threeRailRoad", 100);
				put("fourRailRoad", 200);
				put("morgage", 100);
			}});
		}else if (name.equals("Biscayne Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",11);
				put("oneHouse", 55);
				put("twoHouse", 160);
				put("threeHouse", 475);
				put("fourHouse", 650);
				put("hotel", 800);
				put("sky", 1300);
				put("mortgage", 75);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Miami Avenue") || name.equals("Florida Avenue")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",9);
				put("oneHouse", 45);
				put("twoHouse", 120);
				put("threeHouse", 350);
				put("fourHouse", 500);
				put("hotel", 700);
				put("sky", 1200);
				put("mortgage", 65);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Lombard") || name.equals("Embarcadero")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",17);
				put("oneHouse", 85);
				put("twoHouse", 240);
				put("threeHouse", 475);
				put("fourHouse", 670);
				put("hotel", 1025);
				put("sky", 1525);
				put("mortgage", 105);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Fishermans")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",21);
				put("oneHouse", 105);
				put("twoHouse", 320);
				put("threeHouse", 780);
				put("fourHouse", 950);
				put("hotel", 1125);
				put("sky", 1625);
				put("mortgage", 125);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Beacon") || name.equals("Boylston")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",30);
				put("oneHouse", 160);
				put("twoHouse", 470);
				put("threeHouse", 1050);
				put("fourHouse", 1250);
				put("hotel", 1500);
				put("sky", 2500);
				put("mortgage", 165);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("Newbury")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",40);
				put("oneHouse", 185);
				put("twoHouse", 550);
				put("threeHouse", 1200);
				put("fourHouse", 1500);
				put("hotel", 1700);
				put("sky", 2700);
				put("mortgage", 190);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("Fifth") || name.equals("Madison")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",60);
				put("oneHouse", 220);
				put("twoHouse", 650);
				put("threeHouse", 1500);
				put("fourHouse", 1800);
				put("hotel", 2100);
				put("sky", 3600);
				put("mortgage", 215);
				put("housePrice", 300);
				put("hotelPrice", get("housePrice")*4+300);
				put("skyPrice", get("hotelPrice")+300);
			}});
		}else if (name.equals("Wall")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",80);
				put("oneHouse", 300);
				put("twoHouse", 800);
				put("threeHouse", 1800);
				put("fourHouse", 2200);
				put("hotel", 2700);
				put("sky", 4200);
				put("mortgage", 250);
				put("housePrice", 300);
				put("hotelPrice", get("housePrice")*4+300);
				put("skyPrice", get("hotelPrice")+300);
			}});
		}else if (name.equals("Lake") || name.equals("Nicollet")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",1);
				put("oneHouse", 5);
				put("twoHouse", 15);
				put("threeHouse", 45);
				put("fourHouse", 80);
				put("hotel", 125);
				put("sky", 625);
				put("mortgage", 15);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Hennepin")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",3);
				put("oneHouse", 15);
				put("twoHouse", 45);
				put("threeHouse", 120);
				put("fourHouse", 240);
				put("hotel", 350);
				put("sky", 850);
				put("mortgage", 30);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Esplanade")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",5);
				put("oneHouse", 25);
				put("twoHouse", 80);
				put("threeHouse", 225);
				put("fourHouse", 360);
				put("hotel", 600);
				put("sky", 1000);
				put("mortgage", 50);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Katy") || name.equals("WestHeimer")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",11);
				put("oneHouse", 55);
				put("twoHouse", 160);
				put("threeHouse", 475);
				put("fourHouse", 650);
				put("hotel", 800);
				put("sky", 1300);
				put("mortgage", 70);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Kirby") || name.equals("Cullen")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",14);
				put("oneHouse", 70);
				put("twoHouse", 200);
				put("threeHouse", 880);
				put("fourHouse", 750);
				put("hotel", 950);
				put("sky", 1450);
				put("mortgage", 80);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Canal")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",5);
				put("oneHouse", 25);
				put("twoHouse", 80);
				put("threeHouse", 225);
				put("fourHouse", 360);
				put("hotel", 600);
				put("sky", 1000);
				put("mortgage", 50);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Magazine") || name.equals("Bourbon")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",8);
				put("oneHouse", 40);
				put("twoHouse", 100);
				put("threeHouse", 300);
				put("fourHouse", 450);
				put("hotel", 600);
				put("sky", 1100);
				put("mortgage", 60);
				put("housePrice", 50);
				put("hotelPrice", get("housePrice")*4+50);
				put("skyPrice", get("hotelPrice")+50);
			}});
		}else if (name.equals("Dekalb") || name.equals("Young")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",17);
				put("oneHouse", 85);
				put("twoHouse", 240);
				put("threeHouse", 670);
				put("fourHouse", 840);
				put("hotel", 1025);
				put("sky", 1525);
				put("mortgage", 90);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Decatur") || name.equals("Peachtree")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",20);
				put("oneHouse", 100);
				put("twoHouse", 300);
				put("threeHouse", 750);
				put("fourHouse", 925);
				put("hotel", 1100);
				put("sky", 1600);
				put("mortgage", 100);
				put("housePrice", 100);
				put("hotelPrice", get("housePrice")*4+100);
				put("skyPrice", get("hotelPrice")+100);
			}});
		}else if (name.equals("Randolph") || name.equals("LakeShore")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",23);
				put("oneHouse", 115);
				put("twoHouse", 345);
				put("threeHouse", 825);
				put("fourHouse", 1010);
				put("hotel", 1180);
				put("sky", 2180);
				put("mortgage", 110);
				put("housePrice", 150);
				put("hotelPrice", get("housePrice")*4+150);
				put("skyPrice", get("hotelPrice")+150);
			}});
		}else if (name.equals("Wacker") || name.equals("Michigan")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",26);
				put("oneHouse", 130);
				put("twoHouse", 390);
				put("threeHouse", 900);
				put("fourHouse", 1100);
				put("hotel", 1275);
				put("sky", 2275);
				put("mortgage", 120);
				put("housePrice", 150);
				put("hotelPrice", get("housePrice")*4+150);
				put("skyPrice", get("hotelPrice")+150);
			}});
		}else if (name.equals("SouthTemple") || name.equals("WestTemple")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",32);
				put("oneHouse", 160);
				put("twoHouse", 470);
				put("threeHouse", 1050);
				put("fourHouse", 1250);
				put("hotel", 1500);
				put("sky", 2500);
				put("mortgage", 130);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("NorthTemple") || name.equals("TempleSquare")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",38);
				put("oneHouse", 170);
				put("twoHouse", 520);
				put("threeHouse", 1125);
				put("fourHouse", 1425);
				put("hotel", 1275);
				put("sky", 1650);
				put("mortgage", 140);
				put("housePrice", 200);
				put("hotelPrice", get("housePrice")*4+200);
				put("skyPrice", get("hotelPrice")+200);
			}});
		}else if (name.equals("South") || name.equals("Broad")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",45);
				put("oneHouse", 210);
				put("twoHouse", 575);
				put("threeHouse", 1300);
				put("fourHouse", 1600);
				put("hotel", 1800);
				put("sky", 3300);
				put("mortgage", 150);
				put("housePrice", 250);
				put("hotelPrice", get("housePrice")*4+250);
				put("skyPrice", get("hotelPrice")+250);
			}});
		}else if (name.equals("Walnut") || name.equals("Market")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",55);
				put("oneHouse", 225);
				put("twoHouse", 630);
				put("threeHouse", 1450);
				put("fourHouse", 1750);
				put("hotel", 2050);
				put("sky", 3550);
				put("mortgage", 160);
				put("housePrice", 250);
				put("hotelPrice", get("housePrice")*4+250);
				put("skyPrice", get("hotelPrice")+250);
			}});
		}else if (name.equals("Mulholland")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",70);
				put("oneHouse", 350);
				put("twoHouse", 750);
				put("threeHouse", 1600);
				put("fourHouse", 1850);
				put("hotel", 2100);
				put("sky", 3600);
				put("mortgage", 175);
				put("housePrice", 300);
				put("hotelPrice", get("housePrice")*4+300);
				put("skyPrice", get("hotelPrice")+300);
			}});
		}else if (name.equals("Ventura")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",80);
				put("oneHouse", 400);
				put("twoHouse", 825);
				put("threeHouse", 1800);
				put("fourHouse", 2175);
				put("hotel", 2550);
				put("sky", 4050);
				put("mortgage", 200);
				put("housePrice", 300);
				put("hotelPrice", get("housePrice")*4+300);
				put("skyPrice", get("hotelPrice")+300);
			}});
		}else if (name.equals("Rodeo")) {
			return new Deed("Street", new HashMap<String, Integer>(){{
				put("rent",90);
				put("oneHouse", 450);
				put("twoHouse", 900);
				put("threeHouse", 2000);
				put("fourHouse", 2500);
				put("hotel", 3000);
				put("sky", 4500);
				put("mortgage", 250);
				put("housePrice", 300);
				put("hotelPrice", get("housePrice")*4+300);
				put("skyPrice", get("hotelPrice")+300);
			}});
		}else if (name.equals("CheckerCab") || name.equals("BlacknWhiteCab") ||
				name.equals("YellowCab") || name.equals("UteCab")) {
			return new Deed("CabCompany", new HashMap<String, Integer>(){{
				put("rent", 30);
				put("twoCab", 60);
				put("threeCab", 120);
				put("fourCab", 240);
				put("mortgage", 150);
			}});
		}else if (name.equals("WaterWorks") || name.equals("CableCompany") ||
				name.equals("ElectricCompany") || name.equals("InternetServiceProvider") ||
				name.equals("GasCompany") || name.equals("TelephoneCompany") ||
				name.equals("TrashCollector") || name.equals("SewageSystem")) {
			return new Deed("UtilityCompany", new HashMap<String, Integer>(){{
				put("oneUtility", 4);
				put("twoUtility", 10);
				put("threeUtility", 20);
				put("fourUtility", 40);
				put("fiveUtility", 80);
				put("sixUtility", 100);
				put("sevenUtility", 120);
				put("eightUtility", 150);
				put("mortgage", 75);
			}});
		}else {
			System.out.println("Deeds name is wrong");
			return null;
		}
		
	}
	
}
