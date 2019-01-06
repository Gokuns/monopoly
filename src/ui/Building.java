package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Building extends JPanel{
	
	int x;
	int y;
	int buildingType; // 0 = House, Red; 1 = Hotel, Blue; 2 = SkyScraper, Green;
	int[] id;
	
	public Building(int x, int y, int buildingType, int[] id) {

		this.id = id;
		this.x = x;
		this.y = y;
		this.buildingType = buildingType;
		switch(buildingType) {
		case 0:
			setBackground(Color.RED);
			break;
		case 1:
			setBackground(Color.BLUE);
			break;
		case 2:
			setBackground(Color.GREEN);
		}
		setBorder(BorderFactory.createLineBorder(Color.black));
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		switch(buildingType) {
		case 0:
			g.setColor(Color.RED);
			break;
		case 1:
			g.setColor(Color.BLUE);
			break;
		case 2:
			g.setColor(Color.GREEN);
		}
		g.fillOval(this.x, this.y, 15, 15);
			
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getBuildingType() {
		return buildingType;
	}
	public int getLayer() {
		return id[0];
	}
	public int getIndex() {
		return id[1];
	}
}
