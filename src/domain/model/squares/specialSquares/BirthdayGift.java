package domain.model.squares.specialSquares;

import java.util.ArrayList;

import domain.model.GameState;
import domain.model.players.Player;
import domain.model.squares.SpecialSquare;

public class BirthdayGift extends SpecialSquare {

	public BirthdayGift(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String action(Player p) {
		GameState game= GameState.getInstance();
		ArrayList<Player> list = game.getOrderedPlayerList();
		for (Player player : list) {
			if(player!=p) {
			player.setBalance(player.getBalance()-50);
			}
		}
		int present = (list.size()-1)*50;
		p.setBalance(p.getBalance()+present);
		return p.getName() + " got a total amount of " + present ;
		// TODO Auto-generated method stub
		
		

	}

}
