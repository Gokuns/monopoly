package domain.model.squares;

import domain.model.players.Player;

public class Square {
	private String name;
	private String desciption;
	private SquareStrategy sqStrat;
	private PayCornerStrategy payStrat;

	private boolean isTransitOuter = false;
	private boolean isTransitInner = false;
	private boolean isSpecialSquare = false;
	private boolean isProperty = false;

	private boolean isTransit = false;

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

	public String getDescription() {
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

	/**
	 * @return the sqStrat
	 */
	public SquareStrategy getSqStrat() {
		return sqStrat;
	}

	/**
	 * @param strat the sqStrat to set
	 */
	public void setSqStrat(SquareStrategy strat) {
		this.sqStrat = strat;
	}
	
	public String tryToAct(Player p) {
		return sqStrat.action(p);
	}
	
	public void setSquareAction(SquareStrategy newstrat) {
		sqStrat = newstrat;
	}
	
	public PayCornerStrategy getPayStrat() {
		return payStrat;
	}
	
	public void setPayStrat(PayCornerStrategy newstrat) {
		payStrat = newstrat;
	}
	
	public String tryToGetPaid(Player p) {
		return payStrat.getPaid(p);
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
	
	@Override
	public String toString() {
		return name;
	}

	public boolean isProperty() {
		return isProperty;
	}

	public void setProperty(boolean isProperty) {
		this.isProperty = isProperty;
	}

}
