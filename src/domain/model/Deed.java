package domain.model;

import java.util.HashMap;

public class Deed {
	
	private String type;
	private HashMap<String, Integer> values;
	
	public Deed(String type, HashMap<String, Integer> values){

		this.setValues(values);
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the values
	 */
	public HashMap<String, Integer> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(HashMap<String, Integer> values) {
		this.values = values;
	}
	
}
