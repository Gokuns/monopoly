package domain.model.gameHandler;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

import domain.model.dice.Cup;

public class GameSaver {
	
	static SaveData data = SaveData.getInstance();
	public GameSaver() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void writeJsonOnject(String filename, GameState gameState, Cup cup) throws Exception {
		  JsonObject saveObject = data.convertGameToSave(gameState, cup);
		  
		  Files.write(Paths.get(filename), saveObject.toString().getBytes());
		  
		  }
	
	

}
