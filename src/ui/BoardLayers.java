package ui;

import java.util.ArrayList;
import java.util.List;

public class BoardLayers {
	
	private ArrayList<SquareCoordinates> layerone;
	private ArrayList<SquareCoordinates> layertwo;
	private ArrayList<SquareCoordinates> layerthree;
	List<List<SquareCoordinates>> layerList = new ArrayList<List<SquareCoordinates>>();
	
	public BoardLayers() {
		
		layerone = new ArrayList<SquareCoordinates>();
		layertwo = new ArrayList<SquareCoordinates>();
		layerthree = new ArrayList<SquareCoordinates>();

	// short lines = 38 , long lines = 78
		//layer1 initialization
		layerone.add(new SquareCoordinates(451,451));
		for(int i=0;i<5;i++) {
			layerone.add(new SquareCoordinates(413-38*i, 451));
		}
		layerone.add(new SquareCoordinates(183, 451));
		for(int i=0;i<5;i++) {
			layerone.add(new SquareCoordinates(183, 413-38*i));
		}
		layerone.add(new SquareCoordinates(183, 183));
		layerone.add(new SquareCoordinates(261, 183));
		for(int i=0;i<5;i++) {
			layerone.add(new SquareCoordinates(299+38*i, 183));
		}
		layerone.add(new SquareCoordinates(451, 261));
		for(int i=0;i<4;i++) {
			layerone.add(new SquareCoordinates(451, 299+38*i));
		}
		//layer1 initialized
		
		//layer2 initialization
		
		layertwo.add(new SquareCoordinates(533, 533));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(495-38*i, 533));
		}
		layertwo.add(new SquareCoordinates(113, 533));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(113, 495-38*i));
		}
		layertwo.add(new SquareCoordinates(113, 113));
		layertwo.add(new SquareCoordinates(191, 113));
		for(int i=0;i<9;i++) {
			layertwo.add(new SquareCoordinates(229+38*i, 113));
		}
		layertwo.add(new SquareCoordinates(533, 191));
		for(int i=0;i<8;i++) {
			layertwo.add(new SquareCoordinates(533, 229+38*i));
		}
		// layer2 initialized
		
		// layer3 initialization
		
		layerthree.add(new SquareCoordinates(616,616));
		for(int i=0;i<13;i++) {
			layerthree.add(new SquareCoordinates(578-38*i, 616));
		}
		layerthree.add(new SquareCoordinates(44,616));
		for(int i=0;i<13;i++) {
			layerthree.add(new SquareCoordinates(44, 578-38*i));
		}
		layerthree.add(new SquareCoordinates(44,44));
		layerthree.add(new SquareCoordinates(122,44));
		for(int i=0;i<13;i++) {
			layerthree.add(new SquareCoordinates(160+38*i, 44));
		}
		layerthree.add(new SquareCoordinates(616,122));
		for(int i=0;i<12;i++) {
			layerthree.add(new SquareCoordinates(616, 160+38*i));
		}
		//layer3 initialized
		layerList.add(layerone);
		layerList.add(layertwo);
		layerList.add(layerthree);
		
	}

	public SquareCoordinates getSquareCoordinates(int layer, int number) {
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
