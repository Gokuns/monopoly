package domain.model;

import java.awt.Color;

public class Street extends Property {
	private String color;
	private int houseCount;
	private boolean hasHotel;
	private boolean hasSkyscraper;
	public Street(String name, int price, String description, String color, Deed deed) {
		super(name, price, description, deed);
		this.color = color;
		this.houseCount = 0;
		this.hasHotel = false;
		this.hasSkyscraper = false;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getHouseCount() {
		return houseCount;
	}
	public void setHouseCount(int houseCount) {
		this.houseCount = houseCount;
	}
	public boolean isHasHotel() {
		return hasHotel;
	}
	public void setHasHotel(boolean hasHotel) {
		this.hasHotel = hasHotel;
	}
	public boolean isHasSkyscraper() {
		return hasSkyscraper;
	}
	public void setHasSkyscraper(boolean hasSkyscraper) {
		this.hasSkyscraper = hasSkyscraper;
	}

}
