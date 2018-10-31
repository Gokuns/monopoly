package Model;

import java.awt.Color;

public class Street extends Property {
	private Color color;
	private int houseCount;
	private boolean hasHotel;
	private boolean hasSkyscraper;
	public Street(String name, int price, String description, Color color, int houseCount, boolean hasHotel, boolean hasSkyscraper) {
		super(name, price, description);
		this.color = color;
		this.houseCount = houseCount;
		this.hasHotel = hasHotel;
		this.hasSkyscraper = hasSkyscraper;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
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
