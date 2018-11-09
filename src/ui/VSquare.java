package ui;

import java.awt.Image;

public class VSquare {

	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected Image img;
	
	public VSquare(int posX, int posY, int width, int height, Image img) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.img = img;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public Image getImg() {
		return img;
	}
	
}

