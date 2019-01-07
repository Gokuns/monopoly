package ui;

import java.awt.Point;
import java.util.ArrayList;

public class Animator {

	public void animate(Drawable g, ArrayList<Point> coordinateList, int animationSlowness) {
		g.draw(coordinateList, animationSlowness);
	}

	
}
