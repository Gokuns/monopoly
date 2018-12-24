package domain.model.gameHandler;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.*;

public class GameSaver {
	
	static SaveData data = SaveData.getInstance();
	static String filename =  "game.json";
	public GameSaver() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static void writeJsonOnject() throws Exception {
		  JsonObject saveObject = data.convertGameStateToSave(GameState.getInstance());
		  
		  Files.write(Paths.get(filename), saveObject.toString().getBytes());
//		  Files.write(Paths.get(filename), "aaaaa".getBytes());
		  
		  }
	
	

}
