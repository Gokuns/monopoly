package domain.model;

public abstract class Square {
	private String name;
	private String desciption;
	private boolean isTransitOuter = false;
	private boolean isTransitInner = false;
	
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

	/**
	 * @return the isTransitOuter
	 */
	public boolean isTransitOuter() {
		return isTransitOuter;
	}

	/**
	 * @param isTransitOuter the isTransitOuter to set
	 */
	public void setTransitOuter(boolean isTransitOuter) {
		this.isTransitOuter = isTransitOuter;
	}

	/**
	 * @return the isTransitInner
	 */
	public boolean isTransitInner() {
		return isTransitInner;
	}

	/**
	 * @param isTransitInner the isTransitInner to set
	 */
	public void setTransitInner(boolean isTransitInner) {
		this.isTransitInner = isTransitInner;
	}
	
}
