package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import domain.controller.GameController;
import domain.model.GameState;
import domain.model.dice.FaceValue;
import domain.model.players.Player;

public class GameControllerTest {
	
	private List<String> PlayerNames = new ArrayList<String>()  {{
	    add("Ali");
	    add("Berk");
	    add("Cem");
	}};
	
	private List<Integer> PlayerIDs = new ArrayList<Integer>()  {{
	    add(0);
	    add(1);
	    add(2);
	}};

	@Test
	public final void testInitializePlayers() {
		List<String> playerNames = new ArrayList<String>()  {{
		    add("Ali");
		    add("Berk");
		    add("Cem");
		}};
		List<Integer> playerIDs = new ArrayList<Integer>()  {{
		    add(0);
		    add(1);
		    add(2);
		}};
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("type", "gameStarted");
		map.put("playerCount", "3");
		for(int i = 0; i < playerNames.size(); i++) {
			map.put("player"+(i)+"Name", playerNames.get(i));
			map.put("player"+(i)+"ID", Integer.toString(playerIDs.get(i)));
		}
		map.put("currentPlayer", playerNames.get(0));
		map.put("currentPlayerID", Integer.toString(playerIDs.get(0)));
		
		GameController.getInstance().initializePlayers(map);
		assertThat(GameState.getInstance().getOrderedPlayerList(), 
				is(equalTo(GameState.getInstance().getPlayerList())));
		assertThat(GameState.getInstance().getPlayerList().size(),
				is(equalTo(3)));
		for(int i = 0; i < playerNames.size(); i++) {
			assertThat(GameState.getInstance().getPlayerList().get(i).getName(),
					is(equalTo(playerNames.get(i))));
			assertThat(GameState.getInstance().getPlayerList().get(i).getID(),
					is(equalTo(playerIDs.get(i))));
		}
	}

}
