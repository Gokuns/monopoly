package domain.model.cards.chanceCards;

import java.util.ArrayList;
import java.util.HashMap;

import domain.model.cards.ChanceCard;
import domain.model.gameHandler.Board;
import domain.model.gameHandler.GameState;
import domain.model.players.Player;
import domain.model.squares.properties.Property;
import domain.model.squares.properties.Street;

public class Hurricane extends ChanceCard{
	
	private String colorOfDistrict = "";

	public String getColorOfDistrict() {
		return colorOfDistrict;
	}

	public void setColorOfDistrict(String colorOfDistrict) {
		this.colorOfDistrict = colorOfDistrict;
	}

	public Hurricane(String name, String desc){
		super(name,desc);
	}

	@Override
	protected void action(Player p) {
		
		GameState game = GameState.getInstance();
		Board board = Board.getInstance();
		ArrayList<Player> playerList = game.getPlayerList();
		
		setColorOfDistrict2ChosenColorOfDistrict(game);
		
		colorOfDistrict = "Light Blue";
		
		HashMap<String, Integer> referenceHmap = board.getColoredDistricts();
		System.out.println(referenceHmap);
		System.out.println(colorOfDistrict);
		int districtMaxCount = referenceHmap.get(colorOfDistrict);

		
		HashMap<String, String> mapForUITransfer = new HashMap<String, String>();
		mapForUITransfer.put("type", "hurricane");
		mapForUITransfer.put("successfulHurricane", "false");
		mapForUITransfer.put("detail", "");
		
		for(Player curP:playerList){
			if(!curP.getName().equals(p.getName())){
				System.out.println(curP.getName());
				HashMap<String, Integer> ownedColoredDistrictMap = curP.propertyList2ownedColoredDistricts();
				System.out.println(ownedColoredDistrictMap);
				System.out.println("districtMaxCount: "+ districtMaxCount);

				if(ownedColoredDistrictMap.containsKey(colorOfDistrict)){
					int count = ownedColoredDistrictMap.get(colorOfDistrict);
					System.out.println("count: "+ count);
					if(count == districtMaxCount){
						ArrayList<Property> propertyList = curP.getPrList();
						for(Property prop:propertyList){
							Street street = (Street) prop;
							if(street.getColor().equals(colorOfDistrict)){
								String currentDetail = mapForUITransfer.get("detail");
								if(street.isHasSkyscraper()){
									street.setHasSkyscraper(false);
									street.setHasHotel(true);
									mapForUITransfer.put("successfulHurricane", "true");
									mapForUITransfer.put("detail", " // " + currentDetail + " " + curP.getName() + "'s " + street.getName() + " has been affected by a hurricane: Skycraper has been replaced by a hotel.");
								}else if(street.isHasHotel()){
									street.setHasHotel(false);
									street.setHouseCount(4);
									mapForUITransfer.put("successfulHurricane", "true");
									mapForUITransfer.put("detail", " // " + currentDetail + " " + curP.getName() + "'s " + street.getName() + " has been affected by a hurricane: Hotel has been replaced by 4 houses.");
								}else if(street.getHouseCount()>0){
									int currentHouseCount = street.getHouseCount();
									street.setHouseCount(currentHouseCount - 1);
									mapForUITransfer.put("successfulHurricane", "true");
									mapForUITransfer.put("detail", " // " + currentDetail + " " + curP.getName() + "'s " + street.getName() + " has been affected by a hurricane: " + currentHouseCount + " houses became " + (currentHouseCount - 1));
								}
							}
						}
						break;
					}
				}
			}
		}
		
		game.publishToUIListeners(mapForUITransfer);////displays an info indicating that current player has been put in prison.
		game.publishToNetworkListeners(mapForUITransfer);//publish the mapping to the network.
		
	}

	private void setColorOfDistrict2ChosenColorOfDistrict(GameState game) {
		HashMap<String, String> mapForGetColoredDistrictFromUI = new HashMap<String, String>();
		mapForGetColoredDistrictFromUI.put("type", "hurricaneGetColorOfDistrictFromUI");
		mapForGetColoredDistrictFromUI.put("colorOfDistrict", "");
		
		game.publishToUIListeners(mapForGetColoredDistrictFromUI);////displays player&pool balances in UI.
		game.publishToNetworkListeners(mapForGetColoredDistrictFromUI);//publish the mapping to the network.
	}
	
}
