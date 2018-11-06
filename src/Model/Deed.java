package Model;

import java.util.HashMap;

public class Deed {
	
	private String name;
	private HashMap<String, Integer> values;
	
	public Deed(String name, HashMap<String, Integer> values){
		this.name = name;
		this.values = values;
	}
	
}
