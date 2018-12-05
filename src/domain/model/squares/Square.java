package domain.model.squares;

public abstract class Square {
	private String name;
	private String desciption;

	@SuppressWarnings("unused")
	private boolean isTransitOuter = false;
	@SuppressWarnings("unused")
	private boolean isTransitInner = false;
	private boolean isSpecialSquare = false;

	private boolean isTransit;

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
	 * @return the isTransit
	 */
	public boolean isTransit() {
		return isTransit;
	}

	/**
	 * @param isTransit
	 *            the isTransit to set
	 */
	public void setTransit(boolean isTransit) {
		this.isTransit = isTransit;
	}

	public boolean isSpecialSquare() {
		return isSpecialSquare;
	}

	public void setSpecialSquare(boolean isSpecialSquare) {
		this.isSpecialSquare = isSpecialSquare;
	}

}
