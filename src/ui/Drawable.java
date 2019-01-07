package ui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public interface Drawable {

	public void draw(ArrayList<Point> squareCoordinates, int animationSlowness);
	
}
