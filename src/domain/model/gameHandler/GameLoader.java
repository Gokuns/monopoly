package domain.model.gameHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GameLoader {
	
	SaveData data;
	String filename = "game.json";

	public GameLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public static JsonObject readJsonSimpleDemo(String filename) throws Exception {  
	    FileReader reader = new FileReader(filename);
	    JsonParser jsonParser = new JsonParser();
	    return (JsonObject) jsonParser.parse(reader);
	}

}
