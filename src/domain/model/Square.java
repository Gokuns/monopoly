package domain.model;

public abstract class Square {
	private String name;
	private String desciption;
	protected boolean isTransit = false;
	
	public Square(String name, String description) {
		this.name = name;
		this.desciption = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	
}
