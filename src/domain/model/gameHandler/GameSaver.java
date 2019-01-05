package domain.model.gameHandler;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

public class GameSaver {
	
	static SaveData data = SaveData.getInstance();
	public GameSaver() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void writeJsonOnject(String filename, GameState gameState) throws Exception {
		  JsonObject saveObject = data.convertGameStateToSave(gameState);
		  
		  Files.write(Paths.get(filename), saveObject.toString().getBytes());
		  
		  }
	
	

}
