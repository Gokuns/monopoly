package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
			g.setColor(Color.green);
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
		
	public void draw(int xLimit, int yLimit) {
		
		//setLocation(xLimit, yLimit);
		this.xLimit = xLimit;
		this.yLimit = yLimit;
		myPath = new StraightLinePath(xStart, yStart, xLimit, yLimit, 50);
		tm = new Timer(5, this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		pos = myPath.nextPosition();
		setLocation((int)pos.getX(), (int)pos.getY());
		
		
		if (! myPath.hasMoreSteps()) {
			xStart = xLimit;
			yStart = yLimit;
			tm.stop();
	        }
	}
	
}
