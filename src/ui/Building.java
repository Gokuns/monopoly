package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Building extends JPanel{
	
	int x;
	int y;
	
	public Building(int x, int y) {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.GREEN);
		g.fillOval(this.x, this.y, 30, 30);
	}

}
