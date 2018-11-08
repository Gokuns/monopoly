package Model;

import java.util.ArrayList;
import java.util.List;

import Model.SpecialSquares.FreeParking;
import Model.SpecialSquares.payCorners.Go;

public class SquareFactory {
	private static SquareFactory squareFactory;
	
	private SquareFactory() {
		
	}
	
	public static synchronized SquareFactory getInstance() {
		if(squareFactory == null) {
			squareFactory = new SquareFactory();
		}
		return squareFactory;
	}
	
	public List<List<Square>> createSquares() {
		List<List<Square>> squares = new ArrayList<>();
		Square Go = new Go("Go", "dec1");
		Square FreePark = new FreeParking("Free Parking", "desc2");
		Square Street1 = new Street("street 1", 100, "desc3", "color", null);
		Square Street2 = new Street("street 2", 120, "desc4", "color", null);
		Square Street3 = new Street("street 3", 150, "desc5", "color", null);
		List<Square> layer1 = new ArrayList<>();
		layer1.add(Go);
		layer1.add(Street1);
		layer1.add(Street2);
		layer1.add(Street3);
		layer1.add(FreePark);
		squares.add(layer1);
		return squares;
	}

}
