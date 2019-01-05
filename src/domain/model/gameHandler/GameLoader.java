package domain.model.gameHandler;

import java.io.File;
import java.io.FileReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class GameLoader {
	
	SaveData data;

	public GameLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public static JsonObject readJsonSimpleDemo(File file) throws Exception {  
	    FileReader reader = new FileReader(file.getAbsolutePath());
	    JsonParser jsonParser = new JsonParser();
	    return (JsonObject) jsonParser.parse(reader);
	}

}
