package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Ball extends JPanel {
	
	String name;
	int color;
	
	public Ball(String name, int color) {
		this.name = name;
		this.color = color;
	}
	
	public void paintComponent(Graphics G) {
		
		if(getColor() == 0) {
			G.setColor(Color.BLACK);
		}
		else if(getColor() == 1) {
			G.setColor(Color.BLUE);
		}
		else if(getColor() == 2) {
			G.setColor(Color.RED);
		}
		else if(getColor() == 3) {
			G.setColor(Color.green);
		}
		else if(getColor() == 4) {
			G.setColor(Color.ORANGE);
		}
		else if(getColor() == 5) {
			G.setColor(Color.YELLOW);
		}
		else {
			G.setColor(Color.PINK);
		}
		
		G.fillOval(0, 0, 25, 25);
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
	
}
