package domain.model;

public abstract class Square {
	private String name;
	private String desciption;
<<<<<<< src/domain/model/Square.java
	private int layer;
	private int number;
	private boolean isTransitOuter = false;
	private boolean isTransitInner = false;
=======
	private boolean isTransit;
>>>>>>> src/domain/model/Square.java
	
	public Square(String name, String description, int layer, int number) {
		this.name = name;
		this.desciption = description;
		this.layer = layer;
		this.number = number;
	}
	
	

	public int getLayer() {
		return layer;
	}



	public void setLayer(int layer) {
		this.layer = layer;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
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

	/**
	 * @return the isTransit
	 */
	public boolean isTransit() {
		return isTransit;
	}

	/**
	 * @param isTransit the isTransit to set
	 */
	public void setTransit(boolean isTransit) {
		this.isTransit = isTransit;
	}


	
}
