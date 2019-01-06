package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Ball extends JPanel implements ActionListener, Drawable{
	
	private Timer tm;
	private String name;
	private int color;
	private int xStart, yStart;
	private int xLimit, yLimit;
	private StraightLinePath myPath;
	private Point pos;
	private int pathNumber = 1;
	private int numberOfPaths;
	private ArrayList<Point> coordinateList;
	private int animationSlowness;
	
	
	public Ball(String name, int color, int xStart, int yStart) {
		this.name = name;
		this.color = color;
		this.xStart = xStart;
		this.yStart = yStart;
	}
	
	public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		
		if(getColor() == 0) {
			g.setColor(Color.BLACK);
		}
		else if(getColor() == 1) {
			g.setColor(Color.BLUE);
		}
		else if(getColor() == 2) {
			g.setColor(Color.RED);
		}
		else if(getColor() == 3) {
			g.setColor(Color.MAGENTA);
		}
		else if(getColor() == 4) {
			g.setColor(Color.ORANGE);
		}
		else if(getColor() == 5) {
			g.setColor(Color.YELLOW);
		}
		else {
			g.setColor(Color.PINK);
		}
		
		g.fillRect(0, 0, 20, 20);

	       
	    }
		
	public void draw(ArrayList<Point> coordinateList, int animationSlowness) {
		
		//setLocation(xLimit, yLimit);
		this.animationSlowness = animationSlowness;
		this.coordinateList = coordinateList;
		this.numberOfPaths = coordinateList.size();
		this.xLimit = (int) coordinateList.get(0).getX();
		this.yLimit = (int) coordinateList.get(0).getY();
		myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
		tm = new Timer(animationSlowness, this);
		tm.start();
			
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public Timer getTimer() {
		return tm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pos = myPath.nextPosition();
		setLocation((int)pos.getX(), (int)pos.getY());
		
		
			if (! myPath.hasMoreSteps()) {
				xStart = xLimit;
				yStart = yLimit;
				tm.stop();
				if(pathNumber <= numberOfPaths) {
					pathNumber++;
					if(pathNumber > numberOfPaths) {
						pathNumber = 1;
					}
					else {
						this.xLimit = (int) coordinateList.get(pathNumber-1).getX();
						this.yLimit = (int) coordinateList.get(pathNumber-1).getY();
						myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
						tm = new Timer(animationSlowness, this);
						tm.start();
					}
				}
			
		}
	}
	
}
