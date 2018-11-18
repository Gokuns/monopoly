package ui;

import java.util.ArrayList;
import java.util.List;

import domain.model.Square;

public class BoardLayers {
	
	private ArrayList<SquareCoordinates> layerone;
	private ArrayList<SquareCoordinates> layertwo;
	private ArrayList<SquareCoordinates> layerthree;
	List<List<SquareCoordinates>> layerList = new ArrayList<List<SquareCoordinates>>();
	
	public BoardLayers() {
		
		layerone = new ArrayList<SquareCoordinates>();
		layertwo = new ArrayList<SquareCoordinates>();
		layerthree = new ArrayList<SquareCoordinates>();
		
		//layer1 initialization
		layerone.add(new SquareCoordinates(510,515));
		layerone.add(new SquareCoordinates(455,515));
		for(int i=0;i<4;i++) {
			layerone.add(new SquareCoordinates(420-40*i, 515));
		}
		layerone.add(new SquareCoordinates(240, 515));
		for(int i=0;i<6;i++) {
			layerone.add(new SquareCoordinates(240, 450-40*i));
		}
		for(int i=0;i<5;i++) {
			layerone.add(new SquareCoordinates(300+40*i, 230));
		}
		layerone.add(new SquareCoordinates(520, 230));
		for(int i=0;i<5;i++) {
			layerone.add(new SquareCoordinates(520, 290+40*i));
		}
		//layer1 initialized
		
		//layer2 initialization
		
		layertwo.add(new SquareCoordinates(600, 590));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(540-40*i, 590));
		}
		layertwo.add(new SquareCoordinates(175, 590));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(165, 530-40*i));
		}
		layertwo.add(new SquareCoordinates(165, 150));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(220+40*i, 150));
		}
		layertwo.add(new SquareCoordinates(600, 150));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(600, 210+40*i));
		}
		// layer2 initialized
		
		// layer3 initialization
		
		layerthree.add(new SquareCoordinates(690,660));
		for(int i=0;i<11;i++) {
			layerthree.add(new SquareCoordinates(620-40*i, 660));
		}
		for(int i=0;i<2;i++) {
			layerthree.add(new SquareCoordinates(175-40*i, 660));
		}
		layerthree.add(new SquareCoordinates(90,660));
		for(int i=0;i<6;i++) {
			layerthree.add(new SquareCoordinates(90, 615-40*i));
		}
		for(int i=0;i<7;i++) {
			layerthree.add(new SquareCoordinates(90, 370-40*i));
		}
		layerthree.add(new SquareCoordinates(90,70));
		for(int i=0;i<3;i++) {
			layerthree.add(new SquareCoordinates(135+40*i, 70));
		}
		for(int i=0;i<10;i++) {
			layerthree.add(new SquareCoordinates(260+40*i, 70));
		}
		layerthree.add(new SquareCoordinates(690,70));
		for(int i=0;i<13;i++) {
			layerthree.add(new SquareCoordinates(690, 130+40*i));
		}
		//layer3 initialized
		layerList.add(layerone);
		layerList.add(layertwo);
		layerList.add(layerthree);
		
	}
	
	public SquareCoordinates getSquareCoordinates(Square square) {
		int layer = 0;
		int number = 1;
		if(square.getName().substring(3).equals("1st layer")) {
			layer = 0;
		}
		else if(square.getName().substring(3).equals("2nd layer")) {
			layer = 1;
		}
		else if(square.getName().substring(3).equals("3rd layer")){
			layer = 2;
		}
		char aChar = square.getName().charAt(0);
		number =  aChar - '0';
		return layerList.get(layer).get(number);
	}
	
	
	

	public ArrayList<SquareCoordinates> getLayerone() {
		return layerone;
	}

	public ArrayList<SquareCoordinates> getLayertwo() {
		return layertwo;
	}

	public ArrayList<SquareCoordinates> getLayerthree() {
		return layerthree;
	}

	public List<List<SquareCoordinates>> getLayerList() {
		return layerList;
	}
	

}
