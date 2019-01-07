package ui;

import java.awt.Point;
import java.util.ArrayList;

public class Animator {

	public void animate(Drawable g, ArrayList<Point> coordinateList, int animationSlowness) {
		g.draw(coordinateList, animationSlowness);
	}
	
	public void animateForLoad(Drawable g, int xLimit, int yLimit, int animationSlowness) {
		g.drawForLoad(xLimit, yLimit, animationSlowness);
	}

	
}
