package view;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Board {

	public ArrayList<VSquare> inSquares = new ArrayList<VSquare>();
	public ArrayList<VSquare> midSquares = new ArrayList<VSquare>();
	public ArrayList<VSquare> outSquares = new ArrayList<VSquare>();

	public Board() throws IOException {

		int posX = 600;
		int posY = 600;
		int width = 0;
		int height = 0;
		Image img;
		int longDim = 223;
		int shortDim = 111;

		//Inner Layer
		for(int i =0; i<24; i++) {	
			if(i == 0 || i == 6 || i == 12 || i == 18) {
				width = longDim;
				height = longDim;
				if( i == 12) posY = posY+height;
				else posX = posX+width;
			}
			else if(i<6) {
				width = shortDim;
				height = longDim;
				posX = posX+width;
			}
			else if(i<12) {
				width = longDim;
				height = shortDim;
				posY = posY+height;
			}
			else if(i<18) {
				width = shortDim;
				height = longDim;
				posX = posX+width;
			}
			else{
				width = longDim;
				height = shortDim;
				posY = posY+height;
			}

			img = ImageIO.read(new File("img/"+ "Squares/" + "LayerIn/" + i + ".png"));
			inSquares.add(new VSquare(posX, posY, width, height, img));		
			i++;
		}

		//Middle Layer
		for(int i =0; i<40; i++) {	
			if(i == 0 || i == 10 || i == 20 || i == 30) {
				width = longDim;
				height = longDim;
				if( i == 20) posY = posY+height;
				else posX = posX+width;
			}
			else if(i<10) {
				width = shortDim;
				height = longDim;
				posX = posX+width;
			}
			else if(i<20) {
				width = longDim;
				height = shortDim;
				posY = posY+height;
			}
			else if(i<30) {
				width = shortDim;
				height = longDim;
				posX = posX+width;
			}
			else{
				width = longDim;
				height = shortDim;
				posY = posY+height;
			}

			img = ImageIO.read(new File("img/"+ "Squares/" + "LayerMid/" + i + ".png"));
			inSquares.add(new VSquare(posX, posY, width, height, img));		
			i++;
		}

		// Outer Layer
		for(int i =0; i<56; i++) {	
			if(i == 0 || i == 14 || i == 28 || i == 42) {
				width = longDim;
				height = longDim;
				if( i == 28) posY = posY+height;
				else posX = posX+width;
			}
			else if(i<14) {
				width = shortDim;
				height = longDim;
				posX = posX+width;
			}
			else if(i<28) {
				width = longDim;
				height = shortDim;
				posY = posY+height;
			}
			else if(i<42) {
				width = shortDim;
				height = longDim;
				posX = posX+width;
			}
			else{
				width = longDim;
				height = shortDim;
				posY = posY+height;
			}

			img = ImageIO.read(new File("img/"+ "Squares/" + "LayerOut/" + i + ".png"));
			inSquares.add(new VSquare(posX, posY, width, height, img));		
			i++;
		}

	}

	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		for(int i= 0; i<24; i++) {
			g.drawImage(inSquares.get(i).getImg(), inSquares.get(i).getPosX(), inSquares.get(i).getPosY(), null);
		}
		for(int i= 0; i<40; i++) {
			g.drawImage(midSquares.get(i).getImg(), midSquares.get(i).getPosX(), midSquares.get(i).getPosY(), null);
		}
		for(int i= 0; i<56; i++) {
			g.drawImage(outSquares.get(i).getImg(), outSquares.get(i).getPosX(), outSquares.get(i).getPosY(), null);
		}
	}
}
